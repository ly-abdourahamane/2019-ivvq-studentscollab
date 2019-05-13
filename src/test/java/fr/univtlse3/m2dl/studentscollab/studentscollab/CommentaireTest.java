package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Commentaire;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommentaireTest {

    static Validator validator;
    private Commentaire c = new Commentaire("mon commentaire");

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testConstructeurNominalValidated() {
        // given: un commentaire
        // when: commentaire est créé
        Commentaire c = new Commentaire("mon commentaire");
        // then: c est validé par le validator
        assertTrue(validator.validate(c).isEmpty());
    }

    @Test
    public void testConstructeurContenuNullInvalidated() {
        // given: un commentaire avec un contenu null
        // when: commentaire est créé
        Commentaire c = new Commentaire(null);
        // then: c n'est pas validé par le validator
        assertFalse(validator.validate(c).isEmpty());
    }

}
