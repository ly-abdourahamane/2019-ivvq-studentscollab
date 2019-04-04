package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Login {

    private String email;

    private String motDePasse;

}
