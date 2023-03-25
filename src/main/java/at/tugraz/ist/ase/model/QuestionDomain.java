package at.tugraz.ist.ase.model;

public class QuestionDomain {
    /**
     * 1..p
     * p = number of questions
     */
    Integer numberOfQuestions;

    /**
     * 1..q_
     * q_ = number of question categories
     */
    Integer numberOfQuestionCategories;

    /**
     * 1..r
     * r = number of question complexity levels
     */
    Integer numberOfQuestionComplexityLevels;
}
