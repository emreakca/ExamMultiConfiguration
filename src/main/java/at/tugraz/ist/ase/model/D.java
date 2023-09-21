package at.tugraz.ist.ase.model;

import lombok.Getter;

public class D {
  @Getter private int[][] dom_q;

  @Getter private int[][] dom_q_type;

  @Getter private int[][] dom_q_level;

  public int getQuestionUB() {
    return 10;
  }

  public int getQuestionLevelUB() {
    return 1;
  }

  public int getQuestionTypeUB() {
    return 4;
  }

  public int getNumberOfExaminees() {
    return 1;
  }

  public int getNumberOfQuestions() {
    return 4;
  }
}
