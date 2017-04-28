package alexa.speechlet;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import helloWorld.constants.IntentsEnum;
import helloWorld.constants.SlotEnum;
import helloWorld.constants.SupportedLanguagesEnum;
import helloWorld.service.ILanguageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

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
        final IntentRequest intentRequest = getIntentRequest(IntentsEnum.HELLO_WORLD, SupportedLanguagesEnum.ENGLISH);

        final SpeechletResponse response = sut.onIntent(intentRequest, sessionMock);
        Assert.assertNotNull(response);
        final String content = ((PlainTextOutputSpeech) response.getOutputSpeech()).getText();
        Assert.assertEquals("Hallo Welt in " + SupportedLanguagesEnum.ENGLISH.toString()
                + " heisst " + helloWorldMockTranslation + ".", content);
    }

    private IntentRequest getIntentRequest(final IntentsEnum intent, final SupportedLanguagesEnum language) {
        final Slot.Builder slotBuilder = Slot.builder().withName(SlotEnum.LANGUAGE.toString());
        slotBuilder.withValue(language.toString());
        final Slot slot = slotBuilder.build();

        final Intent.Builder intentBuilder = Intent.builder();
        intentBuilder.withName(intent.toString());
        final Map<String, Slot> slotMap = new HashMap<String, Slot>();
        slotMap.put(SlotEnum.LANGUAGE.toString(), slot);
        intentBuilder.withSlots(slotMap);
        final IntentRequest.Builder intentRequestBuilder = IntentRequest.builder();
        intentRequestBuilder.withIntent(intentBuilder.build());
        intentRequestBuilder.withRequestId("TestGetIntentRequest");
        return intentRequestBuilder.build();
    }

    // {@link HelloWorldSpeechlet} cannot be mocked with Mockito due to final classes.
    private class HelloWorldSpeechletsTestMock extends HelloWorldSpeechlet {
        public HelloWorldSpeechletsTestMock() {
            super.languageService = languageServiceMock;
        }
    }
}
