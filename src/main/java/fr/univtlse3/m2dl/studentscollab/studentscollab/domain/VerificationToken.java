package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private LocalDateTime dateExpiration;
    @OneToOne
    private Etudiant etudiant;

    public VerificationToken(String token,LocalDateTime dateExpiration, Etudiant etudiant){
        this.token = token;
        this.dateExpiration = dateExpiration;
        this.etudiant = etudiant;
    }

}
