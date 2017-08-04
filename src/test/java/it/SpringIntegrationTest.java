package it;

import alexa.speechlet.HelloWorldSpeechlet;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.github.klousiaj.junit.DockerRule;
import dynamo.DynamoTestSupport;
import helloworld.constants.IntentsEnum;
import helloworld.constants.SupportedLanguagesEnum;
import mock.AlexaTestObjectUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mimo on 26.04.2017.
 */
public class SpringIntegrationTest extends DynamoTestSupport {

    private static HelloWorldSpeechlet sut;

    @ClassRule
    public static DockerRule rabbitRule =
            DockerRule.builder()
                    .image("ryanratcliff/dynamodb")
                    .ports("5672", ":32779", "8000:8000")
                    .build();

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
