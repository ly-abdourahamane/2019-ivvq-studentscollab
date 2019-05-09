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
        InscriptionToMatiere insc = new InscriptionToMatiere();
        inscriptionToMatiereService.saveInscription(insc);
        verify(inscriptionToMatiereService.getInscriptionToMatiereRepository()).save(insc);
    }

    @Test
    public void testfindByIdRepositoryInvoked() throws InscriptionToMatiereNotFoundException {
        when(inscriptionToMatiereRepository.findById(eq(inscriptionToMatiere.getId()))).thenReturn(Optional.ofNullable(inscriptionToMatiere));
        inscriptionToMatiereService.findById(inscriptionToMatiere.getId());
        verify(inscriptionToMatiereService.getInscriptionToMatiereRepository()).findById(inscriptionToMatiere.getId());
    }

    @Test
    public void testfindId() throws InscriptionToMatiereNotFoundException {
        when(inscriptionToMatiereRepository.findById(eq(inscriptionToMatiere.getId()))).thenReturn(Optional.ofNullable(inscriptionToMatiere));
        InscriptionToMatiere insc  = inscriptionToMatiereService.findById(inscriptionToMatiere.getId());
        assertEquals(inscriptionToMatiere, insc);

    }
}
