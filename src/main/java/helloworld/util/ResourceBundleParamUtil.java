package helloworld.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Util to handle the 'speech' resource Bundle.
 *
 * Created by mimo on 24.04.2017.
 */
public class ResourceBundleParamUtil {

    // property file is: package/name/messages.properties
    private static final String BUNDLE_NAME = "speech";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.GERMAN);

    private ResourceBundleParamUtil() {
        throw new IllegalArgumentException("Not supported it is a Util");
    }

    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (final MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public static String getString(final String key, final Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (final MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
