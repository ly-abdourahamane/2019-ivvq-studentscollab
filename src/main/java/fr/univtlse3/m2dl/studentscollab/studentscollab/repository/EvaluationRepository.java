package fr.univtlse3.m2dl.studentscollab.studentscollab.repository;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Evaluation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
    public Evaluation getByEvaluateurIdAndNoteCoursId(Long evaluateurId, Long noteCoursId);
    public List<Evaluation> getByNoteCoursId(Long noteCoursId);
    public List<Evaluation> getByEvaluateurId(Long evaluateurId);
}
