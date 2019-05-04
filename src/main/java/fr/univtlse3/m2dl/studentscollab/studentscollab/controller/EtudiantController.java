package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Login;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/etudiants")
public class EtudiantController  {

    @Autowired
    private EtudiantService etudiantService;

    public EtudiantService getEtudiantService() {
        return etudiantService;
    }

    public void setEtudiantService(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping(value = "/test")
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

        if(etudiant == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }
        model.addAttribute("etudiant", etudiant);

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
    public String login(@ModelAttribute("login") Login login) {
        String page = this.etudiantService.login(login.getEmail(), login.getMotDePasse());

        return "redirect:/api/v1/etudiants/" + page;
    }

    @PostMapping(value = "/delete")
    public String deleteEtudiant(@ModelAttribute("etudiant") Etudiant etudiant) {
        etudiantService.deleteEtudiant(etudiant);

        return "index";
    }

    @GetMapping(value = "/update/{id}")
    public String updatePage(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantService.findById(id).orElse(null);

        if(etudiant == null) {
            model.addAttribute("customMessage", "Impossible. Id non valide");
            return "error";
        }
        model.addAttribute("etudiant", etudiant);

        return "updateEtudiant";
    }

    @GetMapping(value = "/update")
    public String updateEtudiant(@ModelAttribute("id") Long id, @ModelAttribute("etu") Etudiant etu, Model model) {
        Etudiant etudiant = etudiantService.findById(id).get();

        etudiant.setNom(etu.getNom());
        etudiant.setPrenom(etu.getPrenom());
        etudiant.setEmail(etu.getEmail());

        etudiantService.save(etudiant);
        return "redirect:/api/v1/etudiants/" + etudiant.getId();
    }
}
