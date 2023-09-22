import at.tugraz.ist.ase.model.ExamMultiConfigurationModel;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExamMultiConfigurationModelTests {
  @Test
  public void generalTest() {
    var e = new ExamMultiConfigurationModel();
    e.initialize();
    var solver = e.getSolver();

    int idx = 0;
    while (solver.solve()) {
      if (idx == 100) {
        break;
      }
      for (int examinee = 0; examinee < 2; examinee++) {
        int[] questions = new int[4];
        int[] questionTypes = new int[4];
        int[] questionLevels = new int[4];

        for (int i = 0; i < 4; i++) {
          questions[i] = e.getV().getQuestion(examinee, i).getValue();
          questionTypes[i] = e.getV().getQuestionType(examinee, i).getValue();
          questionLevels[i] = e.getV().getQuestionLevel(examinee, i).getValue();
        }

        // cstr 1: all questions must be different
        assertEquals(
            Arrays.stream(questions).distinct().count(),
            questions.length,
            "Questions must be unique!");

        // cstr 2: categories 1,2 must be <= 30% for examinee 0
        if (examinee == 0) {
          int sum = 0;
          for (int questionType : questionTypes) {
            if (questionType == 1 || questionType == 2) {
              sum++;
            }
          }
          int threshold = (int) (questionTypes.length * 0.3);
          assertTrue(sum <= threshold, "Question types of category 1,2 exceed the threshold!");
        }
        // cstr 3: category 3 not included
        if (examinee == 0) {
          for (int questionType : questionTypes) {
            assertNotEquals(questionType, 3, "No questions of category 3 allowed!");
          }
        }

        // cstr 4: question number 2 not allowed in any exam
        for (int question : questions) {
          assertNotEquals(question, 2, "Question 2 not allowed!");
        }

        // cstr 5: each exam includes either question u or question v
        boolean found = false;
        for (int question : questions) {
          if (question == 6 || question == 7) {
            found = true;
            break;
          }
        }
        assertTrue(found, "Question was not found!");
        idx++;
      }
    }
  }
}
