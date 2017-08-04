package helloworld.repository;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;

import java.util.List;

/**
 * Created by mimo on 17.05.2017.
 */
public interface LanguageRepository {

    LanguageEntity findByLanguage(SupportedLanguagesEnum language);

    List<LanguageEntity> listAll();
}
