package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.*;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private NoteCoursRepository noteCoursRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public EvaluationRepository getEvaluationRepository() {return evaluationRepository;}
    public void setEvaluationRepository(EvaluationRepository evaluationRepository) {this.evaluationRepository = evaluationRepository;}

    public NoteCoursRepository getNoteCoursRepository() {
        return this.noteCoursRepository;
    }
    public void setNoteCoursRepository(NoteCoursRepository noteCoursRepository) { this.noteCoursRepository = noteCoursRepository; }

    public InscriptionRepository getInscriptionRepository() {return inscriptionRepository;}
    public void setInscriptionRepository( InscriptionRepository inscriptionRepository) {this.inscriptionRepository = inscriptionRepository;}

    public Optional<Etudiant> findById(Long id) {
        return etudiantRepository.findById(id);
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public Etudiant findEtudiantByEmail(String email) {
        return etudiantRepository.findEtudiantByEmail(email);
    }

    public Etudiant save(Etudiant etudiant) {

        if (etudiant == null) {
            throw new IllegalArgumentException();
        }

        Etudiant etudiantResult = etudiantRepository.save(etudiant);
        return etudiantResult;
    }

    public String login(String email, String motDePasse) {

        Etudiant etudiant = this.etudiantRepository.login(email, motDePasse);

        return (etudiant==null ? "connexion": "");
    }

    public void deleteEtudiant(Etudiant etudiant) {
        // suppression des notes de cours de l'étudiant
        List<NoteCours> notes = noteCoursRepository.getByRedacteurId(etudiant.getId());
        for (NoteCours note: notes) {
            // suppression des évaluations de cette note
            List<Evaluation> evaluations = evaluationRepository.getByNoteCoursId(note.getId());
            for (Evaluation evaluation: evaluations) {
                evaluationRepository.delete(evaluation);
            }
            noteCoursRepository.delete(note);
        }

        // suppression de ses évaluations à lui
        List<Evaluation> evaluations = evaluationRepository.getByEvaluateurId(etudiant.getId());
        for (Evaluation evaluation: evaluations) {
            int nbLike = evaluation.getNoteCours().getNbLike();
            int nbDislike = evaluation.getNoteCours().getNbDislike();
            // mise à jour du nb de like/dislike avant de supprimer
            evaluation.getNoteCours().setNbLike(evaluation.getType() == EvalType.LIKE ? nbLike - 1 : nbLike);
            evaluation.getNoteCours().setNbDislike(evaluation.getType() == EvalType.DISLIKE ? nbDislike - 1 : nbDislike);

            evaluationRepository.delete(evaluation);
        }

        List<Inscription> inscriptions = inscriptionRepository.findByEtudiantId(etudiant.getId());
        for (Inscription inscription: inscriptions) {
            inscriptionRepository.delete(inscription);
        }

        // suppression de l'étudiant
        etudiantRepository.delete(etudiant);
    }

    public boolean estInscrit(Long idMatiere, Long idEtudiant){
        Boolean inscrit = false;
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
        for (InscriptionToMatiere inscriptionToMatiere : etudiant.getInscriptionToMatieres()) {
            if(inscriptionToMatiere.getMatiere().getId() == idMatiere)
                inscrit = true;
        }
        return inscrit;
    }

    public EtudiantRepository getEtudiantRepository() {
        return etudiantRepository;
    }

    public void setEtudiantRepository(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }
}