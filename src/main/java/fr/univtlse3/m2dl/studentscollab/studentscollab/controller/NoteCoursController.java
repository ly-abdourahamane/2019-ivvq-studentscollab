package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;

import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteCoursController {

    @Autowired
    private NoteCoursService ncs;

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
}
