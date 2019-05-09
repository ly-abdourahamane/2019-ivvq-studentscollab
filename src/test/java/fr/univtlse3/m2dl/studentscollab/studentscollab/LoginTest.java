package fr.univtlse3.m2dl.studentscollab.studentscollab;

import fr.univtlse3.m2dl.studentscollab.studentscollab.domain.Login;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoginTest {

    private Login login;

    @Before
    public void setup() {
        login = new Login();
        login.setEmail("john@gmail.com");
        login.setMotDePasse("9865l");
    }

    @Test
    public void testGetters() {
        Assert.assertTrue("Renvoie le bon email", login.getEmail().equals("john@gmail.com"));
        Assert.assertTrue("Renvoie le mdp", login.getMotDePasse().equals("9865l"));
    }

    @Test
    public void testSetters() {
        login.setMotDePasse("5421");
        login.setEmail("do@gmail.com");

        Assert.assertFalse("mails different", login.getEmail().equals("john@gmail.com"));
        Assert.assertFalse("mdp different", login.getMotDePasse().equals("9865l"));
    }
}
