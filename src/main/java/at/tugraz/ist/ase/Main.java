package at.tugraz.ist.ase;

import at.tugraz.ist.ase.model.Exam;
import at.tugraz.ist.ase.model.MultiConfigurationTask;
import at.tugraz.ist.ase.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    // Load the problem
    MultiConfigurationTask problem = generateDemoData();

    var exams = problem.solve();

    for (int i = 0; i < 2; i++) {
      System.out.println("Examinee " + i);
      for (int j = 0; j < 5; j++) {
        System.out.println("\t" + exams[i][j]);
      }
      System.out.println();
    }
  }

  public static MultiConfigurationTask generateDemoData() {
    int numberOfExaminees = 2;
    int numberOfQuestionsPerExaminee = 5;
    int numberOfTotalQuestions = 10;

    // id, type, level
    int[][] questions = {
      {1, 1, 1},
      {2, 1, 1},
      {3, 1, 2},
      {4, 1, 2},
      {5, 2, 1},
      {6, 2, 1},
      {7, 2, 3},
      {8, 2, 4},
      {9, 3, 1},
      {10, 3, 1},
      {11, 3, 1},
      {12, 3, 2},
      {13, 4, 5},
      {14, 4, 4},
      {15, 4, 3},
      {16, 4, 4},
      {17, 5, 1},
      {18, 5, 1},
      {19, 5, 1},
      {20, 5, 1},
    };

    return new MultiConfigurationTask(
        numberOfExaminees, numberOfQuestionsPerExaminee, numberOfTotalQuestions, questions);
  }
}
