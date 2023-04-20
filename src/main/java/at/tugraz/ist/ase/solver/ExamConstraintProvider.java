package at.tugraz.ist.ase.solver;

import at.tugraz.ist.ase.model.Exam;
import at.tugraz.ist.ase.model.Question;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class ExamConstraintProvider implements ConstraintProvider {
  @Override
  public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
    return new Constraint[] {
      questionConflict(constraintFactory)
    };
  }

  private Constraint examConflict(ConstraintFactory constraintFactory) {
    return constraintFactory
        .forEach(Exam.class)
        .join(Exam.class, Joiners.lessThan(Exam::getExaminee))
        .penalize(HardSoftScore.ONE_HARD)
        .asConstraint("Unique examinees");
  }

  private Constraint questionConflict(ConstraintFactory constraintFactory) {
    return constraintFactory
        .forEach(Question.class)
        .join(Question.class, Joiners.lessThan(Question::getId))
            .penalize(HardSoftScore.ONE_HARD)
        .asConstraint("Unique questions");
  }

  private Constraint questionLevelConflict(ConstraintFactory constraintFactory) {
    return constraintFactory
            .forEach(Question.class)
            .filter(q -> q.getLevel() < 2)
            .penalize(HardSoftScore.ONE_HARD, q -> q.getLevel() - 1)
            .asConstraint("QLevel");
  }
}
