package alexa.speechlet;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import helloWorld.constants.IntentsEnum;
import helloWorld.constants.SupportedLanguagesEnum;
import helloWorld.service.ILanguageService;
import mock.AlexaTestObjectUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test Class for {@link HelloWorldSpeechlet}
 * Created by mimo on 27.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class HelloWorldSpeechletTest {

    private HelloWorldSpeechlet sut;
    @Mock
    private Session sessionMock;
    @Mock
    private ILanguageService languageServiceMock;

    @Before
    public void setUp() {
        sut = new HelloWorldSpeechletsTestMock();
    }

    @Test
    public void onIntent_HelloWorld() throws SpeechletException {
        final String helloWorldMockTranslation = "Das ist ein Test";
        Mockito.when(sessionMock.getSessionId()).thenReturn("sessionID");
        Mockito.when(languageServiceMock.getHelloWorldForLanguage(
                SupportedLanguagesEnum.ENGLISH)).thenReturn(helloWorldMockTranslation);
        final IntentRequest intentRequest = AlexaTestObjectUtil.getIntentRequest(IntentsEnum.HELLO_WORLD, SupportedLanguagesEnum.ENGLISH);

        final SpeechletResponse response = sut.onIntent(intentRequest, sessionMock);
        Assert.assertNotNull(response);
        final String content = ((PlainTextOutputSpeech) response.getOutputSpeech()).getText();
        Assert.assertEquals("Hallo Welt in " + SupportedLanguagesEnum.ENGLISH.toString()
                + " heisst " + helloWorldMockTranslation + ".", content);
    }

    // {@link HelloWorldSpeechlet} cannot be mocked with Mockito due to final classes.
    private class HelloWorldSpeechletsTestMock extends HelloWorldSpeechlet {
        public HelloWorldSpeechletsTestMock() {
            super.languageService = languageServiceMock;
        }
    }
}
