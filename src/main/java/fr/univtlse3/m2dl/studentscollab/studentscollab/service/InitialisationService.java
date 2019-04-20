package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.FormationRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialisationService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    FormationRepository formationRepository;

    private Matiere matiere1,matiere2,matiere3;

    private Etudiant abdourahamane;
    private Etudiant meriem;
    private Etudiant maxime;
    private Etudiant alexia;

    private Formation dl, bio, ihm, economie;

    public  void init() {
        initEtudiants();
        initMatieres();
        initFormations();
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
        abdourahamane = new Etudiant("ly", "abdou", "abdourahamane.ly1@gmail.com","123456");
        abdourahamane.setEstValide(true);
        etudiantRepository.save(abdourahamane);

        meriem = new Etudiant("ferouj", "meriem", "meriem@gmail.com","123456");
        etudiantRepository.save(meriem);

        maxime = new Etudiant("rouillon", "maxime", "maxime@gmail.com","123456");
        etudiantRepository.save(maxime);

        alexia = new Etudiant("fernandes", "alexia", "alexia@gmail.com","123456");
        etudiantRepository.save(alexia);
    }

    private void initFormations() {
        dl = new Formation("informatique", "M2DL");
        bio = new Formation("Biologie", "Licence 2");
        ihm = new Formation("Informatique", "M1IHM");
        economie = new Formation("Economie", "Licence3");

        formationRepository.save(dl);
        formationRepository.save(bio);
        formationRepository.save(ihm);
        formationRepository.save(economie);
    }

    public EtudiantRepository getEtudiantRepository() {
        return etudiantRepository;
    }

    public void setEtudiantRepository(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public Etudiant getAbdourahamane() {
        return abdourahamane;
    }

    public void setAbdourahamane(Etudiant abdourahamane) {
        this.abdourahamane = abdourahamane;
    }

    public Etudiant getMaxime() {
        return maxime;
    }

    public void setMaxime(Etudiant maxime) {
        this.maxime = maxime;
    }

    public Etudiant getAlexia() {
        return alexia;
    }

    public void setAlexia(Etudiant alexia) {
        this.alexia = alexia;
    }

    public Etudiant getMeriem() {
        return meriem;
    }

    public void setMeriem(Etudiant meriem) {
        this.meriem = meriem;
    }

    public FormationRepository getFormationRepository() {
        return formationRepository;
    }

    public void setFormationRepository(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    public Matiere getMatiere1() {
        return matiere1;
    }

    public void setMatiere1(Matiere matiere1) {
        this.matiere1 = matiere1;
    }

    public Matiere getMatiere2() {
        return matiere2;
    }

    public void setMatiere2(Matiere matiere2) {
        this.matiere2 = matiere2;
    }

    public Matiere getMatiere3() {
        return matiere3;
    }

    public void setMatiere3(Matiere matiere3) {
        this.matiere3 = matiere3;
    }

    public Formation getDl() {
        return dl;
    }

    public void setDl(Formation dl) {
        this.dl = dl;
    }

    public Formation getBio() {
        return bio;
    }

    public void setBio(Formation bio) {
        this.bio = bio;
    }

    public Formation getIhm() {
        return ihm;
    }

    public void setIhm(Formation ihm) {
        this.ihm = ihm;
    }

    public Formation getEconomie() {
        return economie;
    }

    public void setEconomie(Formation economie) {
        this.economie = economie;
    }
}

