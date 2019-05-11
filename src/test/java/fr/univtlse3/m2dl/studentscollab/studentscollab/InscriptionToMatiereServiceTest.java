package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionToMatiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.InscriptionToMatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.InscriptionToMatiereRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionToMatiereService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class InscriptionToMatiereServiceTest {

    private InscriptionToMatiereService inscriptionToMatiereService;

    @MockBean
    private InscriptionToMatiereRepository inscriptionToMatiereRepository;

    private InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere();

    @Before
    public void setup() {
        inscriptionToMatiereService = new InscriptionToMatiereService();
        inscriptionToMatiereService.setInscriptionToMatiereRepository(inscriptionToMatiereRepository);
    }

    @Test
    public void testSave() {
        // given: une inscriptionToMatiere
        InscriptionToMatiere insc = new InscriptionToMatiere();
        // when: la méthode saveInscription est invoquée
        inscriptionToMatiereService.saveInscription(insc);
        // then: la méthode save du InscriptionToMatiereRepository  est invoquée
        verify(inscriptionToMatiereService.getInscriptionToMatiereRepository()).save(insc);
    }

    @Test
    public void testfindByIdRepositoryInvoked() throws InscriptionToMatiereNotFoundException {
        // given: une inscriptionToMatiere
        when(inscriptionToMatiereRepository.findById(eq(inscriptionToMatiere.getId()))).thenReturn(Optional.ofNullable(inscriptionToMatiere));
        // when: la méthode findById est invoquée
        inscriptionToMatiereService.findById(inscriptionToMatiere.getId());
        // then: la méthode findById du InscriptionToMatiereRepository  est invoquée
        verify(inscriptionToMatiereService.getInscriptionToMatiereRepository()).findById(inscriptionToMatiere.getId());
    }

    @Test
    public void testfindByIdFound() throws InscriptionToMatiereNotFoundException {
        // given: un service inscriptionToMatiere
        when(inscriptionToMatiereRepository.findById(eq(inscriptionToMatiere.getId()))).thenReturn(Optional.ofNullable(inscriptionToMatiere));
        // when: la méthode findById est invoquée
        InscriptionToMatiere inscriptionToMatiereAct  = inscriptionToMatiereService.findById(inscriptionToMatiere.getId());
        // then: le id de inscriptionToMatiereAct est retourné
        assertEquals(inscriptionToMatiere, inscriptionToMatiereAct);

    }

    @Test(expected = InscriptionToMatiereNotFoundException.class)
    public void testfindByIdNotFound() throws InscriptionToMatiereNotFoundException {
        inscriptionToMatiereService.findById(0L);
        verify(inscriptionToMatiereService.getInscriptionToMatiereRepository()).findById(0L);
    }
}
