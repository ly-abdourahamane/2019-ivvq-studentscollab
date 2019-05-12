package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import static org.junit.Assert.*;

public class MatiereTest {

    private static Validator validator;

    @BeforeClass
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNomNull() {
        // given: matiere une matière avec un nom null
        // when: matiere est créée
        Matiere matiere = new Matiere(null);
        // then: matiere n'est pas validé par le validator
        assertFalse(validator.validate(matiere).isEmpty());
    }

    @Test
    public void testNomNotNull() {
        // given: matiere une matière avec un nom non null
        // when: matiere est créée
        Matiere matiere = new Matiere("matiere1");
        // then: matiere est validé par le validator
        assertTrue(validator.validate(matiere).isEmpty());
    }





}