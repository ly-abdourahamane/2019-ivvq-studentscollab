package fr.univtlse3.m2dl.studentscollab.studentscollab;


import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.MatiereController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
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
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MatiereControllerTest {

    @Autowired
    private WebApplicationContext webAppli;

    @MockBean
    private MatiereService matiereService;
    private MatiereController matiereController;

    private MockMvc mockMvc;

    private Iterable<Matiere> matieresExpected = new ArrayList<Matiere>(){{
        add(new Matiere("Matiere1"));
    }};

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppli).build();
    }

    @Test
    public void testNouvelleMatiere() throws Exception {
        this.mockMvc.perform(get("/matiere/new")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Ajout d'une Matiere")));

}

    @Test
    public void testMatieres() throws Exception {
        this.mockMvc.perform(get("/matieres")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Matières")));
    }
}
