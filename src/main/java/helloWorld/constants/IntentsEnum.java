package helloWorld.constants;

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

    IntentsEnum(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
