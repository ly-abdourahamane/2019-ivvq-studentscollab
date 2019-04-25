package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Inscription;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author abdou on 20/04/19.
 * @project studentscollab
 */

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private InscriptionService inscriptionService;

    public Formation saveFormation(Formation formation) {

        if (formation == null) {
            throw new IllegalArgumentException("argument non valide");
        }

       return this.formationRepository.save(formation);
    }

    public void deleteFormationById(Long id) {
        findIscriptionByFormationId(id).forEach(inscription -> inscriptionService.deleteById(inscription.getId()));
        this.formationRepository.deleteById(id);
    }

    List<Inscription> findIscriptionByFormationId(Long id) {
        return inscriptionService.findIscriptionByFormationId(id);
    }

    public List<Formation> findAllFormations() {
        return this.formationRepository.findAll();
    }

    public Optional<Formation> findFormationById(Long id) {
        return formationRepository.findById(id);
    }

    public FormationRepository getFormationRepository() {
        return formationRepository;
    }

    public void setFormationRepository(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

}
