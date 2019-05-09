package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.MatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class InscriptionController {

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private InscriptionService inscriptionService ;


    @GetMapping("/inscription")
    public String addInscriptionForm(@RequestParam(value = "id_matiere") Long m,
                                     Model model) throws MatiereNotFoundException {
        model.addAttribute("matiere",m);
        return "ajouterInscription";
    }


    @PostMapping("/inscrire")
    public String inscrire(@RequestParam("idMatiere")Long  idMatiere,HttpSession session,Model model) throws MatiereNotFoundException {
        Matiere matiere = matiereService.findById(idMatiere);
        Etudiant etudiant = (Etudiant) session.getAttribute("etudiant");
        Inscription inscription = new Inscription(etudiant,matiere);
        inscriptionService.saveInscription(inscription);
        model.addAttribute("matiere",matiere);
        return "redirect:/matieres";

    }

}
