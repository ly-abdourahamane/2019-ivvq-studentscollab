package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.NoteCoursController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EvaluationService;
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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteCoursControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private NoteCoursService ncService;

    @MockBean
    private EtudiantService etudiantService;

    @MockBean
    private EvaluationService evaluationService;

    private MockMvc mockMvc;

    private Iterable<NoteCours> listeNcExpected = new ArrayList<NoteCours>(){{
        add(new NoteCours(1L, "nouvelleNote", "contenuNote"));
    }};

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testPageNouvelleNote() throws Exception {
        this.mockMvc.perform(get("/cours/new")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Ajout d'une note de cours")));
    }

    @Test
    public void testPageCoursAll() throws Exception {
        when(ncService.findAll()).thenReturn(listeNcExpected);
        this.mockMvc.perform(get("/cours/all")).andExpect(status().isOk())
                .andExpect(content().string(containsString("nouvelleNote")))
                .andExpect(content().string(containsString("contenuNote")));
    }

    @Test
    public void testPageNoteLike() throws Exception {
        Etudiant etu = new Etudiant("Truc","Bidule","tb@gmail.com","trucbidule");
        etudiantService.save(etu);
        NoteCours nc = new NoteCours("t1", "contenu 1", etu);
        ncService.saveNoteCours(nc);
        this.mockMvc.perform(post("/cours/"+nc.getId()+"/like", nc, etu)).andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }

}
