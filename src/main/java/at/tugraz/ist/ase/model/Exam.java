package at.tugraz.ist.ase.model;

import java.util.List;

public class Exam {
  private Long id;

  private Integer examinee;

  private List<Question> questions;

  public Exam() {
  }

  public Exam(Long id, Integer examinee, List<Question> questions) {
    this.id = id;
    this.examinee = examinee;
    this.questions = questions;
  }

  public Long getId() {
    return id;
  }

  public Integer getExaminee() {
    return examinee;
  }

  public void setExaminee(Integer examinee) {
    this.examinee = examinee;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> question) {
    this.questions = question;
  }

}
