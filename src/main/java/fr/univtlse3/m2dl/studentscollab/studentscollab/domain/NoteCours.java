package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NoteCours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Etudiant redacteur;

    @NotNull
    private String titre;

    @NotNull
    private String contenu;

    @PositiveOrZero
    private int nbLike;

    @PositiveOrZero
    private int nbDislike;

    @ManyToOne
    @NotNull
    private Matiere matiere;

//    @OneToMany
//    private Collection<Commentaire> commentaires = new ArrayList<>();

    public NoteCours() {
    }

    public NoteCours(Long id, @NotNull String titre, @NotNull String contenu) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = 0;
        this.nbDislike = 0;
    }

    public NoteCours(Long id, @NotNull String titre, @NotNull String contenu, Etudiant redacteur) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = 0;
        this.nbDislike = 0;
        this.redacteur = redacteur;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu, @NotNull Etudiant redacteur,@NotNull Matiere matiere) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = 0;
        this.nbDislike = 0;
        this.redacteur = redacteur;
        this.matiere = matiere;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = 0;
        this.nbDislike = 0;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu, @PositiveOrZero int nbLike, @PositiveOrZero int nbDislike/*, Collection<Commentaire> commentaires*/) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = nbLike;
        this.nbDislike = nbDislike;
//        this.commentaires = commentaires;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu, @NotNull Etudiant redacteur) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = 0;
        this.nbDislike = 0;
        this.redacteur = redacteur;
    }

    public NoteCours(@NotNull String titre, @NotNull String contenu, @PositiveOrZero int nbLike, @PositiveOrZero int nbDislike, @NotNull Etudiant redacteur,@NotNull Matiere matiere) {
        this.titre = titre;
        this.contenu = contenu;
        this.nbLike = nbLike;
        this.nbDislike = nbDislike;
        this.redacteur = redacteur;
        this.matiere = matiere;
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

    public Etudiant getRedacteur() {return redacteur;}

    public void setRedacteur(Etudiant redacteur) {this.redacteur = redacteur;}
/*
    public Collection<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }*/

    public Matiere getMatiere() {
        return matiere;
    }
}
