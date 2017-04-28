package it;

import helloWorld.service.ILanguageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mimo on 26.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_test.xml"})
public class SpringIntegrationTest {
    @Autowired
    private ILanguageService sut;

    @Test
    public void springConfigTest() {
        Assert.assertNotNull(sut);
    }

    @Test
    @Sql("classpath:insertLAnguages.sql")
    public void getSupportedLanguages() {
        final String result = sut.getSupportedLanguages();
        Assert.assertEquals("Deutsch, Englisch", result);
    }


}
