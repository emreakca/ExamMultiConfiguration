package at.tugraz.ist.ase.model;

import at.tugraz.ist.ase.helper.Operators;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.jheaps.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExamMultiConfigurationModel {

  @Getter private Model model;

  @Getter private V V;

  private D D;

  public ExamMultiConfigurationModel() {
    model = new Model("Ex");
    D = new D();
    V = new V(model, D);
  }

  public void initialize() {
    log.debug("\tInitializing ExamMultiConfigurationModel for {} >>>", model.getName());

    // Constraint 1: ALl questions must be different
    addConstraintAllDifferent();

    /// Constraint 2: we assume that examinee a prefers to have included ≤ 30%
    // of questions related to the categories {α, β}
    int examinee = 0;
    int[] categories = {1, 2};
    double percentage = 0.3;
    questionsRelatedToCertainCategories(examinee, categories, Operators.le, percentage);

    // Constraint 3: No questions related to 3
    questionsNotRelatedToCategory(examinee, 3);

    // Constraint 4: Exclude question "v" from "x" exams;
    int v = 2;
    int x = 0;
    excludeQuestionVFromXExams(v, x);

    // Constraint 5: Each exam contains question u or question v
    int[] questionsToInclude = {6, 7};
    includeQuestionsForEachExam(questionsToInclude);

    log.debug("\t<<< Model {} initialized", model.getName());
  }

  private void includeQuestionsForEachExam(int[] questionsToInclude) {
    BoolVar[] isTargetValue = new BoolVar[D.getNumberOfExaminees() * D.getNumberOfQuestions()];

    int targetIdx = 0;
    for (int examineeIdx = 0; examineeIdx < D.getNumberOfExaminees(); examineeIdx++) {
      for (int questionIdx = 0; questionIdx < D.getNumberOfQuestions(); questionIdx++) {
        isTargetValue[targetIdx++] =
            model
                .or(
                    model.arithm(
                        V.getQuestion(examineeIdx, questionIdx), "=", questionsToInclude[0]),
                    model.arithm(
                        V.getQuestion(examineeIdx, questionIdx), "=", questionsToInclude[1]))
                .reify();
      }
    }

    model.or(isTargetValue).post();
  }

  private void excludeQuestionVFromXExams(int v, int x) {
    IntVar[] questions = new IntVar[D.getNumberOfExaminees() * D.getNumberOfQuestions()];

    int idx = 0;
    for (int examineeIdx = 0; examineeIdx < D.getNumberOfExaminees(); examineeIdx++) {
      for (int questionIdx = 0; questionIdx < D.getNumberOfQuestions(); questionIdx++) {
        questions[idx++] = V.getQuestion(examineeIdx, questionIdx);
      }
    }

    model.count("", v, questions).le(x).post();
  }

  private void questionsRelatedToCertainCategories(
      int examinee, int[] categories, String op, double percentage) {
    int threshold = (int) (D.getNumberOfQuestions() * percentage);

    IntVar categoryCount =
        model.intVar("count1And2ForExaminee" + examinee, 1, D.getNumberOfQuestions());

    for (int questionIdx = 0; questionIdx < D.getNumberOfQuestions(); questionIdx++) {
      // Redundant, only the last constraint is needed
      Constraint[] constraints = new Constraint[categories.length];
      for (int categoryIdx = 0; categoryIdx < categories.length; categoryIdx++) {
        constraints[categoryIdx] =
            model.arithm(V.getQuestionType(examinee, questionIdx), "=", categories[categoryIdx]);
      }

      model.ifThen(model.or(constraints), model.arithm(categoryCount, ">", questionIdx));
    }

    model.arithm(categoryCount, op, threshold).post();
  }

  private void questionsNotRelatedToCategory(int examinee, int category) {
    for (int questionIdx = 0; questionIdx < D.getNumberOfQuestions(); questionIdx++) {
      model.arithm(V.getQuestionType(examinee, questionIdx), Operators.ne, category).post();
    }
  }

  private void addConstraintAllDifferent() {
    for (IntVar[] q : V.getQ()) {
      model.allDifferent(q).post();
    }
  }

  public Solver solve() {
    Solver solver = model.getSolver();

    if (solver.solve()) {
      // do something, e.g. print out variable values
      V.print();
    } else {
      System.out.println("The solver has proved the problem has no solution");
    }

    return solver;
  }

  @VisibleForTesting
  public Solver getSolver() {
    return model.getSolver();
  }

  public List<Solution> solveAll() {
    var solutions = new ArrayList<Solution>();
    Solver solver = model.getSolver();

    while (solver.solve()) {
      Solution sol = new Solution(model);
      sol.record();
      solutions.add(sol);
      // V.print();
    }

    return solutions;
  }
}
