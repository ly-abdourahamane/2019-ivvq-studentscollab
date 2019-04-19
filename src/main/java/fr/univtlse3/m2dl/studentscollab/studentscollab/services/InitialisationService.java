package fr.univtlse3.m2dl.studentscollab.studentscollab.services;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialisationService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    private Etudiant abdourahamane;
    private Etudiant meriem;
    private Etudiant maxime;
    private Etudiant alexia;

    public  void init() {
        initEtudiants();
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

