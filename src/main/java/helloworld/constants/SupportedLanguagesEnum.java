package helloworld.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum containing all supported languages
 * <p>
 * Created by mimo on 27.04.2017.
 */
public enum SupportedLanguagesEnum {
    GERMAN("Deutsch"),
    ENGLISH("Englisch"),
    UNKNOWN("Unbekannt");

    private final String text;

    SupportedLanguagesEnum(final String text) {
        this.text = text;
        Holder.codeMap.put(text.toUpperCase(), this);
    }

    /**
     * Returns the Enum for the given String constant.
     *
     * @param constant: String searched in the enum as constant.
     * @return The corresponding {@link SupportedLanguagesEnum}.
     * @throws IllegalArgumentException if constant not supported by the {@link SupportedLanguagesEnum}
     */
    public static SupportedLanguagesEnum convertFromString(final String constant) {
        final SupportedLanguagesEnum constantEnum = Holder.codeMap.get(constant.toUpperCase());
        if (null == constantEnum) {
            throw new IllegalArgumentException("String " + constant + "not supported in Enum "
                    + SupportedLanguagesEnum.class.getName());
        }
        return constantEnum;
    }

    @Override
    public String toString() {
        return text;
    }

    private static class Holder {

        static Map<String, SupportedLanguagesEnum> codeMap = new HashMap<>();

        private Holder() {
            throw new IllegalArgumentException("not supported");
        }
    }
}
