package fr.univtlse3.m2dl.studentscollab.studentscollab.domain;

import java.util.List;

public class InscriptionForm {
    private String email;

    private String niveau;

    private List<Formation> formations;

    public InscriptionForm() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }
}
