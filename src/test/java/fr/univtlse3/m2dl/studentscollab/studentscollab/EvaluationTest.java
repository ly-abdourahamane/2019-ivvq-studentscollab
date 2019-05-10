package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.EvalType;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Evaluation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class EvaluationTest {

    private static Validator validator;
    private Etudiant redacteur = new Etudiant("A", "A", "aa@gmail.com", "aaa");
    private NoteCours nct = new NoteCours("nouvelleNote", "contenu", 0, 0, redacteur);

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValide() {
        // given: une Evaluation eval
        // when: eval est créée
        Evaluation eval = new Evaluation(redacteur, nct, EvalType.DISLIKE);
        // then: eval est validée par le validator
        assertTrue(validator.validate(eval).isEmpty());
    }

    @Test
    public void testRedacteurNull() {
        // given: une Evaluation eval
        // when: eval est créée
        Evaluation eval = new Evaluation(null, nct, EvalType.DISLIKE);
        // then: eval n'est pas validée par le validator
        assertTrue(!validator.validate(eval).isEmpty());
    }

    @Test
    public void testNoteCoursNull() {
        // given: une Evaluation eval
        // when: eval est créée
        Evaluation eval = new Evaluation(redacteur, null, EvalType.DISLIKE);
        // then: eval n'est pas validée par le validator
        assertTrue(!validator.validate(eval).isEmpty());
    }

    @Test
    public void testTypeNull() {
        // given: une Evaluation eval
        // when: eval est créée
        Evaluation eval = new Evaluation(redacteur, nct, null);
        // then: eval n'est pas validée par le validator
        assertTrue(!validator.validate(eval).isEmpty());
    }
}
