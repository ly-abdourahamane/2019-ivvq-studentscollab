package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.EvalType;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Evaluation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.EvalNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EvaluationRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EvaluationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EvaluationServiceTest {

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
        evaluationService = new EvaluationService();

        evaluationService.setEtudiantRepository(etudiantRepository);
        etudiant = new Etudiant("do", "john", "joh@gmail.com", "845kh*$");

        evaluationService.setNoteCoursRepository(noteCoursRepository);
        noteCours = new NoteCours("a", "a");

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
    public void testFindAllFromCrudRepositoryIsInvokedWhenFindAllActivite() {
        // given: un EtudiantServiceisLike ? EvalType.LIKE : EvalType.DISLIKE
        // when: la méthode findAll est invoquée
        evaluationService.findAll();
        // then: la méthode findAll du Repository associé est invoquée
        verify(evaluationService.getEvaluationRepository()).findAll();
    }

}
