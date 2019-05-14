package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Commentaire;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.NoteCoursNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.CommentaireRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.CommentaireService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommentaireServiceTest {

    private CommentaireService commentaireService;

    @MockBean
    private CommentaireRepository commentaireRepository;

    @MockBean
    private NoteCoursRepository noteCoursRepository;

    private NoteCours noteCours = new NoteCours(1L, "nouvelleNote", "contenuNote");
    private List<Commentaire> listeCommentairesExpected;
    private Commentaire commentaire1;
    private Commentaire commentaire2;


    @Before
    public void setup() {
        commentaireService = new CommentaireService();
        commentaireService.setCommentaireRepository(commentaireRepository);
        listeCommentairesExpected = new ArrayList<>();
        commentaire1 = new Commentaire("Commentaire1");
        commentaire2 = new Commentaire("Commentaire2");
        commentaire1.setId(1L);
        commentaire1.setId(2L);
        listeCommentairesExpected.add(commentaire1);
        listeCommentairesExpected.add(commentaire2);
        noteCours.setCommentaires(listeCommentairesExpected);
        noteCoursRepository.save(noteCours);
    }

    @Test
    public void testFindCommentaireById() throws NoteCoursNotFoundException {
        // given: un repository commentaire
        when(commentaireRepository.findById(eq(commentaire1.getId()))).thenReturn(Optional.ofNullable(commentaire1));
        // when: la méthode findCommentaireById est invoquée
        Optional<Commentaire> commentaireActual = commentaireService.findCommentaireById(commentaire1.getId());
        // then: le commentaire est renvoyé
        assertEquals(commentaire1, commentaireActual.get());
    }

    @Test
    public void testSave() {
        // given: un commentaire
        Commentaire c = new Commentaire("mon commentaire");
        // when: la méthode saveCommentaire est invoquée
        commentaireService.saveCommentaire(c);
        // then: la méthode save du repository associé est invoquée
        verify(commentaireService.getCommentaireRepository()).save(c);
    }

    @Test
    public void testAddCommentaire() {
        // given: un commentaire
        // when: la méthode addCommentaires est invoquée
        commentaireService.addCommentaires(noteCours);
        // then: la méthode save du repository associé est invoquée
        verify(commentaireService.getCommentaireRepository()).save(commentaire1);
        verify(commentaireService.getCommentaireRepository()).save(commentaire2);
    }
}