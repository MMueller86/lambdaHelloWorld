package helloWorld.service;

import helloWorld.constants.SupportedLanguagesEnum;
import helloWorld.dao.ILanguageDAO;
import helloWorld.entity.LanguageEntity;
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
        String result = "";
        for (final Iterator<LanguageEntity> iterator = languageList.iterator(); iterator.hasNext(); ) {
            String supportedLanguage = iterator.next().getLanguage();
            if (iterator.hasNext()) {
                supportedLanguage += ", ";
            }
            result += supportedLanguage;
        }
        return result;
    }


    @Override
    public String getHelloWorldForLanguage(final SupportedLanguagesEnum language) {
        return languageDao.getLanguage(language).getHelloWorld();
    }

    public void setLanguageDao(final ILanguageDAO languageDao) {
        this.languageDao = languageDao;
    }
}
