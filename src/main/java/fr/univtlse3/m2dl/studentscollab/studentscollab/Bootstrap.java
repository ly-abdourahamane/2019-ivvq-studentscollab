package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InitialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Bootstrap {

    @Autowired
    private InitialisationService initialisationService;

    @PostConstruct
    public void init() {
        try {
            initialisationService.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
