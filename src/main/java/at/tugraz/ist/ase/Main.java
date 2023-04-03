package at.tugraz.ist.ase;

import at.tugraz.ist.ase.model.MultiConfigurationTask;
import at.tugraz.ist.ase.model.Question;
import at.tugraz.ist.ase.model.Requirement;
import org.chocosolver.solver.constraints.Constraint;

import java.util.*;

public class Main {
  public static void main(String[] args) {

    // Input
    List<Question> questions = generateQuestions();
    List<Integer> examineeIds = generateExamineeIds();
    Set<Requirement> REQ = new HashSet<>();
    Set<Constraint> C = new HashSet<>();

    // Output
    Map<Integer, List<Question>> exams;

    MultiConfigurationTask mct = new MultiConfigurationTask(questions, examineeIds, REQ, C);
    mct.createExams();
  }

  private static List<Integer> generateExamineeIds() {
    List<Integer> examineeIds =
        new ArrayList<>() {
          {
            add(1);
            add(2);
            add(3);
          }
        };

    return examineeIds;
  }

  private static List<Question> generateQuestions() {
    List<Question> questions =
        new ArrayList<>() {
          {
            add(new Question(1, 1, 1));
            add(new Question(2, 3, 4));
            add(new Question(3, 4, 5));
          }
        };

    return questions;
  }
}
