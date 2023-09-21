package at.tugraz.ist.ase.model;

import lombok.Getter;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class V {
  @Getter private final IntVar[][] q;

  @Getter private final IntVar[][] q_type;

  @Getter private final IntVar[][] q_level;

  private final D D;

  public V(Model model, D D) {
    this.D = D;
    int numberExaminees = D.getNumberOfExaminees();
    int numberQuestions = D.getNumberOfQuestions();

    q = model.intVarMatrix("Q", numberExaminees, numberQuestions, 1, D.getQuestionUB());
    q_type =
        model.intVarMatrix("Q_Type", numberExaminees, numberQuestions, 1, D.getQuestionTypeUB());
    q_level =
        model.intVarMatrix("Q_Level", numberExaminees, numberQuestions, 1, D.getQuestionLevelUB());
  }

  public IntVar getQuestion(int examinee, int question) {
    return q[examinee][question];
  }

  public IntVar getQuestionType(int examinee, int question) {
    return q_type[examinee][question];
  }

  public IntVar getQuestionLevel(int examinee, int question) {
    return q_level[examinee][question];
  }

  public void print() {
    for (int e = 0; e < D.getNumberOfExaminees(); e++) {
      System.out.println("Examinee " + (e + 1));
      for (int q = 0; q < D.getNumberOfQuestions(); q++) {
        System.out.println(
            getQuestion(e, q) + ", " + getQuestionType(e, q) + ", " + getQuestionLevel(e, q));
      }
      System.out.println();
    }
  }
}
