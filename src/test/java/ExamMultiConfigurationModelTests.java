import at.tugraz.ist.ase.model.ExamMultiConfigurationModel;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExamMultiConfigurationModelTests {
  @Test
  public void generalTest() {
    var e = new ExamMultiConfigurationModel();
    e.initialize();
    var sols = e.solveAll();

    assertFalse(sols.isEmpty(), "No solutions!");

    for (var solution : sols) {
      int[] questions = new int[4];
      int[] questionTypes = new int[4];
      int[] questionLevels = new int[4];

      // fill data
      for (int questionIdx = 0; questionIdx < 4; questionIdx++) {
        questions[questionIdx] = solution.getIntVal(e.getV().getQuestion(0, questionIdx));
        questionTypes[questionIdx] = solution.getIntVal(e.getV().getQuestionType(0, questionIdx));
        questionLevels[questionIdx] = solution.getIntVal(e.getV().getQuestionLevel(0, questionIdx));
      }

      // cstr 1: all questions must be different
      assertEquals(
          Arrays.stream(questions).distinct().count(),
          questions.length,
          "Questions must be unique!");

      // cstr 2: categories 1,2 must be <= 30%
      int sum = 0;
      for (int questionType : questionTypes) {
        if (questionType == 1 || questionType == 2) {
          sum++;
        }
      }
      int threshold = (int) (questionTypes.length * 0.3);
      assertTrue(sum <= threshold, "Question types of category 1,2 exceed the threshold!");

      // cstr 3: category 3 not included
      for (int questionType : questionTypes) {
        assertNotEquals(questionType, 3, "No questions of category 3 allowed!");
      }

      for (int question : questions) {
        assertNotEquals(question, 2, "Question 2 not allowed!");
      }
    }
  }
}
