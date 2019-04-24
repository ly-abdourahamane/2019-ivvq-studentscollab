package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author abdou on 20/04/19.
 * @project studentscollab
 */

@Controller
@RequestMapping("api/v1/formations")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping(value = "creer")
    public  String creerFormation(Model model) {
        model.addAttribute("formation", new Formation());
        return "formationForm";
    }

    @PostMapping("valider")
    public String validerCreation(@ModelAttribute("formation") Formation formation) {
        formationService.saveFormation(formation);
        return "redirect:/api/v1/formations";
    }

    @DeleteMapping("delete/{id}")
    public String deleteFormationById(@PathVariable Long id){

        formationService.deleteFormationById(id);
        return "redirect:/api/v1/formations";
    }

    @GetMapping("{id}")
    public String findById(@PathVariable Long id, Model model){
        Formation formation = formationService.findFormationById(id).orElse(null);
        model.addAttribute("formation", formation);

        return "formation";
    }

    @GetMapping(value = "")
    public String findAll(Model model) {

        model.addAttribute("formations", formationService.findAllFormations());
        return "formations";
    }
}
