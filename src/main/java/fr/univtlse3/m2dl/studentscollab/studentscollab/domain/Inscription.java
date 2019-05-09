package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Inscription {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @NotNull
    @ManyToOne
    private Etudiant etudiant;

    @NotNull
    @ManyToOne
    private Matiere matiere;

    public Inscription() {
    }

    public Inscription(Etudiant etudiant, Matiere matiere) {
        this.etudiant = etudiant;
        this.matiere = matiere;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
