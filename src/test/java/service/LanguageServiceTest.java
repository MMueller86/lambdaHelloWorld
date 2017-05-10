package service;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.dao.ILanguageDAO;
import helloworld.entity.LanguageEntity;
import helloworld.service.LanguageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by mimo on 26.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LanguageServiceTest {

    @Mock
    private ILanguageDAO languageDAOMock;
    private LanguageService sut;

    @Before
    public void setUp() {
        sut = new LanguageService();
        sut.setLanguageDao(languageDAOMock);
    }

    @Test
    public void getSupportedLanguages_EmptyList() {
        when(languageDAOMock.listLanguages()).thenReturn(new ArrayList<LanguageEntity>());
        final String result = sut.getSupportedLanguages();
        Assert.assertEquals("", result);
    }

    @Test
    public void getSupportedLanguages_OneElement() {
        when(languageDAOMock.listLanguages()).thenReturn(getMockLanguageList(1));
        final String result = sut.getSupportedLanguages();
        Assert.assertEquals("language 0", result);
    }

    @Test
    public void getSupportedLanguages_TwoElements() {
        when(languageDAOMock.listLanguages()).thenReturn(getMockLanguageList(2));
        final String result = sut.getSupportedLanguages();
        Assert.assertEquals("language 0, language 1", result);
    }

    @Test
    public void getHelloWorldForLanguage() {
        final LanguageEntity entity = new LanguageEntity();
        entity.setHelloWorld("Hello World");
        when(languageDAOMock.getLanguage(SupportedLanguagesEnum.ENGLISH)).thenReturn(entity);
        final String result = sut.getHelloWorldForLanguage(SupportedLanguagesEnum.ENGLISH);
        Assert.assertEquals(entity.getHelloWorld(), result);
    }

    private List<LanguageEntity> getMockLanguageList(final int count) {
        final List<LanguageEntity> mockResultList = new ArrayList<LanguageEntity>();

        for (int i = 0; i < count; i++) {
            final LanguageEntity entity = new LanguageEntity();
            entity.setId(i);
            entity.setLanguage("language " + i);
            entity.setHelloWorld("Hallo Welt " + i);
            mockResultList.add(entity);
        }
        return mockResultList;
    }
}
