package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionToMatiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertTrue;

public class InscriptionToMatiereTest {

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
        InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere(null,matiere);
        assertTrue(validator.validate(inscriptionToMatiere).size() != 0);
    }

    @Test
    public void testMatiereNull() {
        InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere(etudiant,null);
        assertTrue(validator.validate(inscriptionToMatiere).size() != 0);
    }

    @Test
    public void testEtudiantEtMatiereNotNull() {
        InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere(etudiant,matiere);
        assertTrue(validator.validate(inscriptionToMatiere).size() == 0);
    }
}
