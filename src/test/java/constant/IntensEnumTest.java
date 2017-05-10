package constant;

import helloworld.constants.IntentsEnum;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mimo on 28.04.2017.
 */
public class IntensEnumTest {

    @Test
    public void convertFromString_success() {
        final IntentsEnum result = IntentsEnum.convertFromString(IntentsEnum.HELLO_WORLD.toString());
        Assert.assertEquals(result, IntentsEnum.HELLO_WORLD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertFromString_NotSupported() {
        IntentsEnum.convertFromString("Not_EXISTING");
    }
}
