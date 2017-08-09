package helloworld.service;

import helloworld.constants.SupportedLanguagesEnum;

/**
 * Service-Interface of the UI. All Hello World and language functionality are
 * encapsulated by this Interface.
 * Created by mimo on 26.04.2017.
 */
public interface ILanguageService {

    /**
     * Gets a <code>String</code> with all supported languages comma separated.
     *
     * @return languages comma sperated.
     */
    String getSupportedLanguages();

    /**
     * Gets the translated 'Hello Word' of the given language.
     *
     * @param {@link SupportedLanguagesEnum}language
     * @return {@link String} translated HelloWorld
     */
    String getHelloWorldForLanguage(final SupportedLanguagesEnum language);
}
