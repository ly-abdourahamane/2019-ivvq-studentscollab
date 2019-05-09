package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class NoteCours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String titre;

    @NotNull
    private String contenu;

    @PositiveOrZero
    private int nbLike;

    @PositiveOrZero
    private int nbDislike;

    @OneToMany(mappedBy = "noteCours", cascade = CascadeType.PERSIST)
    private Collection<Commentaire> commentaires = new ArrayList<>();

    public NoteCours() {
    }

    public NoteCours(Long id, @NotNull String titre, @NotNull String contenu) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = 0;
        this.nbDislike = 0;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = 0;
        this.nbDislike = 0;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu, @PositiveOrZero int nbLike, @PositiveOrZero int nbDislike) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = nbLike;
        this.nbDislike = nbDislike;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu, @PositiveOrZero int nbLike, @PositiveOrZero int nbDislike, Collection<Commentaire> commentaires) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = nbLike;
        this.nbDislike = nbDislike;
        this.commentaires = commentaires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getNbLike() {
        return nbLike;
    }

    public void setNbLike(int nbLike) {
        this.nbLike = nbLike;
    }

    public int getNbDislike() {
        return nbDislike;
    }

    public void setNbDislike(int nbDislike) {
        this.nbDislike = nbDislike;
    }

    public Collection<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
