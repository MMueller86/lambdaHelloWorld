package constant;

import helloworld.constants.SupportedLanguagesEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mimo on 28.04.2017.
 */
public class SupportedLanguageEnumTest {

    @Test
    public void convertFromString_success() {
        final SupportedLanguagesEnum result = SupportedLanguagesEnum.convertFromString(SupportedLanguagesEnum.GERMAN.toString());
        Assert.assertEquals(result, SupportedLanguagesEnum.GERMAN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertFromString_NotSupported() {
        SupportedLanguagesEnum.convertFromString("Not_EXISTING");
    }
}
