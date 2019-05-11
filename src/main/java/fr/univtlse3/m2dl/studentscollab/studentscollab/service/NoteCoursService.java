package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.NoteCoursNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteCoursService {

    @Autowired
    private NoteCoursRepository noteCoursRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public EtudiantRepository getEtudiantRepository() {
        return etudiantRepository;
    }

    public void setEtudiantRepository(EtudiantRepository etudiantRepository) { this.etudiantRepository = etudiantRepository; }


    public NoteCours saveNoteCours(NoteCours n) {
        if(n == null)
            throw new IllegalArgumentException();
        Matiere matiere = n.getMatiere();
        n = noteCoursRepository.save(n);
        matiere.getNoteCours().add(n);
        if (n.getRedacteur().getId() == null) {
            etudiantRepository.save(n.getRedacteur());
        }

        return this.noteCoursRepository.save(n);
    }

    public Iterable<NoteCours> findAll(){
        return this.noteCoursRepository.findAll();
    }

    public NoteCours findNoteCoursById(Long id) throws NoteCoursNotFoundException {
        Optional<NoteCours> nc = this.noteCoursRepository.findById(id);
        if (!nc.isPresent()){
            throw new NoteCoursNotFoundException("La note de cours id n°"+id+" n'existe pas");
        }
        return nc.get();
    }

    public NoteCoursRepository getNoteCoursRepository() {
        return this.noteCoursRepository;
    }

    public void setNoteCoursRepository(NoteCoursRepository noteCoursRepository) {
        this.noteCoursRepository = noteCoursRepository;
    }



}
