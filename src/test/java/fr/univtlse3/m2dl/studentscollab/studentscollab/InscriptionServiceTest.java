package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.InscriptionNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.InscriptionRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionService;
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
public class InscriptionServiceTest {

    private InscriptionService inscriptionService;

    @MockBean
    private InscriptionRepository inscriptionRepository;

    private Inscription inscription = new Inscription();

    @Before
    public void setup() {
        inscriptionService = new InscriptionService();
        inscriptionService.setInscriptionRepository(inscriptionRepository);
    }

    @Test
    public void testSave() {
        Inscription insc = new Inscription();
        inscriptionService.saveInscription(insc);
        verify(inscriptionService.getInscriptionRepository()).save(insc);
    }

    @Test
    public void testfindByIdRepositoryInvoked() throws  InscriptionNotFoundException {
        when(inscriptionRepository.findById(eq(inscription.getId()))).thenReturn(Optional.ofNullable(inscription));
        inscriptionService.findById(inscription.getId());
        verify(inscriptionService.getInscriptionRepository()).findById(inscription.getId());
    }

    @Test
    public void testfindId() throws  InscriptionNotFoundException {
        when(inscriptionRepository.findById(eq(inscription.getId()))).thenReturn(Optional.ofNullable(inscription));
        Inscription insc  = inscriptionService.findById(inscription.getId());
        assertEquals(inscription, insc);

    }
}
