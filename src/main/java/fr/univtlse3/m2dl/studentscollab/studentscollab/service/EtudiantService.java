package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Optional<Etudiant> findById(Long id) {
        return etudiantRepository.findById(id);
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public Etudiant findEtudiantByEmail(String email) {
        return etudiantRepository.findEtudiantByEmail(email);
    }

    public Etudiant save(Etudiant etudiant) {

        if (etudiant == null) {
            throw new IllegalArgumentException();
        }

        Etudiant etudiantResult = etudiantRepository.save(etudiant);
        return etudiantResult;
    }

    public String login(String email, String motDePasse) {

        Etudiant etudiant = this.etudiantRepository.login(email, motDePasse);

       return (etudiant==null ? "connexion": "");
    }

    public EtudiantRepository getEtudiantRepository() {
        return etudiantRepository;
    }

    public void setEtudiantRepository(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }
}
