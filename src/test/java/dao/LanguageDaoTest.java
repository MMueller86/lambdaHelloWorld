package dao;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.dao.ILanguageDAO;
import helloworld.entity.LanguageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mimo on 24.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_test.xml"})
public class LanguageDaoTest {
    @Autowired
    ILanguageDAO sut;


    @Test
    @Sql("classpath:insertLanguages.sql")
    public void ListLanguages() {
        final List<LanguageEntity> languageList = sut.listLanguages();
        assertNotNull(languageList);
        assertEquals(2, languageList.size());
    }

    @Test
    @Sql("classpath:insertLanguages.sql")
    public void getLanguage() {
        final LanguageEntity language = sut.getLanguage(SupportedLanguagesEnum.ENGLISH);
        assertNotNull(language);
        assertEquals(SupportedLanguagesEnum.ENGLISH.toString(), language.getLanguage());
    }
}
