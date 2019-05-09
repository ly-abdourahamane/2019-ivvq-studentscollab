package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Matiere {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long Id;
    @NotNull
    private String nom;

    @OneToMany(mappedBy = "matiere")
    @JsonIgnore
    private Set<InscriptionToMatiere> inscriptionToMatieres = new HashSet<>();

    @OneToMany(mappedBy = "matiere")
    @JsonIgnore
    private Set<NoteCours> noteCours = new HashSet<>();

    public Matiere() {
    }

    public Matiere(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Set<NoteCours> getNoteCours() {
        return noteCours;
    }

    public Set<InscriptionToMatiere> getInscriptionToMatieres() {
        return inscriptionToMatieres;
    }

    public void setInscriptionToMatieres(Set<InscriptionToMatiere> inscriptionToMatieres) {
        this.inscriptionToMatieres = inscriptionToMatieres;
    }

    public void setNoteCours(Set<NoteCours> noteCours) {
        this.noteCours = noteCours;
    }
}
