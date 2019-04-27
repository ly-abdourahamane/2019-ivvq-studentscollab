package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.EvalType;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Evaluation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.EvalNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EvaluationRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EvaluationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class EvaluationServiceTest {

    private EtudiantService etudiantService;
    private NoteCoursService noteCoursService;
    private EvaluationService evaluationService;

    @MockBean
    private EtudiantRepository etudiantRepository;
    @MockBean
    private EvaluationRepository evalRepository;
    @MockBean
    private NoteCoursRepository noteCoursRepository;

    private Etudiant etudiant;
    private NoteCours noteCours;
    private Evaluation evaluation;

    @Before
    public void setup() {
        etudiantService = new EtudiantService();
        etudiantService.setEtudiantRepository(etudiantRepository);
        etudiant = new Etudiant("do", "john", "joh@gmail.com", "845kh*$");

        noteCoursService = new NoteCoursService();
        noteCoursService.setNoteCoursRepository(noteCoursRepository);
        noteCours = new NoteCours("a", "a");

        evaluationService = new EvaluationService();
        evaluationService.setEvaluationRepository(evalRepository);
        evaluation = new Evaluation(etudiant, noteCours, EvalType.LIKE);
    }

    @Test
    public void testTypeRepository() {
        // le Repository associé à un EvaluationService est de type CrudRepository
        assertThat(evaluationService.getEvaluationRepository(), instanceOf(CrudRepository.class));
    }

    @Test
    public void testSaveFromRepositoryIsInvokedWhenEvaluationIsSaved() {
        // given: un EvaluationService et une évaluation
        // when: la méthode save est invoquée
        evaluationService.saveEvaluation(evaluation);
        // then: la méthode save du EtudiantService associé est invoquée
        verify(evaluationService.getEvaluationRepository()).save(evaluation);
    }

    @Test
    public void testfindByIdRepositoryIsInvoked() throws EvalNotFoundException {
        // given: un service note de l'étudiant
        evaluationService.saveEvaluation(evaluation);
        when(evaluationService.findEvaluationById(eq(evaluation.getId()))).thenReturn(evaluation);
        // when: la méthode findById est invoquée
        evalRepository.findById(0L);
        // then: la méthode findById du Repository associé est invoquée
        verify(evaluationService.getEvaluationRepository()).findById(0L);
    }

    @Test
    public void testFindAllFromCrudRepositoryIsInvokedWhenFindAllActivite() {
        // given: un EtudiantServiceisLike ? EvalType.LIKE : EvalType.DISLIKE
        // when: la méthode findAll est invoquée
        evaluationService.findAll();
        // then: la méthode findAll du Repository associé est invoquée
        verify(evaluationService.getEvaluationRepository()).findAll();
    }

}
