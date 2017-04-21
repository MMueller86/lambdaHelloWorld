import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by mimo on 21.04.2017.
 */
public class LoadResourceBundleTest {

    @Rule public ExpectedException thrown= ExpectedException.none();
    @Test
    public void ressourceBundleExists() {
        final String bundleName = "speech";
        final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, Locale.GERMAN);
        Assert.assertNotNull("RessourceBundle "  + bundleName + "not found", bundle);

        thrown.expect(MissingResourceException.class);
        thrown.expectMessage("Can't find bundle for base name NOT_EXISTING, locale de");
        ResourceBundle.getBundle("NOT_EXISTING", Locale.GERMAN);
    }
}
