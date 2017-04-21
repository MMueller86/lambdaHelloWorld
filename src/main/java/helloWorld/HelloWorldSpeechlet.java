package helloWorld;

import helloWorld.constants.IntentsEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 */
public class HelloWorldSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);
    private final ResourceBundle bundle = ResourceBundle.getBundle("speech", Locale.GERMAN);

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return createSpeechletAskResponse("txtWelcome", "lblHelloWorld");
    }

    @Override
    /**
     * No Java Doc. {@link Speechlet}
     */
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;
        log.info("IntentName = " + intentName);

        if (IntentsEnum.HELLO_WORLD.toString().equals(intentName)) {
            return getHelloResponse();
        } else if (IntentsEnum.HELP.toString().equals(intentName)) {
            return createSpeechletAskResponse("txtHelp", "lblHelp");
        } else if (IntentsEnum.START.toString().equals(intentName)) {
            return createSpeechletAskResponse("txtWelcome", "lblHelloWorld");
        } else {
            log.warn("Unknown Intent: Return unknown text");
            return createSpeechletAskResponse("txtUnknown", "lblUknown");
        }
    }

    @Override
    /**
     * No Java Doc. {@link Speechlet}
     */
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any cleanup logic goes here
    }

    /**
     * Creates a TellResponse {@code SpeechletResponse}.
     *
     * @return SpeechletResponse spoken and visual response.
     */
    private SpeechletResponse getHelloResponse() {
        String speechText = bundle.getString("txtHelloWorld");

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle(bundle.getString("lblHelloWorld"));
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    /*
    Creates an {@code SpeechletResponse} Speechleet askResponse spoken and visual.
     */
    private SpeechletResponse createSpeechletAskResponse(String text, String title) {
        String speechText = bundle.getString(text);

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle(bundle.getString(title));
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}