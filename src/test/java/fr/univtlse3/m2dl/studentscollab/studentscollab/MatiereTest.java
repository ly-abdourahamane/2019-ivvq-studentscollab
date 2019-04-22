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
        Matiere matiere = new Matiere(null);
        assertTrue(validator.validate(matiere).size() != 0);
    }

    @Test
    public void testNomNotNull() {
        Matiere matiere = new Matiere("matiere1");
        assertTrue(validator.validate(matiere).size() == 0);
    }
}