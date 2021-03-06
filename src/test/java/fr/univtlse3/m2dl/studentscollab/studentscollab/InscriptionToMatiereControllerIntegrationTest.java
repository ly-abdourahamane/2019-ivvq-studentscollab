package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InitialisationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class InscriptionToMatiereControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webAppli;

    @Autowired
    private InitialisationService initialisationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HttpSession session;

    private Matiere matiere = new Matiere("matiere");

    private String ResultatTemplate;

    private MediaType contentType = new MediaType(MediaType.TEXT_HTML.getType(),
            MediaType.TEXT_HTML.getSubtype(),
            Charset.forName("utf8"));


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppli).build();
    }

    @Test
    public void testAddInscription() throws Exception {
        Matiere matiere = initialisationService.getMatiere1();
        mockMvc.perform(get("/inscription?id_matiere="
                + matiere.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    ResultatTemplate = mvcResult.getResponse().getContentAsString();
                });;

    }


    @Test
    public void testAddInscriptionMatiereNotFound() throws Exception {
        Etudiant etudiant = initialisationService.getAbdourahamane();
        mockMvc.perform(get("/inscription?id_matiere="
                + 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    ResultatTemplate = mvcResult.getResponse().getContentAsString();
                });;
        session.setAttribute("etudiant",etudiant);
        mockMvc.perform(post("/inscrire?idMatiere="
                + 10))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    ResultatTemplate = mvcResult.getResponse().getContentAsString();
                });;
    }
}
