package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertTrue;

public class InscriptionTest {

    private static Validator validator;
    private static Matiere matiere;
    private static Etudiant etudiant;

    @BeforeClass
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        matiere = new Matiere();
        etudiant = new Etudiant();
    }

    @Test
    public void testEtudiantNull() {
        Inscription inscription = new Inscription(null,matiere);
        assertTrue(validator.validate(inscription).size() != 0);
    }

    @Test
    public void testMatiereNull() {
        Inscription inscription = new Inscription(etudiant,null);
        assertTrue(validator.validate(inscription).size() != 0);
    }

    @Test
    public void testEtudiantEtMatiereNotNull() {
        Inscription inscription = new Inscription(etudiant,matiere);
        assertTrue(validator.validate(inscription).size() == 0);
    }
}
