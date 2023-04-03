package at.tugraz.ist.ase.model;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.SetVar;

import java.util.List;
import java.util.Map;
import java.util.Set;

/** MultiConfigurationTask */
public class MultiConfigurationTask {
  // Input
  private final List<Question> questions;
  private final List<Integer> examineeIds;

  // Output
  /**
   * V consists of q_ij Whereas q_ij is a question j posed to examinee i Each question has a level
   * and a complexity
   */
  private Map<Integer, List<Question>> V;

  /** D consists of dom(q_11)..dom(q_kl) Whereas q_ij is a question j posed to examinee i */
  private Map<Integer, List<QuestionDomain>> D;

  private Set<Requirement> REQ;

  private Set<Constraint> C;

  public MultiConfigurationTask(
      List<Question> questions,
      List<Integer> examineeIds,
      Set<Requirement> REQ,
      Set<Constraint> C) {
    this.questions = questions;
    this.examineeIds = examineeIds;
    this.REQ = REQ;
    this.C = C;
  }

  public Map<Integer, List<Question>> createExams() {
    Model model = new Model("Multi Exam Configuration");

    int[] questionNumbers = new int[questions.size()];
    int[] questionTypes = new int[questions.size()];
    int[] questionLevel = new int[questions.size()];
    initializeArrays(questionNumbers, questionTypes, questionLevel);

    SetVar varQuestionNumbers = model.setVar("Question Number", questionNumbers);
    SetVar varQuestionTypes = model.setVar("Question Type", questionTypes);
    SetVar varQuestionLevels = model.setVar("Question Level", questionLevel);
    SetVar varExamineeIds =
        model.setVar("Examinee Ids", examineeIds.stream().mapToInt(Integer::intValue).toArray());

    return null;
  }

  private void initializeArrays(int[] questionNumbers, int[] questionTypes, int[] questionLevel) {
    int i = 0;
    for (var q : questions) {
      questionNumbers[i] = q.getNumber();
      questionTypes[i] = q.getType();
      questionLevel[i] = q.getLevel();
      i++;
    }
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public List<Integer> getExamineeIds() {
    return examineeIds;
  }

  public Map<Integer, List<Question>> getV() {
    return V;
  }

  public Map<Integer, List<QuestionDomain>> getD() {
    return D;
  }

  public Set<Requirement> getREQ() {
    return REQ;
  }

  public Set<Constraint> getC() {
    return C;
  }
}
