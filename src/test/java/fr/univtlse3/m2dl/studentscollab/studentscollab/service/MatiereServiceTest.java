package fr.univtlse3.m2dl.studentscollab.studentscollab.service;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Matiere;
import fr.univtlse3.m2dl.studentscollab.studentscollab.repository.MatiereRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatiereServiceTest {

    @Autowired
    MatiereService matiereService;

    Matiere matiere1;

    @Before
    public void setup(){
        matiere1 = new Matiere();
        matiereService = new MatiereService();
    }

    @Test
    public void findAllMatieres() {

    }

    @Test
    public void findById() {
    }

    @Test
    public void saveMatiere() {
     
    }
}