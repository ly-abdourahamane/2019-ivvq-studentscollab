package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteCoursService {

    @Autowired
    private NoteCoursDao ncdao;

    public NoteCours saveNoteCours(NoteCours n) {
        return this.ncdao.save(n);
    }

    public Iterable<NoteCours> findAll(){
        return this.ncdao.findAll();
    }

    public Optional<NoteCours> findNoteCoursById(Long id) {
        return this.ncdao.findById(id);
    }
}
