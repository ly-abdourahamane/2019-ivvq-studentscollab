package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
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

    private boolean estValide = false;

    public Etudiant(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public void etudiantValide() {
        estValide = true;
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

    @Override
    public int hashCode() {
        int result = nom!=null?nom.hashCode():0;
        result = 31 * result + (motDePasse!=null? motDePasse.hashCode():0);
        result = 31 * result + (prenom!=null? prenom.hashCode():0);

        return result;
    }
}
