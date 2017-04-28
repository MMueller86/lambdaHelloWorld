package helloWorld.dao;

import helloWorld.constants.SupportedLanguagesEnum;
import helloWorld.entity.LanguageEntity;

import java.util.List;

/**
 * Created by mimo on 25.04.2017.
 */
public interface ILanguageDAO {

    /**
     * List all Languages.
     *
     * @return <code>List<LanguageEntity></code>
     */
    List<LanguageEntity> listLanguages();

    /**
     * Returns the requested {@link LanguageEntity}.
     *
     * @param <{@link SupportedLanguagesEnum}
     * @return
     */
    LanguageEntity getLanguage(final SupportedLanguagesEnum language);
}
