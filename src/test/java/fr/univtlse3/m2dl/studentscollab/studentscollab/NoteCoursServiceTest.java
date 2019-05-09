package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.NoteCoursNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
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
public class NoteCoursServiceTest {

    private NoteCoursService ncService;

    @MockBean
    private NoteCoursRepository ncRepository;

    @MockBean
    private EtudiantRepository etudiantRepository;

    private Etudiant etu = new Etudiant("nom", "pre", "eeeee@gmail.com", "54654");
    private NoteCours ncExpected = new NoteCours(1L, "nouvelleNote", "contenuNote", etu);

    @Before
    public void setup() {
        ncService = new NoteCoursService();
        ncService.setNoteCoursRepository(ncRepository);
        ncService.setEtudiantRepository(etudiantRepository);
    }

    @Test
    public void testSave() {
        // given: une note de cours
        NoteCours nc = new NoteCours("titre","description", etu);
        // when: la méthode saveNote est invoquée
        ncService.saveNoteCours(nc);
        // then: la méthode save du NoteCoursRepository associé est invoquée
        verify(ncService.getNoteCoursRepository()).save(nc);
    }

    @Test
    public void testfindNoteCoursByIdRepositoryIsInvoked() throws NoteCoursNotFoundException {
        // given: un service note de cours
        when(ncRepository.findById(eq(ncExpected.getId()))).thenReturn(Optional.ofNullable(ncExpected));
        // when: la méthode findActiviteById est invoquée
        ncService.findNoteCoursById(ncExpected.getId());
        // then: la méthode findById du Repository associé est invoquée
        verify(ncService.getNoteCoursRepository()).findById(ncExpected.getId());
    }

    @Test
    public void testfindNoteCoursByIdNominal() throws NoteCoursNotFoundException {
        // given: un service note de cours
        when(ncRepository.findById(eq(ncExpected.getId()))).thenReturn(Optional.ofNullable(ncExpected));
        // when: la méthode findActiviteById est invoquée
        NoteCours ncActual  = ncService.findNoteCoursById(ncExpected.getId());
        // then: la méthode la note de cours est renvoyée
        assertEquals(ncExpected, ncActual);

    }

    @Test(expected = NoteCoursNotFoundException.class)
    public void testfindNoteCoursByIdNotFound() throws NoteCoursNotFoundException {
        // given: un service note de cours
        // when: la méthode findActiviteById est invoquée
        ncService.findNoteCoursById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(ncService.getNoteCoursRepository()).findById(0L);
    }

    @Test
    public void testFindAllRepositoryIsInvoked() {
        // given: un note de cours
        // when: la méthode findAll du service est appelé
        ncService.findAll();
        // then: la méthode findAll du Repository associé est invoquée
        verify(ncService.getNoteCoursRepository()).findAll();
    }

}
