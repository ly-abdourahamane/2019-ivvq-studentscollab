package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Commentaire;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;

import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.NoteCoursNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.CommentaireService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteCoursController {

    @Autowired
    private NoteCoursService noteCoursService;

    @Autowired
    private CommentaireService commentaireService;

    @GetMapping("/cours/new")
    public String addCours(Model model) {
        model.addAttribute("noteCours", new NoteCours());
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
    public String savecours(@ModelAttribute("noteCours") NoteCours nc, Long id, Model model) {
        NoteCours noteCours = noteCoursService.saveNoteCours(nc);
        return "note_ajoutee";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addcommentaire")
    public String saveCommentaire(/*final BindingResult bindingResult, */@ModelAttribute("noteCours") NoteCours noteCours) {
        this.commentaireService.addCommentaires(noteCours);
        return "redirect:/cours/all";
    }
}
