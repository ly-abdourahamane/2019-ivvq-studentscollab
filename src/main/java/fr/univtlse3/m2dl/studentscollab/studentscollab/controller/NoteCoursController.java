package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.*;

import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.MatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.NoteCoursNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.EvalNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.CommentaireService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EvaluationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("api/v1")
public class NoteCoursController {

    @Autowired
    private NoteCoursService noteCoursService;

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private CommentaireService commentaireService;

    @GetMapping("/cours/new")
    public String addCours(Model model, HttpSession httpSession) {
        NoteCours noteCours = new NoteCours();
        noteCours.setRedacteur((Etudiant) httpSession.getAttribute("etudiant"));
        model.addAttribute("noteCours", noteCours);
        model.addAttribute("matieres", matiereService.findAllMatieres());
        return "noteform";
    }

    @GetMapping("/cours/all")
    public String coursAll(Model model) {
        model.addAttribute("liste", noteCoursService.findAll());
        return "notes";
    }

    @GetMapping("/cours/{id}")
    public String cours(Model model, @PathVariable("id") Long id) {
        NoteCours noteCours = null;
        try {
            noteCours = noteCoursService.findNoteCoursById(id);
        } catch (NoteCoursNotFoundException e) {
            return "error";
        }
        model.addAttribute("noteCours", noteCours);
        Commentaire c=new Commentaire();
        noteCours.getCommentaires().add(c);
        model.addAttribute("nouveauCommentaire", c);
        return "detail_note";
    }

    @PostMapping("/cours/new")
    public String savecours(@ModelAttribute("noteCours") NoteCours nc, @RequestParam("matiere") Long m, HttpSession session) {
        Etudiant etudiant = (Etudiant) session.getAttribute("etudiant");
        nc.setRedacteur(etudiant);
        try {
            nc.setMatiere(matiereService.findById(m));
        } catch (MatiereNotFoundException e) {
            return "error";
        }
        NoteCours noteCours = noteCoursService.saveNoteCours(nc);
        return "note_ajoutee";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addcommentaire")
    public String saveCommentaire(@ModelAttribute("noteCours") NoteCours noteCours) {
        this.commentaireService.addCommentaires(noteCours);
        return "redirect:/cours/all";
    }

    @GetMapping("/cours/like")
    public String coursEvaluerLike(@ModelAttribute("noteCours") NoteCours nc, @ModelAttribute("etudiant") Etudiant etudiant, Model model) {
        try {
            Evaluation savedEval = evaluationService.findEvaluationByEtudiantAndNoteCours(etudiant.getId(), nc.getId());
            if (savedEval == null) {
                // crée le LIKE
                savedEval = new Evaluation(etudiant, nc, EvalType.LIKE);
                evaluationService.saveEvaluation(savedEval);

                nc.setNbLike(nc.getNbLike() + 1);
                noteCoursService.saveNoteCours(nc);
            } else {
                if (savedEval.getType() == EvalType.LIKE) {
                    // recliquer sur LIKE annule le LIKE
                    evaluationService.delete(savedEval.getId());

                    nc.setNbLike(nc.getNbLike() - 1);
                    noteCoursService.saveNoteCours(nc);
                } else {
                    // annule le DISLIKE et crée le LIKE
                    savedEval.setType(EvalType.LIKE);
                    evaluationService.saveEvaluation(savedEval);

                    nc.setNbLike(nc.getNbLike() + 1);
                    nc.setNbDislike(nc.getNbDislike() - 1);
                    noteCoursService.saveNoteCours(nc);
                }
            }
        } catch (EvalNotFoundException e) {
            e.printStackTrace();
        }
        return "note_ajoutee";
    }

    @GetMapping("/cours/dislike")
    public String coursEvaluerDislike(@ModelAttribute("noteCours") NoteCours nc, @ModelAttribute("etudiant") Etudiant etudiant, Model model) {
        try {
            Evaluation savedEval = evaluationService.findEvaluationByEtudiantAndNoteCours(etudiant.getId(), nc.getId());
            if (savedEval == null) {
                // crée le DISLIKE
                savedEval = new Evaluation(etudiant, nc, EvalType.DISLIKE);
                evaluationService.saveEvaluation(savedEval);

                nc.setNbDislike(nc.getNbDislike() + 1);
                noteCoursService.saveNoteCours(nc);
            } else {
                if (savedEval.getType() == EvalType.DISLIKE) {
                    // recliquer sur DISLIKE annule le DISLIKE
                    evaluationService.delete(savedEval.getId());

                    nc.setNbDislike(nc.getNbDislike() - 1);
                    noteCoursService.saveNoteCours(nc);
                } else {
                    // annule le LIKE et crée le DISLIKE
                    savedEval.setType(EvalType.DISLIKE);
                    evaluationService.saveEvaluation(savedEval);

                    nc.setNbLike(nc.getNbLike() - 1);
                    nc.setNbDislike(nc.getNbDislike() + 1);
                    noteCoursService.saveNoteCours(nc);
                }
            }
        } catch (EvalNotFoundException e) {
            e.printStackTrace();
        }
        return "note_ajoutee";
    }
}
