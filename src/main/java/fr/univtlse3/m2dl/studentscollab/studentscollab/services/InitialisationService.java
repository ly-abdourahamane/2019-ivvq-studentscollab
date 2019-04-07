package fr.univtlse3.m2dl.studentscollab.studentscollab.services;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repositories.EtudiantRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
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
}

