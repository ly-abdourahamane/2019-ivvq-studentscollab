package fr.univtlse3.m2dl.studentscollab.studentscollab;


import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.MatiereController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import static org.mockito.Mockito.verify;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MatiereControllerTest {

    @MockBean
    private MatiereService matiereService;
    private MatiereController matiereController;
    private Matiere matiere;
    private Model model;

    @Before
    public void setUp(){
        matiereController = new MatiereController();
        matiereController.setMatiereService(matiereService);
        matiere = new Matiere("matiere1");
    }

    @Test
    public void testControllerDelegationFindToService() {
        //when: on appel le controlleur : MatiereController
        matiereController.findAllMatiereTests();
        //then: le service est invoqué
        verify(matiereService).findAllMatieres();
    }


    @Test
    public void testControllerDelegationSaveToService() {
        //when: on appel le controlleur : MatiereController
        matiereController.saveMatiereTests(matiere);
       //then: le service est invoqué
        verify(matiereService).saveMatiere(matiere);
    }

}
