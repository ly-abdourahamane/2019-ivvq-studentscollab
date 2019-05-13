package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionToMatiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.MatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionToMatiereService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class InscriptionToMatiereController {

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private InscriptionToMatiereService inscriptionToMatiereService;


    @GetMapping("/inscription")
    public String addInscriptionForm(@RequestParam(value = "id_matiere") Long m,
                                     Model model){
        String nomMetiere = null;
        try {
            nomMetiere = matiereService.findById(m).getNom();
        } catch (MatiereNotFoundException e) {
            return "error";
        }
        model.addAttribute("matiere",m);
        model.addAttribute("nomMetiere",nomMetiere);
        return "ajouterInscription";
    }


    @PostMapping("/inscrire")
    public String inscrire(@RequestParam("idMatiere")Long  idMatiere,HttpSession session,Model model){
        Matiere matiere = null;
        try {
            matiere = matiereService.findById(idMatiere);
        } catch (MatiereNotFoundException e) {
            return "error";
        }
        Etudiant etudiant = (Etudiant) session.getAttribute("etudiant");
        InscriptionToMatiere inscriptionToMatiere = new InscriptionToMatiere(etudiant,matiere);
        inscriptionToMatiereService.saveInscription(inscriptionToMatiere);
        model.addAttribute("matiere",matiere);
        return "redirect:/matieres";

    }

}
