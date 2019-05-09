package fr.univtlse3.m2dl.studentscollab.studentscollab.formation;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Formation;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.FormationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InitialisationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author abdou on 20/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class FormationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InitialisationService initialisationService;

    @Autowired
    private FormationService formationService;

    private MediaType contentType = new MediaType(MediaType.TEXT_HTML.getType(),
            MediaType.TEXT_HTML.getSubtype(),
            Charset.forName("utf8"));

    private String htmlResult;

    @Test
    public void testAfficherListeEtudiants() throws Exception {
        Formation formation = initialisationService.getDl();

        //when: un admin émet une requête pour obtenir la liste des formations
        mockMvc.perform(get("/api/v1/formations"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: le résultat obtenu contient le nom de la formation
        assertThat(htmlResult, containsString(formation.getNom()));
        // then: le résultat obtenu contient le niveau de la formation persistée
        assertThat(htmlResult, containsString(formation.getNiveau()));
    }

    @Test
    public void testAffichageFormation() throws Exception {
        Formation formation = initialisationService.getDl();

        //when: un admin émet une requête pour obtenir un étudiant
        mockMvc.perform(get("/api/v1/formations/"+formation.getId()))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: le résultat obtenu contient le nom de la formation
        assertThat(htmlResult, containsString(formation.getNom()));
        // then: le résultat obtenu contient le niveau de la formation persistée
        assertThat(htmlResult, containsString(formation.getNiveau()));
    }

    @Test
    public void testCreationFormation() throws Exception {
        //when: un admin émet une requête pour créer une formation
        mockMvc.perform(get("/api/v1/formations/creer"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: le résultat obtenu contient le label nom de  la formation
        assertThat(htmlResult, containsString("Nom"));
        // then: le résultat obtenu contient le label niveau de la formation
        assertThat(htmlResult, containsString("Niveau"));
    }


    @Test
    public void testSuppressionFormation() throws Exception {
        // given: un objet MockMvc qui simulate des échanges Mvc
        // when: on simule du requête HTTP de type DELETE vers "/formations/delete" avec un id invalide
        // then: la requête est acceptée (status OK)
        // then: une redirection vers la requête GET vers "/api/v1/formations" a lieu
        // then: le nombre de formations en base a diminué de 1

        long count = formationService.findAllFormations().size();
        Formation formation = initialisationService.getDl();

        Assert.assertTrue(count > 0);

        mockMvc.perform(delete("/api/v1/formations/delete/"+formation.getId()))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/api/v1/formations"))
                .andDo(MockMvcResultHandlers.print());
        Assert.assertEquals(count - 1, formationService.findAllFormations().size());

    }

    @Test
    public void testRedirectionAprsCreationDeFormation() throws Exception {
        final long count = formationService.findAllFormations().size();
        Assert.assertTrue(count > 0);

        mockMvc.perform(post("/api/v1/formations/valider"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/api/v1/formations"))
                .andDo(MockMvcResultHandlers.print());
    }

}
