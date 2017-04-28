package helloWorld.service;

import helloWorld.constants.SupportedLanguagesEnum;

/**
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
