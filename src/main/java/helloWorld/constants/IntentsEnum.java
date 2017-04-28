package helloWorld.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum containing all supported Intents
 *
 * Created by mimo on 20.04.2017.
 */
public enum IntentsEnum {
    HELLO_WORLD("HelloWorldIntent"),
    HELP("AMAZON.HelpIntent"),
    START("StartIntent");

    private final String text;

    IntentsEnum(final String constant) {
        this.text = constant;
        IntentsEnum.Holder.INTENT_MAP.put(constant, this);
    }

    /**
     * Returns the Enum for the given String constant.
     *
     * @param constant: String searched in the enum as constant.
     * @return The corresponding {@link IntentsEnum}.
     * @throws IllegalArgumentException if constant not supported by the {@link IntentsEnum}
     */
    public static IntentsEnum convertFromString(final String constant) {
        final IntentsEnum constantEnum = IntentsEnum.Holder.INTENT_MAP.get(constant);
        if (null == constantEnum) {
            throw new IllegalArgumentException("String " + constant + "not supported in Enum "
                    + IntentsEnum.class.getName());
        }
        return constantEnum;
    }

    @Override
    public String toString() {
        return text;
    }

    private static class Holder {
        static Map<String, IntentsEnum> INTENT_MAP = new HashMap<>();
    }
}
