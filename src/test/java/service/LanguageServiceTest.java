package service;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import helloworld.repository.LanguageRepository;
import helloworld.service.LanguageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private final LanguageService sut = new LanguageService();
    @Mock
    private LanguageRepository languageRepository;

    @Test
    public void getSupportedLanguages_EmptyList() {
        when(languageRepository.listAll()).thenReturn(new ArrayList<LanguageEntity>());
        final String result = sut.getSupportedLanguages();
        Assert.assertEquals("", result);
    }

    @Test
    public void getSupportedLanguages_OneElement() {
        when(languageRepository.listAll()).thenReturn(getMockLanguageList(1));
        final String result = sut.getSupportedLanguages();
        Assert.assertEquals("Deutsch", result);
    }

    @Test
    public void getSupportedLanguages_TwoElements() {
        when(languageRepository.listAll()).thenReturn(getMockLanguageList(2));
        final String result = sut.getSupportedLanguages();
        Assert.assertEquals("Deutsch, Deutsch", result);
    }

    @Test
    public void getHelloWorldForLanguage() {
        final LanguageEntity entity = new LanguageEntity();
        entity.setHelloWorld("Hello World");
        when(languageRepository.findByLanguage(SupportedLanguagesEnum.ENGLISH)).thenReturn(entity);
        final String result = sut.getHelloWorldForLanguage(SupportedLanguagesEnum.ENGLISH);
        Assert.assertEquals(entity.getHelloWorld(), result);
    }

    private List<LanguageEntity> getMockLanguageList(final int count) {
        final List<LanguageEntity> mockResultList = new ArrayList<LanguageEntity>();

        for (int i = 0; i < count; i++) {
            final LanguageEntity entity = new LanguageEntity();
            entity.setId(String.valueOf(i));
            entity.setLanguage(SupportedLanguagesEnum.GERMAN.toString());
            entity.setHelloWorld("Hallo Welt " + i);
            mockResultList.add(entity);
        }
        return mockResultList;
    }
}
