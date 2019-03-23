package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;
import javax.persistence.*;

@Entity
public class Matiere {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long Id;
    private String nom;


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
}
