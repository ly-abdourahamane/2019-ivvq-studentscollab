package fr.univtlse3.m2dl.studentscollab.studentscollab.etudiant;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class EtudiantServiceTest {

    private EtudiantService etudiantService;

    @MockBean
    private EtudiantRepository etudiantRepository;

    private Etudiant etudiant;

    @Before
    public void setup() {
        etudiantService = new EtudiantService();
        etudiantService.setEtudiantRepository(etudiantRepository);
        etudiant = new Etudiant("do", "john", "joh@gmail.com", "845kh*$");
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un EtudiantService est de type PagingAndSortingRepository
        assertThat(etudiantService.getEtudiantRepository(), instanceOf(PagingAndSortingRepository.class));
    }

    @Test
    public void testSaveFromPagingAndSortingRepositoryIsInvokedWhenEtudiantIsSaved() {
        // given: un EtudiantService et un etudiant
        // when: la méthode save est invoquée
        etudiantService.save(etudiant);
        // then: la méthode save du EtudiantService associé est invoquée
        verify(etudiantService.getEtudiantRepository()).save(etudiant);
    }

    @Test
    public void testfindByIdRepositoryIsInvoked() {
        // given: un service note de l'étudiant
        when(etudiantService.findById(eq(etudiant.getId()))).thenReturn(Optional.ofNullable(etudiant));
        // when: la méthode findById est invoquée
        etudiantRepository.findById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(etudiantService.getEtudiantRepository()).findById(0L);
    }

    @Test
    public void testFindAllFromCrudRepositoryIsInvokedWhenFindAllActivite() {
        // given: un EtudiantService
        // when: la méthode findAll est invoquée
        etudiantService.findAll();
        // then: la méthode findAll du Repository associé est invoquée
        verify(etudiantService.getEtudiantRepository()).findAll();
    }


    @Test
    public void testLoginEtudiant() {
        //given: un etudiant repository
        //when: la méthode save est invoquée
        etudiantService.save(etudiant);
        //then: l'utilisateur ne peut pas se connecter tant qu'il n'a pas validé son inscription
        String result = etudiantService.login(etudiant.getEmail(), etudiant.getMotDePasse());
       Assert.assertTrue("L'étudiant s'est connecté"
                , result == "connexion");
    }
}
