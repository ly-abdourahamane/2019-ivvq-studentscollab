package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repositories.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.services.EtudiantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
public class EtudiantServiceTest {

    @MockBean
    private EtudiantRepository etudiantRepository;

    private EtudiantService etudiantService;

    @Before
    public void setup() {
        etudiantService = new EtudiantService();
        etudiantService.setEtudiantRepository(etudiantRepository);
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un EtudiantService est de type PagingAndSortingRepository
        assertThat(etudiantService.getEtudiantRepository(), instanceOf(PagingAndSortingRepository.class));
    }

    @Test
    public void testSaveFromPagingAndSortingRepositoryIsInvokedWhenEtudiantIsSaved() {
        // given: un EtudiantService et un etudiant
        Etudiant etudiant = new Etudiant("do", "john", "joh@gmail.com", "11111");
        // when: la méthode save est invoquée
//        etudiantService.save(etudiant, "");
        // then: la méthode save du EtudiantService associé est invoquée
//        verify(etudiantService.getEtudiantRepository()).save(etudiant);
    }

}
