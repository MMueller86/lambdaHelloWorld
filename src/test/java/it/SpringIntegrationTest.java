package it;

import alexa.speechlet.HelloWorldSpeechlet;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import helloworld.constants.IntentsEnum;
import helloworld.constants.SupportedLanguagesEnum;
import mock.AlexaTestObjectUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by mimo on 26.04.2017.
 */
//@ContextConfiguration(locations = {"classpath:spring_test.xml"})
public class SpringIntegrationTest {

    private static HelloWorldSpeechlet sut;

    @BeforeClass
    public static void setUp() {
        sut = new HelloWorldSpeechlet("spring_test.xml");
    }

    @Test
    public void HelloWorldSpeechletIntegration_HelloWorld_English() throws SpeechletException {
        final IntentRequest request = AlexaTestObjectUtil.getIntentRequest(IntentsEnum.HELLO_WORLD, SupportedLanguagesEnum.ENGLISH);
        final Session session = AlexaTestObjectUtil.getSession();
        final PlainTextOutputSpeech result = (PlainTextOutputSpeech) sut.onIntent(request, session).getOutputSpeech();
        Assert.assertNotNull(result.getText());
        Assert.assertEquals("Hallo Welt in Englisch heisst Hello World.", result.getText());
    }

    @Test
    public void HelloWorldSpeechletIntegration_HelloWorld_German() throws SpeechletException {
        final IntentRequest request = AlexaTestObjectUtil.getIntentRequest(IntentsEnum.HELLO_WORLD, SupportedLanguagesEnum.GERMAN);
        final Session session = AlexaTestObjectUtil.getSession();
        final PlainTextOutputSpeech result = (PlainTextOutputSpeech) sut.onIntent(request, session).getOutputSpeech();
        Assert.assertNotNull(result.getText());
        Assert.assertEquals("Hallo Welt in Deutsch heisst Hallo Welt.", result.getText());
    }
}
