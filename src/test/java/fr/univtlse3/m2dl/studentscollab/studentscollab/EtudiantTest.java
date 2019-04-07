package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
public class EtudiantTest {

    private static Validator validator;

    @BeforeClass
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEtudiantNomNull() {
        //given: un étudiant crée un compte avec un nom null
        //when: l'ojet de l'utilisateur est créé
        Etudiant etudiant = new Etudiant(null, "john", "john@gmail.com", "000");
        //then: l'étudiant n'est pas validé par le validateur
        Assert.assertFalse(validator.validate(etudiant).isEmpty());
    }

    @Test
    public void testEtudiantNomVide() {
        //given: un étudiant avec un nom vide
        //when: etudiant créé
        Etudiant etudiant = new Etudiant("", "john", "john@gmail.com", "000");
        //then: l'étudiant n'est pas validé par le validateur
        Assert.assertFalse(validator.validate(etudiant).isEmpty());
    }

    @Test
    public void testEtudiantPreomNull() {
        //given: un étudiant crée un compte avec un prenom null
        //when: l'ojet de l'utilisateur est créé
        Etudiant etudiant = new Etudiant("do", null, "john@gmail.com", "000");
        //then: l'étudiant n'est pas validé par le validateur
        Assert.assertFalse(validator.validate(etudiant).isEmpty());
    }

    @Test
    public void testEtudiantPrenomVide() {
        //given: un étudiant avec un prenom vide
        //when: etudiant créé
        Etudiant etudiant = new Etudiant("", "", "john@gmail.com", "000");
        //then: l'étudiant n'est pas validé par le validateur
        Assert.assertFalse(validator.validate(etudiant).isEmpty());
    }

    @Test
    public void testUtilisateurEmailNull() {
        // given: un étudiant util avec un email null
        // when: util est créé
        Etudiant etudiant = new Etudiant("Durand", "john", null, "Mff");
        // then: l'étudiant n'est pas validé par le validator
        assertFalse(validator.validate(etudiant).isEmpty());
    }

    @Test
    public void testUtilisateurMDPNull() {
        // given: un étudiant avec un mdp null
        // when: util est créé
        Etudiant etudiant = new Etudiant("Durand", "john", "durant@gmail.com", null);
        // then: l'étudiant n'est pas validé par le validator
        assertFalse(validator.validate(etudiant).isEmpty());
    }

}