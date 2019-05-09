package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InscriptionToMatiereControllerTest {

    @Autowired
    private WebApplicationContext webAppli;

    @MockBean
    private NoteCoursService ncService;

    private MockMvc mockMvc;

    private String ResultatTemplate;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppli).build();
    }

    @Test
    public void testAddInscription() throws Exception {
       /* Long idMatiere = 0L;
        mockMvc.perform(get("/inscription").requestAttr("id_matiere",idMatiere))
        .andExpect(status().isOk())
        .andDo(mvcResult -> ResultatTemplate = mvcResult.getResponse().getContentAsString());
        */
    }

    @Test
    public void testInscrire() throws Exception {
        /*Long idMatiere = 0L;
        mockMvc.perform(get("/inscrire").requestAttr("idMatiere",idMatiere))
                .andExpect(status().isOk())
                .andDo(mvcResult -> ResultatTemplate = mvcResult.getResponse().getContentAsString());
                */
    }
}
