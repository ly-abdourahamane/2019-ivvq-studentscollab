package fr.univtlse3.m2dl.studentscollab.studentscollab.inscription;

import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author abdou on 24/04/19.
 * @project studentscollab
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class InscriptionControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InscriptionService inscriptionService;

    private MediaType contentType = new MediaType(MediaType.TEXT_HTML.getType(),
            MediaType.TEXT_HTML.getSubtype(),
            Charset.forName("utf8"));

    private String htmlResult;


    @Test
    public void testCreationInscription() throws Exception {
        //when: un étudiant veut s'inscrire à une formation
        mockMvc.perform(get("/api/v1/inscriptions/creer"))
                // then: la réponse a le status 200(OK)
                .andExpect(status().isOk())
                // then: la réponse est au format HTML et utf8
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });

        // then: le résultat obtenu contient le label email de  la formation
        assertThat(htmlResult, containsString("Email"));
        // then: le résultat obtenu contient le label formation de la formation
        assertThat(htmlResult, containsString("Formation"));
    }

    @Test
    public void testRedirectionAprsCreationInscription() throws Exception {
        final long count = inscriptionService.findAll().size();
        Assert.assertTrue(count > 0);

        mockMvc.perform(post("/api/v1/formations/valider"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/api/v1/formations"))
                .andDo(MockMvcResultHandlers.print());
    }
}
