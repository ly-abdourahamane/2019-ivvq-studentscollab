package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionToMatiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertFalse;
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
        // given: une inscription avec un étudiant null
        // when: inscription est créée
        InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere();
        // then: inscription n'est pas validé par le validator
        assertFalse(validator.validate(inscriptionToMatiere).isEmpty());
    }

    @Test
    public void testMatiereNull() {
        // given: une inscription avec une matiere null
        // when: inscription est créée
        InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere();
        // then: inscription n'est pas validé par le validator
        assertFalse(validator.validate(inscriptionToMatiere).isEmpty());
    }

    @Test
    public void testEtudiantEtMatiereNotNull() {
        // given: une inscription avec une matiere et un étudiant non null
        // when: inscription est créée
        InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere(etudiant, matiere);
        // then: inscription est validée par le validator
        assertTrue(validator.validate(inscriptionToMatiere).isEmpty());
    }
}
