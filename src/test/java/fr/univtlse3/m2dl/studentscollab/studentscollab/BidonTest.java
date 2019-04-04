package fr.univtlse3.m2dl.studentscollab.studentscollab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BidonTest {

    private List<String> list;
    @Before //fixture
    public void setUp() throws Exception {
       list = new ArrayList<String>();
    }

    @Test
    public void testEmptyList() {
        Assert.assertTrue("La liste est vide", list.size()  == 0);
    }
}
