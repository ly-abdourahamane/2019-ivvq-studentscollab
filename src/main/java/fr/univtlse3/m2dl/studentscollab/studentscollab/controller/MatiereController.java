package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.MatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MatiereController {

    @Autowired
    MatiereService matiereService;

    @Autowired
    EtudiantService etudiantService;

    @GetMapping("/matieres")
    public String matieres(Model model, HttpSession session){
        List<Matiere> matieres = matiereService.findAllMatieres();
        model.addAttribute("matieres",matieres);
        return "matieres";
    }

    @GetMapping("/matiere")
    public String matiere(@RequestParam(value = "id_etudiant")Long id_etudiant,
                          @RequestParam(value = "id_matiere")Long id_matiere, Model model) {
        Matiere matiere = null;
        try {
            matiere = matiereService.findById(id_matiere);
        } catch (MatiereNotFoundException e) {
            return "error";
        }
        if(etudiantService.estInscrit(id_matiere,id_etudiant)){
            model.addAttribute("matiere",matiere);
            return "matiere";
        }
        return "redirect:/inscription?id_matiere="+id_matiere;
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
}
