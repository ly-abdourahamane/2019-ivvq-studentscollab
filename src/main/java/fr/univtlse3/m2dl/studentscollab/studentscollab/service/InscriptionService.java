package fr.univtlse3.m2dl.studentscollab.studentscollab.service;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.InscriptionNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InscriptionService{

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public void setInscriptionRepository(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public InscriptionRepository getInscriptionRepository() {
        return inscriptionRepository;
    }

    public Inscription saveInscription(Inscription inscription){
        if(inscription == null)
            throw new IllegalArgumentException();
        inscription = inscriptionRepository.save(inscription);
        return inscription;
    }


    public Inscription findById(Long l) throws InscriptionNotFoundException {
        Optional<Inscription> inscription = inscriptionRepository.findById(l);
        if (!inscription.isPresent()){
            throw new InscriptionNotFoundException("L'inscription n°"+l+" n'existe pas");
        }
        return inscription.get();
    }
}
