package repository;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import helloworld.repository.LanguageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mimo on 17.05.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_test.xml"})
public class LanguageRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    LanguageRepository sut;

    @Test
    @Sql("classpath:insertLanguages.sql")
    public void getLanguage2() {
        final List<LanguageEntity> resultList = sut.findAll();
        Assert.assertEquals(2, resultList.size());
    }

    @Test
    @Sql("classpath:insertLanguages.sql")
    public void getLanguage() {
        final LanguageEntity language = sut.findByLanguage(SupportedLanguagesEnum.ENGLISH);
        assertNotNull(language);
        assertEquals(SupportedLanguagesEnum.ENGLISH, language.getLanguage());
        assertEquals(Long.valueOf(2l), language.getId());
        assertEquals("Hello World", language.getHelloWorld());
    }
}
