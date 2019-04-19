package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialisationService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    private Matiere matiere1,matiere2,matiere3;

    private Etudiant abdourahamane;
    private Etudiant meriem;
    private Etudiant maxime;
    private Etudiant alexia;



    public  void init() {
        initEtudiants();
        initMatieres();
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
}

