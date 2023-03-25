package at.tugraz.ist.ase.model;

import org.chocosolver.solver.constraints.Constraint;

import java.util.Map;
import java.util.Set;

public class MultiConfigurationTask {
    /**
     * V consists of q_ij
     * Whereas q_ij is a question j posed to examinee i
     * Each question has a level and a complexity
     */
    Map<Integer, Question> V;

    /**
     * D consists of dom(q_11)..dom(q_kl)
     * Whereas q_ij is a question j posed to examinee i
     */
    Map<Integer, QuestionDomain> D;

    Set<Requirement> REQ;

    Set<Constraint> C;
}
