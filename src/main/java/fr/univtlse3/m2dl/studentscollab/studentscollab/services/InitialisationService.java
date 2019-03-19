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
        etudiantRepository.save(abdourahamane);
    }
}

