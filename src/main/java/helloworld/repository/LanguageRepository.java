package helloworld.repository;

import helloworld.constants.SupportedLanguagesEnum;
import helloworld.entity.LanguageEntity;

import java.util.List;

/**
 * Providing all needed Database Accesses independent of the Database Technology.
 *
 * Created by mimo on 17.05.2017.
 */
public interface LanguageRepository {

    LanguageEntity findByLanguage(SupportedLanguagesEnum language);

    List<LanguageEntity> listAll();
}
