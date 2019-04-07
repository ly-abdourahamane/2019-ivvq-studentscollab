package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Login;
import fr.univtlse3.m2dl.studentscollab.studentscollab.services.EtudiantService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("api/v1/etudiants")
@Getter
@Setter
public class EtudiantController  {

    @Autowired
    private EtudiantService etudiantService;

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
    public String save(@ModelAttribute("etudiant") Etudiant etudiant, HttpServletRequest request, Model model) {

        String rootURL = getBaseUrlFromRequest(request);

        String url = rootURL + "/api/v1/etudiants/verification/";

        Etudiant etud = etudiantService.save(etudiant, url);

       if (etud == null) {
           model.addAttribute("custoMessage", "Enregistremnet impossible");
           return "error";
       }

       return "verification-email";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Etudiant etudiant = etudiantService.findById(id);

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

    @GetMapping(value = "/verification/{token}")
    public String validerEtudiant(@PathVariable String token, Model model){
        model.addAttribute("login", new Login());

        return etudiantService.validateEtudiant(token);
    }

    private String getBaseUrlFromRequest(HttpServletRequest request){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
