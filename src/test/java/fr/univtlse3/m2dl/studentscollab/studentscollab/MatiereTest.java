package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionToMatiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void testConstructeurValidate() {
        String nom = "nom";
        Set<InscriptionToMatiere> inscriptionToMatieres = new HashSet<>();
        Set<NoteCours> noteCours = new HashSet<>();
        // given: une matière
        // when: la matière est crée
        Matiere matiere = new Matiere(nom,inscriptionToMatieres,noteCours);
        // then: la matière est validé par le validator
        assertTrue(validator.validate(matiere).isEmpty());
    }


    @Test
    public void testConstructeurNotValidate() {
        String nom = "nom";
        Set<InscriptionToMatiere> inscriptionToMatieres = new HashSet<>();
        Set<NoteCours> noteCours = new HashSet<>();
        // given: une matière avec non null
        // when: la matière est crée
        Matiere matiere = new Matiere(null,inscriptionToMatieres,noteCours);
        // then: la matière est validé par le validator
        assertFalse(validator.validate(matiere).isEmpty());
    }

    @Test
    public void testConstructeur2Validate() {
        String nom = "nom";
        Set<InscriptionToMatiere> inscriptionToMatieres = new HashSet<>();
        // given: une matière
        // when: la matière est crée
        Matiere matiere = new Matiere(nom,inscriptionToMatieres);
        // then: la matière est validé par le validator
        assertTrue(validator.validate(matiere).isEmpty());
    }

    @Test
    public void testConstructeur2NotValidate() {
        String nom = "nom";
        Set<InscriptionToMatiere> inscriptionToMatieres = new HashSet<>();
        // given: une matière
        // when: la matière est crée
        Matiere matiere = new Matiere(null,inscriptionToMatieres);
        // then: la matière est validé par le validator
        assertFalse(validator.validate(matiere).isEmpty());
    }





}