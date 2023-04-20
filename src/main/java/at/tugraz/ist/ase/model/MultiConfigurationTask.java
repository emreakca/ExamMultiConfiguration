package at.tugraz.ist.ase.model;

import java.util.List;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

/** MultiConfigurationTask */
@PlanningSolution
public class MultiConfigurationTask {
  /** Number of examinees */
  @ValueRangeProvider @ProblemFactCollectionProperty private List<Integer> k;

  @ValueRangeProvider @ProblemFactCollectionProperty private List<Question> q;

  /**
   * V consists of q_ij Whereas q_ij is a question j posed to examinee i Each question has a level
   * and a complexity
   */
  @PlanningEntityCollectionProperty private List<Exam> V;

  /** D consists of dom(q_11)..dom(q_kl) Whereas q_ij is a question j posed to examinee i /* */
  /*
  @ValueRangeProvider
  @ProblemFactCollectionProperty
  private Map<Integer, List<QuestionDomain>> D;

  private Set<Requirement> REQ;

  private Set<Constraint> C;
  */

  @PlanningScore private HardSoftScore score;

  public MultiConfigurationTask() {}

  public MultiConfigurationTask(
      List<Integer> k, List<Question> q, List<Exam> v) {
    this.k = k;
    this.q = q;
    this.V = v;
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

  public HardSoftScore getScore() {
    return score;
  }

  public void setScore(HardSoftScore score) {
    this.score = score;
  }
}
