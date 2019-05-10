package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {this.id = id;}
    public Long getId() {return id;}

    public Evaluation() {}
    public Evaluation(Etudiant etudiant, NoteCours noteCours, EvalType evalType) {
        this.evaluateur = etudiant;
        this.noteCours = noteCours;
        this.type = evalType;
    }

    @NotNull
    @ManyToOne
    private Etudiant evaluateur;

    public void setEvaluateur(Etudiant evaluateur) {this.evaluateur = evaluateur;}
    public Etudiant getEvaluateur() {return evaluateur;}

    @NotNull
    @ManyToOne
    private NoteCours noteCours;

    public void setNoteCours(NoteCours noteCours) {this.noteCours = noteCours;}
    public NoteCours getNoteCours() {return noteCours;}

    @NotNull
    private EvalType type;

    public void setType(EvalType type) {this.type = type;}
    public EvalType getType() {return type;}
}
