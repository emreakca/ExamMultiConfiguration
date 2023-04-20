package at.tugraz.ist.ase;

import at.tugraz.ist.ase.model.Exam;
import at.tugraz.ist.ase.model.MultiConfigurationTask;
import at.tugraz.ist.ase.model.Question;
import at.tugraz.ist.ase.solver.ExamConstraintProvider;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.groupingBy;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    SolverFactory<MultiConfigurationTask> solverFactory =
        SolverFactory.create(
            new SolverConfig()
                .withSolutionClass(MultiConfigurationTask.class)
                .withEntityClasses(Exam.class)
                .withConstraintProviderClass(ExamConstraintProvider.class)
                // The solver runs only for 5 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

    // Load the problem
    MultiConfigurationTask problem = generateDemoData();

    // Solve the problem
    Solver<MultiConfigurationTask> solver = solverFactory.buildSolver();
    MultiConfigurationTask solution = solver.solve(problem);

    // Visualize the solution
    printExams(solution);
  }

  private static void printExams(MultiConfigurationTask solution) {
    var exams = solution.getV().stream().collect(groupingBy(Exam::getExaminee));

    for (Integer examinee : exams.keySet()) {
      System.out.println("Examinee " + examinee + ":");

      for (Exam e : exams.get(examinee)) {
        System.out.println("\tQuestion <" + e.getQuestion().getId() + ">");
      }

      System.out.println();
    }

    /*
    for (Exam exam : solution.getV()) {
      System.out.println("Examinee " + exam.getId() + ":");
      for (Question q : exam.getQuestions()) {
        System.out.println("\tQuestion <" + q.getId() + ">");
      }
      System.out.println();
    }
     */
  }

  public static MultiConfigurationTask generateDemoData() {
    var random = new Random();
    List<Exam> exams = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    List<List<Question>> q = new ArrayList<>();

    List<Integer> k =
        new ArrayList<>() {
          {
            add(1);
            add(2);
          }
        };

    for (long i = 1; i <= 8; i++) {
      questions.add(new Question(i, random.nextInt(4), random.nextInt(4)));
    }

    long id = 1;
    for (int i = 1; i <= 2; i++) {
      for (int j = 0; j < questions.size(); j++) {
        exams.add(new Exam(id++, i, questions.get(j)));
      }
    }

    return new MultiConfigurationTask(k, questions, exams);
  }
}
