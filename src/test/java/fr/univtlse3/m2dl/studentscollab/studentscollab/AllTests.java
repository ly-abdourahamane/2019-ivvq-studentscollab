package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.etudiant.*;
import fr.univtlse3.m2dl.studentscollab.studentscollab.formation.*;
import fr.univtlse3.m2dl.studentscollab.studentscollab.inscription.InscriptionControllerIntegrationTest;
import fr.univtlse3.m2dl.studentscollab.studentscollab.inscription.InscriptionServiceIntegrationTest;
import fr.univtlse3.m2dl.studentscollab.studentscollab.inscription.InscriptionServiceTest;
import fr.univtlse3.m2dl.studentscollab.studentscollab.inscription.InscriptionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({EtudiantControllerTests.class, EtudiantServiceTest.class, EtudiantTest.class,
        EtudiantControllerIntegrationTest.class, EtudiantServiceIntegrationTest.class,

        FormationTest.class, FormationServiceTest.class, FormationControllerIntegrationTest.class, FormationServiceIntegrationTest.class,

        InscriptionTest.class, InscriptionServiceTest.class, InscriptionControllerIntegrationTest.class, InscriptionServiceIntegrationTest.class
})
public class AllTests {
}
