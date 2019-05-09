package fr.univtlse3.m2dl.studentscollab.studentscollab.service;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionToMatiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.InscriptionToMatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.InscriptionToMatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InscriptionToMatiereService {

    @Autowired
    private InscriptionToMatiereRepository inscriptionToMatiereRepository;

    public void setInscriptionToMatiereRepository(InscriptionToMatiereRepository inscriptionToMatiereRepository) {
        this.inscriptionToMatiereRepository = inscriptionToMatiereRepository;
    }

    public InscriptionToMatiereRepository getInscriptionToMatiereRepository() {
        return inscriptionToMatiereRepository;
    }

    public InscriptionToMatiere saveInscription(InscriptionToMatiere inscriptionToMatiere){
        if(inscriptionToMatiere == null)
            throw new IllegalArgumentException();
        inscriptionToMatiere = inscriptionToMatiereRepository.save(inscriptionToMatiere);
        return inscriptionToMatiere;
    }


    public InscriptionToMatiere findById(Long l) throws InscriptionToMatiereNotFoundException {
        Optional<InscriptionToMatiere> inscription = inscriptionToMatiereRepository.findById(l);
        if (!inscription.isPresent()){
            throw new InscriptionToMatiereNotFoundException("L'inscription n°"+l+" n'existe pas");
        }
        return inscription.get();
    }
}
