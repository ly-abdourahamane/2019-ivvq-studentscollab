package fr.univtlse3.m2dl.studentscollab.studentscollab.repository;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Evaluation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
    public Evaluation getByEvaluateurIdAndNoteCoursId(Long evaluateurId, Long noteCoursId);
    public List<Evaluation> getByNoteCoursId(Long noteCoursId);
    public List<Evaluation> getByEvaluateurId(Long evaluateurId);
}
