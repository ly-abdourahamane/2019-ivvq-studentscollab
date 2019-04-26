package fr.univtlse3.m2dl.studentscollab.studentscollab.formation;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.FormationService;
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
 * @author abdou on 20/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FormationServiceIntegrationTest {

    @Autowired
    private FormationService formationService;

    private Formation formation;

    @Before
    public void setup() {
        formation = new Formation("developpement logiciel", "M2");
    }

    @Test
    public void testSaveFormationHashId() {
        // given: une Formation non persistée Formation
        // then: formation n'a pas d'id
        assertNull(formation.getId());
        // when: formation est persistée
        formationService.saveFormation(formation);
        // then: formation a un id
        assertNotNull(formation.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormationtNull(){
        // when: null est persisté via un FormationService
        // then: une exception IllegalArgumentException est levée
        formationService.saveFormation(null);
    }

    @Test
    public void testFetchedFormationIsNotNull() {
        // given: une Formation persistée Formation
        formationService.saveFormation(formation);
        // when: on appelle findFormationById avec l'id de cette Formation
        Formation fetched = formationService.findFormationById(formation.getId()).get();
        // then: le résultat n'est pas null
        assertNotNull(fetched);
    }

    @Test
    public void testFetchedFormationIsUnchaned() {
        // given: une Formation persistée Formation
        formationService.saveFormation(formation);
        // when: on appelle findFormationById avec l'id de cet Formation
        Formation fetched = formationService.findFormationById(formation.getId()).get();
        // then: la formation obtenu en retour a le bon id
        assertEquals(fetched.getId(), formation.getId());
        // then : l'Formation obtenue en retour a le bon nom
        assertEquals(fetched.getNom(), formation.getNom());
    }

    @Test
    @Transactional
    public void testUpdatedFormation() {
        // given: une Formation persistée
        formationService.saveFormation(formation);

        Formation fetched = formationService.findFormationById(formation.getId()).get();
        // when: l'email est modifié au niveau "objet"
        fetched.setNiveau("M2 IHM");
        // when: l'objet Formation est mis à jour en base
        formationService.saveFormation(fetched);
        // when: l'objet Formation est relu en base
        Formation fetchedUpdated = formationService.findFormationById(formation.getId()).get();
        // then: l'email a bien été mis à jour
        assertEquals(fetched.getNiveau(), fetchedUpdated.getNiveau());
    }
}
