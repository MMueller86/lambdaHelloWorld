package helloworld.service;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.dao.ILanguageDAO;
import helloworld.entity.LanguageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by mimo on 26.04.2017.
 */
@Service
public class LanguageService implements ILanguageService {
    @Autowired
    private ILanguageDAO languageDao;

    @Override
    public String getSupportedLanguages() {
        final List<LanguageEntity> languageList = languageDao.listLanguages();
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
        return languageDao.getLanguage(language).getHelloWorld();
    }

    public void setLanguageDao(final ILanguageDAO languageDao) {
        this.languageDao = languageDao;
    }
}
