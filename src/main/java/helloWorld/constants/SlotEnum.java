package helloWorld.constants;

/**
 * Created by mimo on 27.04.2017.
 */
public enum SlotEnum {
    LANGUAGE("language");

    private final String text;

    SlotEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}