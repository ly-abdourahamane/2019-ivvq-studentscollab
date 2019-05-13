package fr.univtlse3.m2dl.studentscollab.studentscollab.etudiant;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InitialisationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EtudiantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InitialisationService initialisationService;

    private MediaType contentType = new MediaType(MediaType.TEXT_HTML.getType(),
            MediaType.TEXT_HTML.getSubtype(),
            Charset.forName("utf8"));

    private String htmlResult;

    @Test
    public void testAfficherListeEtudiants() throws Exception {
        Etudiant etudiant = initialisationService.getAbdourahamane();

        //when: un admin émet une requête pour obtenir la liste des étudiants
        mockMvc.perform(get("/api/v1/etudiants"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: le résultat obtenu contient l'email d'un étudiant
        assertThat(htmlResult, containsString(etudiant.getEmail()));
        // then: le résultat obtenu contient le nom d'un étudiant persistée
        assertThat(htmlResult, containsString(etudiant.getNom()));
    }


    @Test
    public void testAfficherProfileEtudiant() throws Exception {
        Etudiant etudiant = initialisationService.getAbdourahamane();

        //when: un admin émet une requête pour obtenir un étudiant
        mockMvc.perform(get("/api/v1/etudiants/"+etudiant.getId()))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: le résultat obtenu contient l'email d'un étudiant
        assertThat(htmlResult, containsString(etudiant.getEmail()));
        // then: le résultat obtenu contient le nom d'un étudiant persistée
        assertThat(htmlResult, containsString(etudiant.getNom()));
    }

    @Test
    public void testAfficherProfileEtudiantNonExistant() throws Exception {
        //when: récuperation d'un étudiant inexistant
        mockMvc.perform(get("/api/v1/etudiants/654965"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: l'utilisateur est redirigé vers la page d'erreur
        assertThat(htmlResult, containsString("Error"));
    }

    @Test
    public void testCreationCompteEtudiant() throws Exception {
        //when: un admin émet une requête pour obtenir un étudiant
        mockMvc.perform(get("/api/v1/etudiants/creer-compte"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: le résultat obtenu contient le label nom de  l'étudiant
        assertThat(htmlResult, containsString("Nom"));
        // then: le résultat obtenu contient le label Prénom de  l'étudiant
        assertThat(htmlResult, containsString("Prénom"));
        // then: le résultat obtenu contient le label Email de  l'étudiant
        assertThat(htmlResult, containsString("Email"));
        // then: le résultat obtenu contient le label Mot de passe de  l'étudiant
        assertThat(htmlResult, containsString("Mot de passe"));
    }

    @Test
    public void testUpdateEtudiant() throws Exception {
        Etudiant test = initialisationService.getMaxime();
        Etudiant modified = new Etudiant("pasmax", "pasr", "truc@gmail.com", "123456");
        mockMvc.perform(post("/api/v1/etudiants/update/" + test.getId()).flashAttr("etudiant", modified))
                .andExpect(status().isFound());
        mockMvc.perform(get("/api/v1/etudiants"))
                .andExpect(content().string(containsString("pasmax")))
                .andExpect(content().string(containsString("pasr")))
                .andExpect(content().string(containsString("truc@gmail.com")));
    }

    @Test
    public void testAfficherPageAccueil() throws Exception {
        Etudiant etudiant = initialisationService.getAbdourahamane();

        //when: je lance l'application
        mockMvc.perform(get("/api/v1/etudiants/accueil"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        //then: la page d'acceuil m'affiche 'Bienvenue sur studensCollab'
        assertThat(htmlResult, containsString("Bienvenue sur studensCollab"));
    }

    @Test
    public void testAfficherPageConnexion() throws Exception {
        //when: j'affiche la page de connexion
        mockMvc.perform(get("/api/v1/etudiants/connexion"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        //then: la page contient un champ email et un champ mdp
        assertThat(htmlResult, containsString("Email"));
        assertThat(htmlResult, containsString("Mot de passe"));
    }
}
