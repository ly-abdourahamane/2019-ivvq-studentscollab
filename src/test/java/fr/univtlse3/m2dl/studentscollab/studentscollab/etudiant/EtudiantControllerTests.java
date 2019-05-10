package fr.univtlse3.m2dl.studentscollab.studentscollab.etudiant;

import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.EtudiantController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
public class EtudiantControllerTests {

    @MockBean
    private EtudiantService etudiantService;
    private EtudiantController etudiantController;

    @Before
    public void setUp() throws Exception {
        etudiantController = new EtudiantController();
       // etudiantController.setEtudiantService(etudiantService);
    }

    @Test
    public void testControllerDelegationToService() {
        //when: on récupère dans le controleur
        etudiantController.findAllForTests();
        //then: la requête est traitée par le service correspondant
        verify(etudiantService).findAll();
    }
}
