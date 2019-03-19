package fr.univtlse3.m2dl.studentscollab.studentscollab.controller;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/etudiant")
public class EtudiantController  {

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping(value = "")
    public Etudiant save(@Valid @RequestBody Etudiant etudiant, HttpServletRequest request) {
        String rootURL = getBaseUrlFromRequest(request);

        String url = rootURL + "/api/v1/etudiants/verification/";

        return etudiantService.save(etudiant, url);
    }

    @GetMapping(value = "/{id}")
    public Etudiant getById(@PathVariable Long id) {
        return etudiantService.findById(id);
    }

    @GetMapping(value = "")
    public List<Etudiant> findAll() {
        return etudiantService.findAll();
    }


    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Etudiant update(@Valid @RequestBody Etudiant etudiant){
        return etudiantService.update(etudiant);
    }

    @GetMapping(value = "/verification/{token}")
    public String validerEtudiant(@PathVariable String token){
        return  etudiantService.validateEtudiant(token);
    }


    private String getBaseUrlFromRequest(HttpServletRequest request){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
