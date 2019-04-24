package fr.univtlse3.m2dl.studentscollab.studentscollab.formation;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.FormationRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.FormationService;
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
 * @author abdou on 20/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
public class FormationServiceTest {

    private FormationService formationService;

    @MockBean
    private FormationRepository formationRepository;

    private Formation formation;

    @Before
    public void setup() {
        formationService = new FormationService();
        formationService.setFormationRepository(formationRepository);

        formation = new Formation("dev log", "M2");
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un FormationService est de type PagingAndSortingRepository
        assertThat(formationService.getFormationRepository(), instanceOf(PagingAndSortingRepository.class));
    }

    @Test
    public void testSaveFormation() {
        // given: un FormationService et une formation
        // when: la méthode save est invoquée
        formationService.saveFormation(formation);
        // then: la méthode save du FormationService associé est invoquée
        verify(formationService.getFormationRepository()).save(formation);
    }

    @Test
    public void testFindByIdRepositoryIsInvoked() {
        // given: un service
        when(formationService.findFormationById(eq(formation.getId()))).thenReturn(Optional.ofNullable(formation));
        // when: la méthode findById est invoquée
        formationRepository.findById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(formationService.getFormationRepository()).findById(0L);
    }


    @Test
    public void testFindByIdRepositoryIsInvokedd() {
        Optional<Formation> formationOptional = Optional.of(formation);
        doNothing().when(formationRepository).deleteById(0L);
        /*
        when(formationRepository.findById(0L)).thenReturn(formationOptional);
        formationService.deleteFormationById(formation.getId());
        verify(formationRepository, times(1)).deleteById(formation.getId());
        */
    }

    @Test
    public void testFindAllFromPagingAndSortingRepositoryIsInvokedWhenFindAllActivite() {
        // given: un FormationService
        // when: la méthode findAllFormation est invoquée
        formationService.findAllFormations();
        // then: la méthode findAll du Repository associé est invoquée
        verify(formationService.getFormationRepository()).findAll();
    }
}
