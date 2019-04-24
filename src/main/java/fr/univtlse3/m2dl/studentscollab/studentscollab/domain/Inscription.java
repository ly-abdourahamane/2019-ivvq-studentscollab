package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author abdou on 21/04/19.
 * @project studentscollab
 */

@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Formation formation;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Etudiant etudiant;

    private LocalDateTime dateInscription;

    public Inscription() {}

    public Inscription(@NotNull Etudiant etudiant, @NotNull Formation formation) {
        this.formation = formation;
        this.etudiant = etudiant;
    }

    @PrePersist
    public void updateInscriptionDate() {
        if (dateInscription == null)
            setDateInscription(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", formation=" + formation +
                ", etudiant=" + etudiant +
                ", dateInscription=" + dateInscription +
                '}';
    }
}

