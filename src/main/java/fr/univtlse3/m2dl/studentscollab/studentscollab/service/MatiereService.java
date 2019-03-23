package fr.univtlse3.m2dl.studentscollab.studentscollab.service;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    public Matiere findById(Long id){
        return (matiereRepository.findById(id).orElse(null));
    }

    public Matiere saveMatiere(Matiere matiere){
        if(matiere == null)
            throw new IllegalArgumentException();
        return matiereRepository.save(matiere);
    }

    public long count() {
        return matiereRepository.count();
    }
}
