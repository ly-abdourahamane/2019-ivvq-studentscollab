package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.MatiereController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InitialisationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.junit.Before;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RunWith(SpringRunner.class)
public class MatiereControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.TEXT_HTML.getType(),
            MediaType.TEXT_HTML.getSubtype(),
            Charset.forName("utf8"));
    private String htmlResult;
    @Test
    public void test() throws Exception {
       /* Matiere matiere = new Matiere("matiere1");
        mockMvc.perform(get("/api/matiere/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andDo(mvcResult -> {
                    htmlResult = mvcResult.getResponse().getContentAsString();
                });
        assertThat(htmlResult, containsString(matiere.getNom()));*/
    //}
}


    }
