package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.EvalType;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Evaluation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.EvalNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public EvaluationRepository getEvaluationRepository() {return evaluationRepository;}
    public void setEvaluationRepository(EvaluationRepository evaluationRepository) {this.evaluationRepository = evaluationRepository;}

    public Evaluation saveEvaluation(Evaluation n) {
        return this.evaluationRepository.save(n);
    }

    public Evaluation findEvaluationById(Long id) throws EvalNotFoundException {
        Optional<Evaluation> nc = this.evaluationRepository.findById(id);
        if (!nc.isPresent()){
            throw new EvalNotFoundException("L'eval avec l'id n°"+id+" n'existe pas");
        }
        return nc.get();
    }

    public Evaluation findEvaluationByEtudiantAndNoteCours(Long etudiantId, Long noteCoursId) {
        return this.evaluationRepository.getByEvaluateurIdAndNoteCoursId(etudiantId, noteCoursId);
    }

    public Iterable<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    public void delete(Long id) throws EvalNotFoundException {
        Optional<Evaluation> eval = this.evaluationRepository.findById(id);
        if (!eval.isPresent()){
            throw new EvalNotFoundException("L'eval avec l'id n°"+id+" n'existe pas");
        } else {
            this.evaluationRepository.delete(eval.get());
        }
    }
}
