package at.tugraz.ist.ase.model;

import java.util.List;
import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.IntVar;
import com.google.ortools.sat.Literal;

/** MultiConfigurationTask */
public class MultiConfigurationTask {
  private final int numberOfExaminees;
  private final int numberOfQuestionsPerExaminee;
  private final int numberOfTotalQuestions;
  private final int[][] questions;

  /** Number of examinees */
  private List<Integer> k;

  private List<Question> q;

  /**
   * V consists of q_ij Whereas q_ij is a question j posed to examinee i Each question has a level
   * and a complexity
   */
  private List<Exam> V;

  /** D consists of dom(q_11)..dom(q_kl) Whereas q_ij is a question j posed to examinee i /* */
  /*
  @ValueRangeProvider
  @ProblemFactCollectionProperty
  private Map<Integer, List<QuestionDomain>> D;

  private Set<Requirement> REQ;

  private Set<Constraint> C;
  */

  public MultiConfigurationTask(int numberOfExaminees, int numberOfQuestionsPerExaminee, int numberOfTotalQuestions, int[][] questions) {
    this.numberOfExaminees = numberOfExaminees;
    this.numberOfQuestionsPerExaminee = numberOfQuestionsPerExaminee;
    this.numberOfTotalQuestions = numberOfTotalQuestions;
    this.questions = questions;
  }

  public long[][] solve() {
    Loader.loadNativeLibraries();

    long[][] exams = new long[numberOfExaminees][numberOfQuestionsPerExaminee];

    CpModel model = new CpModel();

    IntVar[][] e = new IntVar[numberOfExaminees][numberOfQuestionsPerExaminee];
    for (int i = 0; i < numberOfExaminees; i++) {
      for (int j = 0; j < numberOfQuestionsPerExaminee; j++) {
        e[i][j] = model.newIntVar(0, numberOfTotalQuestions, "Examinee[" + i + "] - Question[" + j + "]");
      }
      model.addAllDifferent(e[i]);
    }

    CpSolver solver = new CpSolver();
    CpSolverStatus status = solver.solve(model);

    if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
      for (int i = 0; i < numberOfExaminees; i++) {
        for (int j = 0; j < numberOfQuestionsPerExaminee; j++) {
          exams[i][j] = solver.value(e[i][j]);
        }
      }
    } else {
      System.out.println("No solution found.");
    }

    return exams;
  }

  public List<Integer> getK() {
    return k;
  }

  public void setK(List<Integer> k) {
    this.k = k;
  }

  public List<Exam> getV() {
    return V;
  }

  public void setV(List<Exam> v) {
    V = v;
  }

  public List<Question> getQ() {
    return q;
  }

  public void setQ(List<Question> q) {
    this.q = q;
  }

}
