package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InitialisationService {

    @Autowired
    private MatiereRepository matiereRepository;

    private Matiere matiere1, matiere2, matiere3;

    private FormationService formationService;
    private InscriptionService inscriptionService;
    private EtudiantService etudiantService;

    @Autowired
    public InitialisationService(InscriptionService inscriptionService, FormationService formationService, EtudiantService etudiantService) {
        this.inscriptionService = inscriptionService;
        this.formationService = formationService;
        this.etudiantService = etudiantService;
    }

    private Etudiant abdourahamane;
    private Etudiant meriem;
    private Etudiant maxime;
    private Etudiant alexia;

    private Formation dl, bio, ihm, economie;
    private Inscription abdouEnDL, meriemEnDL, maximeEnDL, alexiaEnDL;

    public void run() {
        initMatieres();

        initEtudiants();
        initFormations();
        initInscriptions();
    }

    private void initMatieres() {
        matiere1 = new Matiere("matiere1");
        matiere2 = new Matiere("matiere2");
        matiere3 = new Matiere("matiere3");
        matiereRepository.save(matiere1);
        matiereRepository.save(matiere2);
        matiereRepository.save(matiere3);
    }

    private void initEtudiants() {
        abdourahamane = new Etudiant("ly", "abdou", "abdourahamane.ly1@gmail.com", "123456");
        abdourahamane.setEstValide(true);
        meriem = new Etudiant("ferouj", "meriem", "meriem@gmail.com", "123456");
        maxime = new Etudiant("rouillon", "maxime", "maxime@gmail.com", "123456");
        alexia = new Etudiant("fernandes", "alexia", "alexia@gmail.com", "123456");

        etudiantService.save(abdourahamane);
        etudiantService.save(meriem);
        etudiantService.save(maxime);
        etudiantService.save(alexia);
    }

    private void initFormations() {
        dl = formationService.saveFormation(new Formation("informatique", "M2DL"));
        bio = formationService.saveFormation(new Formation("Biologie", "Licence 2"));
        ihm = formationService.saveFormation(new Formation("Informatique", "M1IHM"));
        economie = formationService.saveFormation(new Formation("Economie", "Licence3"));
    }

    private void initInscriptions() {
        abdouEnDL = inscriptionService.saveInscription(new Inscription(abdourahamane, dl));
        abdouEnDL = inscriptionService.saveInscription(new Inscription(maxime, dl));
        abdouEnDL = inscriptionService.saveInscription(new Inscription(meriem, dl));
        abdouEnDL = inscriptionService.saveInscription(new Inscription(alexia, dl));

        abdouEnDL = inscriptionService.saveInscription(new Inscription(alexia, ihm));

        abdouEnDL = inscriptionService.saveInscription(new Inscription(abdourahamane, dl));
        abdouEnDL = inscriptionService.saveInscription(new Inscription(maxime, economie));
        abdouEnDL = inscriptionService.saveInscription(new Inscription(meriem, dl));
        abdouEnDL = inscriptionService.saveInscription(new Inscription(alexia, bio));

        abdouEnDL = inscriptionService.saveInscription(new Inscription(alexia, ihm));
    }

    public Etudiant getAbdourahamane() {
        return abdourahamane;
    }

    public Formation getDl() {
        return dl;
    }

}

