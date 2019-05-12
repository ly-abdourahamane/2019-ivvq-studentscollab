package fr.univtlse3.m2dl.studentscollab.studentscollab;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.MatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
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
public class MatiereServiceTest {

    private MatiereService matiereService;
    private Matiere matiere;

    @MockBean
    private MatiereRepository matiereRepository;

    @Before
    public void setup() {
        matiere = new Matiere("matiere1");
        matiereService = new MatiereService();
        matiereService.setMatiereRepository(matiereRepository);
    }

    @Test
    public void findAllMatieresTest() {
        // given: une matière
        // when: la méthode findAllMatieres du service est appelée
        matiereService.findAllMatieres();
        // then: la méthode findAll du MatiereRepository est invoquée
        verify(matiereService.getMatiereRepository()).findAll();
    }

    @Test
    public void testfindByIdRepositoryIsInvoked() throws MatiereNotFoundException {
        // given: un service matière
        when(matiereRepository.findById(eq(matiere.getId()))).thenReturn(Optional.ofNullable(matiere));
        // when: la méthode findById est invoquée
        matiereService.findById(matiere.getId());
        // then: la méthode findById du MatiereRepository est invoquée
        verify(matiereService.getMatiereRepository()).findById(matiere.getId());
    }

    @Test
    public void testfindByIdFound() throws MatiereNotFoundException {
        // given: un service matière
        when(matiereRepository.findById(eq(matiere.getId()))).thenReturn(Optional.ofNullable(matiere));
        // when: la méthode findById est invoquée
        Matiere matiereAct  = matiereService.findById(matiere.getId());
        // then: le id de matiere est retourné
        assertEquals(matiere, matiereAct);
    }

    @Test(expected = MatiereNotFoundException.class)
    public void testfindByIdNotFound() throws MatiereNotFoundException {
        matiereService.findById(0L);
        verify(matiereService.getMatiereRepository()).findById(0L);
    }

    @Test
    public void saveMatiereRepositoryInvokedIsTest() {
        // given: une matière matiere
        Matiere matiere = new Matiere("matiere1");
        // when: la méthode saveMatiere() est invoquée
        matiereService.saveMatiere(matiere);
        // then: la méthode save du MatiereRepository  est invoquée
        verify(matiereService.getMatiereRepository()).save(matiere);
    }
}