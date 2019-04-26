package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author abdou on 21/04/19.
 * @project studentscollab
 */
@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription saveInscription(Inscription inscription) {
        if(inscription == null) {
            throw new IllegalArgumentException("inscription invalide");
        }

        return inscriptionRepository.save(inscription);
    }

    public List<Inscription> findIscriptionByFormationId(Long id) {
        return inscriptionRepository.findInscriptionByFormationId(id);
    }

    public InscriptionRepository getInscriptionRepository() {
        return inscriptionRepository;
    }

    public void setInscriptionRepository(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public List<Long> findListFormationIdByEtudiantId(Long id) {
        return inscriptionRepository.findListFormationIdByEtudiantId(id);
    }

    public void deleteById(Long id) {
        inscriptionRepository.deleteById(id);
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findById(Long id) {
        return inscriptionRepository.findById(id);
    }
}
