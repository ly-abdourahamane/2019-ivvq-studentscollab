package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MatiereController {

    @Autowired
    MatiereService matiereService;

    @GetMapping("/matieres")
    public String matieres(Model model){
        List<Matiere> matieres = matiereService.findAllMatieres();
        model.addAttribute("matieres",matieres);
        return "matieres";
    }

    @GetMapping("/matiere/{id}")
    public String matiere(@PathVariable Long id, Model model){
        Matiere matiere = matiereService.findById(id);
        model.addAttribute("matiere",matiere);
        return "matiere";
    }

    @GetMapping("/matiere/new")
    public String addMatiere(Model model){
        model.addAttribute("matiereAj",new Matiere());
        return "matiereForm";
    }

    @PostMapping("/matiere")
    public String matiereSubmit(@ModelAttribute("matiereAJ") Matiere matiere, Model model) {
        Matiere m = matiereService.saveMatiere(matiere);
        model.addAttribute("matiere",m);
        return "matiere";
    }


    public void setMatiereService(MatiereService matiereService) {
        this.matiereService = matiereService;
    }
}
