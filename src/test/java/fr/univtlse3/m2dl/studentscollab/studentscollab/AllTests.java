package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.etudiant.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EtudiantControllerTests.class, EtudiantServiceTest.class, EtudiantTest.class,
        EtudiantControllerIntegrationTest.class, EtudiantServiceIntegrationTest.class})
public class AllTests {
}
