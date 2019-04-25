package fr.univtlse3.m2dl.studentscollab.studentscollab.inscription;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.InscriptionRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionService;
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
import static org.mockito.Mockito.*;

/**
 * @author abdou on 24/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
public class InscriptionServiceTest {

    private InscriptionService inscriptionService;

    @MockBean
    private InscriptionRepository inscriptionRepository;

    private Inscription inscription;

    @Before
    public void setup() {
        inscriptionService = new InscriptionService();
        inscriptionService.setInscriptionRepository(inscriptionRepository);

        Etudiant etudiant = new Etudiant("ly", "abdou", "abdourahamane.ly1@gmail.com", "123456");
        Formation formation = new Formation("informatique", "M2DL");
        inscription = new Inscription(etudiant, formation);
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un InscriptionService est de type PagingAndSortingRepository
        assertThat(inscriptionService.getInscriptionRepository(), instanceOf(PagingAndSortingRepository.class));
    }

    @Test
    public void testSaveInscription() {
        // given: une inscriptionService et une formation
        // when: la méthode save est invoquée
        inscriptionService.saveInscription(inscription);
        // then: la méthode save du inscriptionService associé est invoquée
        verify(inscriptionService.getInscriptionRepository()).save(inscription);
    }

    @Test
    public void testFindByIdRepositoryIsInvoked() {
        // given: un service
        when(inscriptionService.findById(eq(inscription.getId()))).thenReturn(Optional.ofNullable(inscription));
        // when: la méthode findById est invoquée
        inscriptionRepository.findById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(inscriptionService.getInscriptionRepository()).findById(0L);
    }

    @Test
    public void testFindAllFromPagingAndSortingRepositoryIsInvokedWhenFindAllActivite() {
        // given: un inscriptionService
        // when: la méthode findAll est invoquée
        inscriptionService.findAll();
        // then: la méthode findAll du Repository associé est invoquée
        verify(inscriptionService.getInscriptionRepository()).findAll();
    }

    @Test
    public void testFindByIdRepositoryIsInvokedd() {
        Optional<Inscription> inscriptionOptional = Optional.of(inscription);

        when(inscriptionRepository.findById(0L)).thenReturn(inscriptionOptional);
        inscriptionService.deleteById(inscription.getId());
        verify(inscriptionRepository, times(1)).deleteById(inscription.getId());
        
    }

}
