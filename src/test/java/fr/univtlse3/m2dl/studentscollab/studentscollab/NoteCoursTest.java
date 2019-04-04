package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoteCoursTest {

    private static Validator validator;
    private NoteCours nc = new NoteCours("nouvelleNote", "contenu", 0, 0);

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testConstructeurNominalValidated() {
        // given: une Note de cours nc
        // when: nc est créé
        NoteCours nc = new NoteCours("titre1", "contenu1", 0, 0);
        // then: nc est validé par le validator
        assertTrue(validator.validate(nc).isEmpty());
    }

    @Test
    public void testConstructeurTitreNullInvalidated() {
        // given: une Note de cours nc avec un titre null
        // when: nc est créé
        NoteCours nc = new NoteCours(null, "contenu2", 0, 0);
        // then: nc n'est pas validé par le validator
        assertFalse(validator.validate(nc).isEmpty());
    }

    @Test
    public void testConstructeurContenuNullInvalidated() {
        // given: une Note de cours nc avec un contenu null
        // when: nc est créé
        NoteCours nc = new NoteCours(null, "contenu3", 0, 0);
        // then: nc n'est pas validé par le validator
        assertFalse(validator.validate(nc).isEmpty());
    }

    @Test
    public void testConstructeurNbLikeNegInvalidated() {
        // given: une Note de cours nc avec un titre null
        // when: nc est créé
        NoteCours nc = new NoteCours("titre4", "contenu4", -1, 0);
        // then: nc n'est pas validé par le validator
        assertFalse(validator.validate(nc).isEmpty());
    }

    @Test
    public void testConstructeurNbDisLikeNegInvalidated() {
        // given: une Note de cours nc avec un titre null
        // when: nc est créé
        NoteCours nc = new NoteCours("titre5", "contenu5", 1, -1);
        // then: nc n'est pas validé par le validator
        assertFalse(validator.validate(nc).isEmpty());
    }
}
