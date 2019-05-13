package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.service.EtudiantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class BoostrapTest {

    //private Bootstrap bootstrap;

    @MockBean
    private EtudiantService etudiantService;

    @Before
    public void setUp() {
    //   bootstrap = new Bootstrap();
    }

    @Test
    public void test() {
        //assertThat(bootstrap, is(notNullValue()));
    }

}
