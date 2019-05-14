package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Login;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;

import fr.univtlse3.m2dl.studentscollab.studentscollab.service.FormationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("api/v1/etudiants")
public class EtudiantController  {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    FormationService formationService;

    public EtudiantService getEtudiantService() {
        return etudiantService;
    }

    public void setEtudiantService(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping(value = "test")
    public List<Etudiant> findAllForTests() {

        return etudiantService.findAll();
    }

    @GetMapping("accueil")
    public String accueil() {
        return "index";
    }

    @GetMapping("/creer-compte")
    public String creerCompte(Model model) {
        model.addAttribute("etudiant", new Etudiant());

        return "creer-compte";
    }

    @PostMapping(value = "/valider")
    public String save(@ModelAttribute("etudiant") Etudiant etudiant) {
      etudiantService.save(etudiant);
      return "redirect:/api/v1/etudiants/connexion";
    }


    @GetMapping(value = "/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantService.findById(id).orElse(null);
        Set<Formation> formations = new HashSet<>();
        List<Long> formationIds = inscriptionService.findListFormationIdByEtudiantId(id);

        formationIds.forEach(idFormation -> formations.add(formationService.findFormationById(idFormation).orElse(null)));

        if(etudiant == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }

        model.addAttribute("etudiant", etudiant);
        model.addAttribute("formations", formations);

        return "profileEtudiant";
    }

    @GetMapping(value = "")
    public String findAll(Model model) {

        model.addAttribute("etudiants", etudiantService.findAll());
        return "etudiants";
    }

    @GetMapping("/connexion")
    public String connexionForm(Model model) {
        model.addAttribute("login", new Login());

        return "connexion";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute("login") Login login, HttpSession session) {
        String page = this.etudiantService.login(login.getEmail(), login.getMotDePasse());
        String mail = login.getEmail();
        Etudiant etudiant = etudiantService.findEtudiantByEmail(mail);
        session.setAttribute("etudiant", etudiant);
        return "redirect:/api/v1/etudiants/" + page;
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteEtudiant(@PathVariable("id") Long id, HttpSession httpSession) {
        Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
        if (etudiantSession == null || etudiantSession.getId() == null || !etudiantSession.getId().equals(id)) {
            return "redirect:/api/v1/etudiants/connexion";
        }

        Etudiant etudiant = etudiantService.findById(id).get();
        etudiantService.deleteEtudiant(etudiant);

        return "index";
    }

    @GetMapping(value = "/edit/{id}")
    public String editEtudiant(@PathVariable("id") Long id, Model model, HttpSession httpSession) {
        Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
        if (etudiantSession == null || etudiantSession.getId() == null || id == null) {
            return "redirect:/api/v1/etudiants/connexion";
        } else if (!etudiantSession.getId().equals(id)) {
            return "redirect:/api/v1/etudiants/";
        }

        Etudiant etudiant = etudiantService.findById(id).orElse(null);

        if(etudiant == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }

        model.addAttribute("etudiant", etudiant);
        model.addAttribute("etudiantSession", etudiantSession);

        return "updateEtudiant";
    }

    @PostMapping(value = "/update/{id}")
    public String update(@Valid Etudiant etudiant, @RequestParam(value = "etudiantSessionId", required = false) Long etudiantSessionId, HttpSession httpSession) {
        Etudiant etudiantSession = (Etudiant) httpSession.getAttribute("etudiant");
        Long sessionId = etudiantSessionId;
        if (sessionId == null) {
            sessionId = (etudiantSession != null) ? etudiantSession.getId() : null;
        }

        if (sessionId == null) {
            return "redirect:/api/v1/etudiants/connexion";
        } else if (etudiant.getId() == null || !sessionId.equals(etudiant.getId())) {
            return "redirect:/api/v1/etudiants/";
        }

        etudiantService.save(etudiant);
        return "redirect:/api/v1/etudiants/";
    }
}
