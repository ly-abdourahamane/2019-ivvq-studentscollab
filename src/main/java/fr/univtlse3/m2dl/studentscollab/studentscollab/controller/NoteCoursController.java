package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.EvalType;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Evaluation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;

import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.EvalNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.NoteCoursNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EvaluationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteCoursController {

    @Autowired
    private NoteCoursService ncs;

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/cours/new")
    public String addCours(Model model) {
        model.addAttribute("noteCours", new NoteCours());
        return "noteform";
    }

    @GetMapping("/cours/all")
    public String coursAll(Model model) {
        model.addAttribute("liste", ncs.findAll());
        return "notes";
    }

    @PostMapping("/cours")
    public String coursSubmit(@ModelAttribute("noteCours") NoteCours nc, Long id, Model model) {
        NoteCours noteCours = ncs.saveNoteCours(nc);
        return "note";
    }

    @PostMapping("/cours/{id}/like")
    public String coursEvaluerLike(@PathVariable Long id, @ModelAttribute("etudiant") Etudiant etudiant, Model model) {
        NoteCours nc = null;
        try {
            nc = ncs.findNoteCoursById(id);

            Evaluation savedEval = evaluationService.findEvaluationByEtudiantAndNoteCours(etudiant.getId(), id);
            if (savedEval == null) {
                // crée le LIKE
                savedEval = new Evaluation(etudiant, nc, EvalType.LIKE);
                evaluationService.saveEvaluation(savedEval);

                nc.setNbLike(nc.getNbLike() + 1);
                ncs.saveNoteCours(nc);
            } else {
                if (savedEval.getType() == EvalType.LIKE) {
                    // recliquer sur LIKE annule le LIKE
                    evaluationService.delete(savedEval.getId());

                    nc.setNbLike(nc.getNbLike() - 1);
                    ncs.saveNoteCours(nc);
                } else {
                    // annule le DISLIKE et crée le LIKE
                    savedEval.setType(EvalType.LIKE);
                    evaluationService.saveEvaluation(savedEval);

                    nc.setNbLike(nc.getNbLike() + 1);
                    nc.setNbDislike(nc.getNbDislike() - 1);
                    ncs.saveNoteCours(nc);
                }
            }
        } catch (NoteCoursNotFoundException | EvalNotFoundException e) {
            e.printStackTrace();
        }
        return "note";
    }

    @PostMapping("/cours/{id}/dislike")
    public String coursEvaluerDislike(@PathVariable Long id, @ModelAttribute("etudiant") Etudiant etudiant, Model model) {
        NoteCours nc = null;
        try {
            nc = ncs.findNoteCoursById(id);

            Evaluation savedEval = evaluationService.findEvaluationByEtudiantAndNoteCours(etudiant.getId(), id);
            if (savedEval == null) {
                // crée le DISLIKE
                savedEval = new Evaluation(etudiant, nc, EvalType.DISLIKE);
                evaluationService.saveEvaluation(savedEval);

                nc.setNbDislike(nc.getNbDislike() + 1);
                ncs.saveNoteCours(nc);
            } else {
                if (savedEval.getType() == EvalType.DISLIKE) {
                    // recliquer sur DISLIKE annule le DISLIKE
                    evaluationService.delete(savedEval.getId());

                    nc.setNbDislike(nc.getNbDislike() - 1);
                    ncs.saveNoteCours(nc);
                } else {
                    // annule le LIKE et crée le DISLIKE
                    savedEval.setType(EvalType.DISLIKE);
                    evaluationService.saveEvaluation(savedEval);

                    nc.setNbLike(nc.getNbLike() - 1);
                    nc.setNbDislike(nc.getNbDislike() + 1);
                    ncs.saveNoteCours(nc);
                }
            }
        } catch (NoteCoursNotFoundException | EvalNotFoundException e) {
            e.printStackTrace();
        }
        return "note";
    }
}
