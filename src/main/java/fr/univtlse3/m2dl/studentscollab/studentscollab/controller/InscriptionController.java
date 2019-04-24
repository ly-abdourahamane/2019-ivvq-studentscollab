package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionForm;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.FormationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author abdou on 21/04/19.
 * @project studentscollab
 */

@Controller
@RequestMapping("api/v1/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private FormationService formationService;

    @GetMapping(value = "creer")
    public  String saveInscription(Model model) {

        List<Formation> formations = formationService.findAllFormations();
        InscriptionForm inscriptionForm = new InscriptionForm();

        model.addAttribute("inscriptionForm", inscriptionForm);
        model.addAttribute("formations", formations);

        return "inscriptionForm";
    }

    @PostMapping("valider")
    public String validerCreation(@RequestParam("email") String email, @RequestParam("formationId") Long formationId) {

        Etudiant etudiant = etudiantService.findEtudiantByEmail(email);
        Formation formation1 = formationService.findFormationById(formationId).get();

        inscriptionService.saveInscription(etudiant, formation1);

        return "redirect:/api/v1/etudiants";
    }
}
