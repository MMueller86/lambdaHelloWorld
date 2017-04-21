package helloWorld;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by mimo on 21.04.2017.
 */
public class Main {

    public static void main(String [] args)
    {
        final ResourceBundle bundle = ResourceBundle.getBundle("speech", Locale.GERMAN);
        bundle.getString("lblHelloWorld");
    }
}
