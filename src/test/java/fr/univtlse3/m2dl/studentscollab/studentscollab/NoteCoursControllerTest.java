package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InitialisationService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class NoteCoursControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private NoteCoursService ncService;

    @Autowired
    private InitialisationService initialisationService;

    @Autowired
    private HttpSession session;

    @MockBean
    private EtudiantService etudiantService;

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
        this.mockMvc.perform(get("/api/v1/cours/new")).andExpect(status().isFound());
    }

    @Test
    public void testPageCoursAll() throws Exception {
        when(ncService.findAll()).thenReturn(listeNcExpected);
        when(etudiantService.findById( initialisationService.getAbdourahamane().getId())).thenReturn(Optional.of(initialisationService.getAbdourahamane()));
        this.mockMvc.perform(get("/api/v1/cours/all").param("etudiantId", initialisationService.getAbdourahamane().getId().toString())).andExpect(status().isOk())
                .andExpect(content().string(containsString("nouvelleNote")))
                .andExpect(content().string(containsString("contenuNote")));
    }

    @Test
    public void testPageCoursDetail() throws Exception {
        Long idNote = initialisationService.getNoteMaxime().getId();
        when(ncService.findNoteCoursById(idNote)).thenReturn(initialisationService.getNoteMaxime());
        when(etudiantService.findById( initialisationService.getAbdourahamane().getId())).thenReturn(Optional.of(initialisationService.getAbdourahamane()));
        this.mockMvc.perform(get("/api/v1/cours/"+idNote).param("etudiantId", initialisationService.getAbdourahamane().getId().toString())).andExpect(status().isOk())
                .andExpect(content().string(containsString(initialisationService.getNoteMaxime().getTitre())))
                .andExpect(content().string(containsString(initialisationService.getNoteMaxime().getContenu())));
    }

    @Test
    public void testPageCoursDetailAnonymousUser() throws Exception {
        Long idNote = initialisationService.getNoteMaxime().getId();
        when(ncService.findNoteCoursById(idNote)).thenReturn(initialisationService.getNoteMaxime());
        this.mockMvc.perform(get("/api/v1/cours/"+idNote)).andExpect(status().isFound())
                .andExpect(redirectedUrl("/api/v1/etudiants/connexion"));
    }
}
