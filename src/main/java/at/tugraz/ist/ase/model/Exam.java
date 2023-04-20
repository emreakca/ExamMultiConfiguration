package at.tugraz.ist.ase.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Exam {
  @PlanningId
  private Long id;

  @PlanningVariable
  private Integer examinee;

  @PlanningVariable
  private Question question;

  public Exam() {
  }

  public Exam(Long id, Integer examinee, Question question) {
    this.id = id;
    this.examinee = examinee;
    this.question = question;
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

  public Question getQuestion() {
    return question;
  }

  public void setQuestions(Question question) {
    this.question = question;
  }
}
