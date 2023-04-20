package at.tugraz.ist.ase.model;

public class QuestionDomain {
  /** 1..p p = number of questions */
  Integer numberOfQuestions;

  /** 1..q_ q_ = number of question categories */
  Integer numberOfQuestionCategories;

  /** 1..r r = number of question complexity levels */
  Integer numberOfQuestionComplexityLevels;

  public QuestionDomain() {}

  public QuestionDomain(
      Integer numberOfQuestions,
      Integer numberOfQuestionCategories,
      Integer numberOfQuestionComplexityLevels) {
    this.numberOfQuestions = numberOfQuestions;
    this.numberOfQuestionCategories = numberOfQuestionCategories;
    this.numberOfQuestionComplexityLevels = numberOfQuestionComplexityLevels;
  }

  public Integer getNumberOfQuestions() {
    return numberOfQuestions;
  }

  public void setNumberOfQuestions(Integer numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
  }

  public Integer getNumberOfQuestionCategories() {
    return numberOfQuestionCategories;
  }

  public void setNumberOfQuestionCategories(Integer numberOfQuestionCategories) {
    this.numberOfQuestionCategories = numberOfQuestionCategories;
  }

  public Integer getNumberOfQuestionComplexityLevels() {
    return numberOfQuestionComplexityLevels;
  }

  public void setNumberOfQuestionComplexityLevels(Integer numberOfQuestionComplexityLevels) {
    this.numberOfQuestionComplexityLevels = numberOfQuestionComplexityLevels;
  }
}
