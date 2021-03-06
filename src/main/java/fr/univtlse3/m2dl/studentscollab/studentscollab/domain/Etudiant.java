package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1)
    private String nom;

    @NotNull
    @Size(min = 1)
    private String prenom;

    @Email
    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "mot_de_passe")
    @NotNull
    private String motDePasse;

    public Etudiant(){

    }
    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    private Set<InscriptionToMatiere> inscriptionToMatieres = new HashSet<>();

    private boolean estValide = false;

    public Etudiant(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        
        Etudiant etudiant = (Etudiant) o;
        if(nom!=null? !nom.equals(etudiant.nom): etudiant.nom!=null) {
            return false;
        }

        if(motDePasse!=null? !motDePasse.equals(etudiant.motDePasse): etudiant.motDePasse!=null) {
            return false;
        }

        return prenom!=null? prenom.equals(etudiant.prenom): etudiant.prenom==null;
    }

    public Set<InscriptionToMatiere> getInscriptionToMatieres() {
        return inscriptionToMatieres;
    }

    @Override
    public int hashCode() {
        int result = nom!=null?nom.hashCode():0;
        result = 31 * result + (motDePasse!=null? motDePasse.hashCode():0);
        result = 31 * result + (prenom!=null? prenom.hashCode():0);

        return result;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
