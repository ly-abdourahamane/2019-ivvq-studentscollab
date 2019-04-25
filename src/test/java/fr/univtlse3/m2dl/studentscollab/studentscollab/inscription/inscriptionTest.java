package fr.univtlse3.m2dl.studentscollab.studentscollab.inscription;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author abdou on 24/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
public class inscriptionTest {

    private static Validator validator;
    private Inscription inscription;
    private Etudiant etudiant;
    private Formation formation;

    @BeforeClass
    public static void setupContext() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Before
    public void setup() {
        etudiant = new Etudiant("ly", "abdou", "abdourahamane.ly1@gmail.com", "123456");
        formation = new Formation("informatique", "M2DL");
        inscription = new Inscription(etudiant, formation);
    }

    @Test
    public void testInscriptionEtudiantNull() {
        //given: une inscription créé  avec etudiant null
        //when: l'ojet de inscription est créé
        inscription.setEtudiant(null);
        //then: l'inscription n'est pas validée par le validateur
        Assert.assertFalse(validator.validate(inscription).isEmpty());
    }

    @Test
    public void testInscriptionFormationNull() {
        //given: une inscription créé  avec formation null
        //when: l'ojet de inscription est créé
        inscription.setFormation(null);
        //then: l'inscription n'est pas validée par le validateur
        Assert.assertFalse(validator.validate(inscription).isEmpty());
    }

    @Test
    public void testGettersEtSetters() {
        etudiant.setPrenom("labdour");
        formation.setNom("master dl");

        Assert.assertTrue("Meme nom de formation", inscription.getFormation().getNom().equals("master dl"));
        Assert.assertTrue("Même nom d'étudiant", inscription.getEtudiant().equals(etudiant));

        etudiant.setId(8541L);
        formation.setId(5245L);

        Assert.assertFalse("",  inscription.getFormation().getId() == 851L);
        Assert.assertFalse("",  inscription.getEtudiant().getId() == 896551L);
    }

    @Test
    public void testUpdateInscriptionDate() {
        Assert.assertTrue("La date d'inscription n'est pas encore créé", inscription.getDateInscription() == null);
    }
}
