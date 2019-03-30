package fr.univtlse3.m2dl.studentscollab.studentscollab.services;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.VerificationToken;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repositories.EtudiantRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private MailService mailService;

    public Etudiant findById(Long id) {
        return etudiantRepository.findById(id).get();
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public String validateEtudiant(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken == null || verificationToken.getDateExpiration().isBefore(LocalDateTime.now())) {
            return "Le lien a expiré, veuillez vous réinscrire";
        }

        Etudiant etudiant = verificationToken.getEtudiant();
        etudiant.etudiantValide();

        etudiantRepository.save(etudiant);
        verificationTokenRepository.delete(verificationToken);

        return "Bienvenue sur l'application de collaboration de studentsCollab";
    }

    public Etudiant save(Etudiant etudiant, String url) {
        Etudiant etudiantSaved = etudiantRepository.save(etudiant);
        sendConfirmationEmail(etudiantSaved, url);

        return etudiantSaved;
    }

    public String login(String email, String motDePasse) {
        Etudiant etudiant = this.etudiantRepository.login(email, motDePasse);

        String message = "email ou mot de passe invalid";

        if(etudiant != null) {
            message = etudiant.isEstValide() ? "Vous êtes connecté" : "email ou mot de passe invalide";
        }

        return message;
    }

    private void sendConfirmationEmail(Etudiant etudiant,String url){
        String token = UUID.randomUUID().toString();
        LocalDateTime dateExpirationToken = LocalDateTime.now().plusHours(5); //5h pour que l'étudiant confirme son inscription

        VerificationToken verificationToken = new VerificationToken(token,dateExpirationToken,etudiant);
        VerificationToken savedToken = verificationTokenRepository.save(verificationToken);

        mailService.sendVerificationEmail(url,savedToken.getToken(), etudiant.getEmail());
    }

}
