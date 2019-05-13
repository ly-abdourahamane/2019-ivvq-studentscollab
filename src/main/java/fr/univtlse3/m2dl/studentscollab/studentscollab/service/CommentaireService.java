package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Commentaire;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;


    public void addCommentaires(NoteCours noteCours) {
        for (Commentaire c: noteCours.getCommentaires()) {
            c.setNoteCours(noteCours);
            c = this.saveCommentaire(c);
        }
    }

    public Commentaire saveCommentaire(Commentaire c){
        return this.commentaireRepository.save(c);
    }

    public Optional<Commentaire> findCommentaireById(Long id){
        return this.commentaireRepository.findById(id);
    }

    public CommentaireRepository getCommentaireRepository() {
        return commentaireRepository;
    }

    public void setCommentaireRepository(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

}
