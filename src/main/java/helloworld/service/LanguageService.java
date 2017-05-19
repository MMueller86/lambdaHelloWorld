package helloworld.service;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;
import helloworld.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mimo on 26.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class LanguageService implements ILanguageService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public String getSupportedLanguages() {
        final List<LanguageEntity> languageList = languageRepository.findAll();
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
