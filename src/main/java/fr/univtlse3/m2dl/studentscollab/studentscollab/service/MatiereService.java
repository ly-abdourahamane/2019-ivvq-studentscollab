package fr.univtlse3.m2dl.studentscollab.studentscollab.service;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.MatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {

    @Autowired
    MatiereRepository matiereRepository;



    public MatiereRepository getMatiereRepository() {
        return matiereRepository;
    }

    public void setMatiereRepository(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public List<Matiere> findAllMatieres(){
        List<Matiere> matieres = new ArrayList();
        matiereRepository.findAll().forEach(matieres:: add);
        return matieres;
    }

    public Matiere findById(Long id) throws MatiereNotFoundException {
        Optional<Matiere> matiere = matiereRepository.findById(id);
        if (!matiere.isPresent()){
            throw new MatiereNotFoundException("La matière n°"+id+" n'existe pas");
        }
        return matiere.get();
    }

    public Matiere saveMatiere(Matiere matiere){
        if(matiere == null)
            throw new IllegalArgumentException();
        matiere = matiereRepository.save(matiere);
        return matiereRepository.save(matiere);
    }

}
