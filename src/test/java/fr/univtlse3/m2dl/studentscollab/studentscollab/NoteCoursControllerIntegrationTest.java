package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.NoteCoursController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Etudiant;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.NoteCours;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.NoteCoursRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EvaluationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InitialisationService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.NoteCoursService;
import org.aspectj.weaver.ast.Not;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class NoteCoursControllerIntegrationTest {

    @Autowired
    private InitialisationService initialisationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPageNoteLike() throws Exception {
        Etudiant etu = initialisationService.getMaxime();
        NoteCours nc = initialisationService.getNoteMaxime();
        this.mockMvc.perform(get("/api/v1/cours/like/" + nc.getId())
                .param("etudiantId", etu.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("nbLike: 1")));
    }

    @Test
    public void testPageNoteDislike() throws Exception {
        Etudiant etu = initialisationService.getMaxime();
        NoteCours nc = initialisationService.getNoteMaxime();
        this.mockMvc.perform(get("/api/v1/cours/dislike/" + nc.getId())
                .param("etudiantId", etu.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("nbDislike: 1")));
    }
}
