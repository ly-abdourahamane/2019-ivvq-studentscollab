package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
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
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", estValide=" + estValide +
                '}';
    }
}
