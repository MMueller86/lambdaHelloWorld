package util;

import helloWorld.util.ResourceBundleParamUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mimo on 21.04.2017.
 */
public class ResourceBundleParamUtilTest {

    @Test
    public void resourceBundleGetStringWithoutParam() {
        final String value = ResourceBundleParamUtil.getString("lblHelloWorld");
        Assert.assertEquals("Hallo Welt", value);
    }

    @Test
    public void resourceBundleGetNotExistingString() {
        final String value = ResourceBundleParamUtil.getString("NOT_EXISTING");
        Assert.assertEquals("!NOT_EXISTING!", value);
    }

    @Test
    public void resourceBundleGetStringWithParam() {
        final String language = "English";
        final String helloWorldTranslated = "Hello World";
        final String value = ResourceBundleParamUtil.getString("txtHelloWorld", language, helloWorldTranslated);
        Assert.assertEquals("Hallo Welt in " + language + " heisst " + helloWorldTranslated + ".", value);
    }
}
