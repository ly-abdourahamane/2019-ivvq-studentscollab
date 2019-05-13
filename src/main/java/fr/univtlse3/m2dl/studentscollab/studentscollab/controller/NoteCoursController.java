package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.*;

import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.EvalNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.MatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.NoteCoursNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.EvalNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class NoteCoursController {

    @Autowired
    private NoteCoursService noteCoursService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private MatiereService matiereService;

    @GetMapping("/cours/new")
    public String addCours(Model model, HttpSession httpSession) {
        NoteCours noteCours = new NoteCours();
        Etudiant etudiant = (Etudiant) httpSession.getAttribute("etudiant");
        if (etudiant == null || etudiant.getId() == null) {
            return "redirect:/api/v1/etudiants/connexion";
        }
        model.addAttribute("noteCours", noteCours);
        Collection<Matiere> matieres = matiereService.findAllMatieres();
        model.addAttribute("matieres", matieres);
        return "noteform";
    }

    @GetMapping("/cours/all")
    public String coursAll(Model model, HttpSession httpSession) {
        model.addAttribute("liste", noteCoursService.findAll());
        return "notes";
    }

    @GetMapping("/cours/{id}")
    public String cours(Model model, @PathVariable("id") Long id, HttpSession httpSession) {
        NoteCours noteCours = null;
        try {
            noteCours = noteCoursService.findNoteCoursById(id);
        } catch (NoteCoursNotFoundException e) {
            return "error";
        }
        Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
        if (etudiantSession == null || etudiantSession.getId() == null) {
            return "redirect:/api/v1/etudiants/connexion";
        }

        model.addAttribute("noteCours", noteCours);
        model.addAttribute("etudiant", etudiantSession);
        Commentaire c=new Commentaire();
        noteCours.getCommentaires().add(c);
        model.addAttribute("nouveauCommentaire", c);
        return "detail_note";
    }

    @PostMapping("/cours/new")
    public String savecours(@ModelAttribute("noteCours") NoteCours nc, Model model, HttpSession httpSession) {
        try {
            Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
            if (etudiantSession == null || etudiantSession.getId() == null) {
                return "redirect:/api/v1/etudiants/connexion";
            }
            model.addAttribute("etudiant", etudiantSession);
            nc.setRedacteur(etudiantSession);
            noteCoursService.saveNoteCours(nc);
            return "note_ajoutee";
        } catch (Exception e) {
            return "error";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addcommentaire")
    public String saveCommentaire(@ModelAttribute("noteCours") NoteCours noteCours, Model model, HttpSession httpSession) {
        this.commentaireService.addCommentaires(noteCours);
        Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
        if (etudiantSession == null || etudiantSession.getId() == null) {
            return "redirect:/api/v1/etudiants/connexion";
        }
        model.addAttribute("etudiant", etudiantSession);
        return "redirect:/cours/all";
    }

    @GetMapping("/cours/like/{id}")
    public String coursEvaluerLike(@PathVariable("id") Long id, @RequestParam(value = "etudiantId", required = false) Long etudiantId, Model model, HttpSession httpSession) {
        try {
            NoteCours nc = noteCoursService.findNoteCoursById(id);
            Etudiant etudiant = null;
            if (etudiantId != null) {
                etudiant = etudiantService.findById(etudiantId).get();
            } else {
                Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
                if (etudiantSession == null || etudiantSession.getId() == null) {
                    return "redirect:/api/v1/etudiants/connexion";
                }
                etudiant = etudiantSession;
            }
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
            model.addAttribute(nc);
            model.addAttribute(etudiant);
        } catch (EvalNotFoundException | NoteCoursNotFoundException e) {
            e.printStackTrace();
        }
        return "note_ajoutee";
    }

    @GetMapping("/cours/dislike/{id}")
    public String coursEvaluerDislike(@PathVariable("id") Long id, @RequestParam(value = "etudiantId", required = false) Long etudiantId, Model model, HttpSession httpSession) {
        try {
            NoteCours nc = noteCoursService.findNoteCoursById(id);
            Etudiant etudiant = null;
            if (etudiantId != null) {
                etudiant = etudiantService.findById(etudiantId).get();
            } else {
                Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
                if (etudiantSession == null || etudiantSession.getId() == null) {
                    return "redirect:/api/v1/etudiants/connexion";
                }
                etudiant = etudiantSession;
            }
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
            model.addAttribute(nc);
            model.addAttribute(etudiant);
        } catch (EvalNotFoundException | NoteCoursNotFoundException e) {
            e.printStackTrace();
        }
        return "note_ajoutee";
    }
}
