package fr.univtlse3.m2dl.studentscollab.studentscollab.inscription;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author abdou on 24/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InscriptionServiceIntegrationTest {

    @Autowired
    private InscriptionService inscriptionService;

    private Inscription inscription;
    private Etudiant etudiant;
    private Formation formation;

    @Before
    public void setup() {
        etudiant = new Etudiant("ly", "abdou", "abdourahamane.ly1@gmail.com", "123456");
        formation = new Formation("informatique", "M2DL");
        inscription = new Inscription(etudiant, formation);
    }

    @Test
    public void testSaveInscriptionHashId() {
        // given: une inscription non persistée Formation
        // then: inscription n'a pas d'id
        assertNull(inscription.getId());
        // when: inscription est persistée
        inscriptionService.saveInscription(inscription);
        // then: inscription a un id
        assertNotNull(inscription.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInscriptiontNull(){
        // when: null est persisté via un inscriptionService
        // then: une exception IllegalArgumentException est levée
        inscriptionService.saveInscription(null);
    }

    @Test
    public void testFetchedInscriptionIsNotNull() {
        // given: une Inscription persistée
        inscriptionService.saveInscription(inscription);
        // when: on appelle findById avec l'id de cette Formation
        Inscription fetched = inscriptionService.findById(inscription.getId()).get();
        // then: le résultat n'est pas null
        assertNotNull(fetched);
    }

    @Test
    public void testFetchedInscriptionIsUnchaned() {
        // given: une inscription persistée inscription
        inscriptionService.saveInscription(inscription);
        // when: on appelle findById avec l'id de cette incription
        Inscription fetched = inscriptionService.findById(inscription.getId()).get();
        // then: l'inscription obtenue en retour a le bon id
        assertEquals(fetched.getId(), inscription.getId());
        // then : l'inscription obtenue en retour est le bon
        assertEquals(fetched.getEtudiant(), inscription.getEtudiant());
        assertEquals(fetched.getDateInscription(), inscription.getDateInscription());
        assertEquals(fetched.getFormation(), inscription.getFormation());
    }

    @Test
    @Transactional
    public void testUpdatedInscription() {
        // given: une inscription persistée
        inscriptionService.saveInscription(inscription);
        Etudiant etudiant1 = new Etudiant("dupond", "yael", "abdourahamane.ly1@gmail.com", "123456");

        Inscription fetched = inscriptionService.findById(inscription.getId()).get();
        // when: l'étudiant est modifié au niveau "objet"
        fetched.setEtudiant(etudiant1);
        // when: l'objet inscription est mis à jour en base
        inscriptionService.saveInscription(fetched);
        // when: l'objet inscription est relu en base
        Inscription fetchedUpdated = inscriptionService.findById(inscription.getId()).get();
        // then: l'inscription a bien été mis à jour
        assertEquals(fetched.getEtudiant(), fetchedUpdated.getEtudiant());
    }
}
