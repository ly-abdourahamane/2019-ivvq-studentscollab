package fr.univtlse3.m2dl.studentscollab.studentscollab.formation;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertTrue;

/**
 * @author abdou on 20/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
public class FormationTest {

    private static Validator validator;
    private Formation formation;

    @BeforeClass
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setup() {
        formation = new Formation("dev logiciel", "M2");
    }

    @Test
    public void testFormationNomNull() {
        //given: une formation créé  avec nom null
        //when: l'ojet de l'utilisateur est créé
        formation.setNom(null);
        //then: la formation n'est pas validée par le validateur
        Assert.assertFalse(validator.validate(formation).isEmpty());
    }

    @Test
    public void testFormationNomVide() {
        //given: une formation avec un nom vide
        //when: Formation créé
       formation.setNom("");
        //then: la formation n'est pas validée par le validateur
        Assert.assertFalse(validator.validate(formation).isEmpty());
    }

    @Test
    public void testFormatioNiveauNull() {
        //given: une formation créé  avec nom null
        //when: l'ojet formation est créé
        formation.setNiveau(null);
        //then: la formation n'est pas validée par le validateur
        Assert.assertFalse(validator.validate(formation).isEmpty());
    }

    @Test
    public void testFormationNiveauVide() {
        //given: une formation avec un nom vide
        //when: Formation créé
        formation.setNiveau("");
        //then: la formation n'est pas validée par le validateur
        Assert.assertFalse(validator.validate(formation).isEmpty());
    }

    @Test
    public void testGettersEtSetters() {
        formation.setNom("info");
        Assert.assertTrue("Le nom de la formation est info", formation.getNom().equals("info"));

        formation.setNiveau("L3");
        Assert.assertFalse("Le niveau de la formation est n'a pas changé", formation.getNiveau().equals("M2"));
    }
}
