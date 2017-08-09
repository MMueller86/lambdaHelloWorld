package helloworld.service;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import helloworld.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Implementation of {@link ILanguageService}.
 *
 * Created by mimo on 26.04.2017.
 */
@Service
public class LanguageService implements ILanguageService {


    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public String getSupportedLanguages() {
        final List<LanguageEntity> languageList = languageRepository.listAll();
        final StringBuilder sb = new StringBuilder();
        for (final Iterator<LanguageEntity> iterator = languageList.iterator(); iterator.hasNext(); ) {
            sb.append(iterator.next().getLanguage());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }


    @Override
    public String getHelloWorldForLanguage(final SupportedLanguagesEnum language) {
        return languageRepository.findByLanguage(language).getHelloWorld();
    }
}
