package fr.univtlse3.m2dl.studentscollab.studentscollab;


import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import fr.univtlse3.m2dl.studentscollab.studentscollab.service.MatiereService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class MatiereServiceTest {

    private MatiereService matiereService;

    @MockBean
    private MatiereRepository matiereRepository;

    @Before
    public void setup() {
        matiereService = new MatiereService();
        matiereService.setMatiereRepository(matiereRepository);
    }

    @Test
    public void findAllMatieres() {
        matiereService.findAllMatieres();
        verify(matiereService.getMatiereRepository()).findAll();
    }

    @Test
    public void findById() {
        matiereService.findById(0L);
        verify(matiereService.getMatiereRepository()).findById(0L);
    }

    @Test
    public void saveMatiere() {
         Matiere matiere = new Matiere();
         matiereService.saveMatiere(matiere);
         verify(matiereService.getMatiereRepository()).save(matiere);
    }
}