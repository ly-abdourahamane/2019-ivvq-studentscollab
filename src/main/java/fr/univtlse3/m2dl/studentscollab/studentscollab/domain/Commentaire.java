package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String contenu;

    @ManyToOne
    private NoteCours noteCours;

    public Commentaire() {
    }

    public Commentaire(@NotNull String contenu) {
        this.contenu = contenu;
    }

    public Commentaire(Long id, @NotNull String contenu) {
        this.contenu = contenu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public NoteCours getNoteCours() {
        return noteCours;
    }

    public void setNoteCours(NoteCours noteCours) {
        this.noteCours = noteCours;
    }
}
