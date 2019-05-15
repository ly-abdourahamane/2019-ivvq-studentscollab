package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.InscriptionToMatiereController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.controller.MatiereController;
import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.InscriptionToMatiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.exception.InscriptionToMatiereNotFoundException;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.InscriptionToMatiereService;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
public class InscriptionToMatiereControllerTest {

    private InscriptionToMatiere inscriptionToMatiere;

    @MockBean
    private InscriptionToMatiereService inscriptionToMatiereService;
    private InscriptionToMatiereController inscriptionToMatiereController;

    @Before
    public void setUp() throws Exception {
        inscriptionToMatiereController = new InscriptionToMatiereController();
        inscriptionToMatiereController.setInscriptionToMatiereService(inscriptionToMatiereService);
        inscriptionToMatiere = new InscriptionToMatiere();
    }

    @Test
    public void testControllerDelegationFindToService() throws InscriptionToMatiereNotFoundException {
        //when: on appel le controlleur : InscriptionToMatiereController
        inscriptionToMatiereController.findMatiereByIdTests(0L);
        //then: le service est invoqué
        verify(inscriptionToMatiereService).findById(0L);
    }


    @Test
    public void testControllerDelegationSaveToService() {
        //when: on appel le controlleur : InscriptionToMatiereController
        inscriptionToMatiereController.saveInscriptionToMatiereTests(inscriptionToMatiere);
        //then: le service est invoqué
        verify(inscriptionToMatiereService).saveInscription(inscriptionToMatiere);
    }

}
