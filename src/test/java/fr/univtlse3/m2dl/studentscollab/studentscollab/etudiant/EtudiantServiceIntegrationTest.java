package fr.univtlse3.m2dl.studentscollab.studentscollab.etudiant;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EtudiantServiceIntegrationTest {

    @Autowired
    private EtudiantService etudiantService;

    private Etudiant etudiant;

    @Before
    public void setup() {
        etudiant = new Etudiant("do", "john", "hohn@gmail.com","123456");
    }

    @Test
    public void testSaveEtudiantHashId() {
        // given: un Etudiant non persisté etudiant
        // then: etudiant n'a pas d'id
        assertNull(etudiant.getId());
        // when: etudiant est persisté
        etudiantService.save(etudiant);
        // then: etudiant a un id
        assertNotNull(etudiant.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEtudiantNull(){
        // when: null est persisté via un EtudiantService
        // then: une exception IllegalArgumentException est levée
        etudiantService.save(null);
    }

    @Test
    public void testFetchedEtudiantIsNotNull() {
        // given: une Etudiant persisté etudiant
        etudiantService.save(etudiant);
        // when: on appelle findEtudiantById avec l'id de cet Etudiant
        Etudiant fetched = etudiantService.findById(etudiant.getId()).get();
        // then: le résultat n'est pas null
        assertNotNull(fetched);
    }

    @Test
    public void testFetchedEtudiantIsUnchangedForDescriptif() {
        // given: une Etudiant persisté etudiant
        etudiantService.save(etudiant);
        // when: on appelle findEtudiantById avec l'id de cet Etudiant
        Etudiant fetched = etudiantService.findById(etudiant.getId()).get();
        // then: l'Etudiant obtenu en retour a le bon id
        assertEquals(fetched.getId(), etudiant.getId());
        // then : l'Etudiant obtenue en retour a le bon nom
        assertEquals(fetched.getNom(), etudiant.getNom());
    }

    @Test
    @Transactional
    public void testUpdatedEtudiant() {
        // given: un Etudiant persisté etudiant
        etudiantService.save(etudiant);

        Etudiant fetched = etudiantService.findById(etudiant.getId()).get();
        // when: l'email est modifié au niveau "objet"
        fetched.setEmail("test@gmail.com");
        // when: l'objet etudiant est mis à jour en base
        etudiantService.save(fetched);
        // when: l'objet etudiant est relu en base
        Etudiant fetchedUpdated = etudiantService.findById(etudiant.getId()).get();
        // then: l'email a bien été mis à jour
        assertEquals(fetched.getEmail(), fetchedUpdated.getEmail());
    }

}
