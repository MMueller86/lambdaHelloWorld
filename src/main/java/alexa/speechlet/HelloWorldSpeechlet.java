package alexa.speechlet;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import helloworld.constants.IntentsEnum;
import helloworld.constants.SlotEnum;
import helloworld.constants.SupportedLanguagesEnum;
import helloworld.service.ILanguageService;
import helloworld.service.LanguageService;
import helloworld.util.ResourceBundleParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Contains all methods to handle the Alexa communication.
 * This class is used to get the events (intens) from Alexa and call the
 * Alexa independent backend. The backend result is transferred to an alexa understandable response.
 */
public class HelloWorldSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);
    @Autowired
    protected ILanguageService languageService;
    protected ApplicationContext context;

    /**
     * By using this constructor you have to initialize the Spring Context as well as the needed services on your own!
     */
    protected HelloWorldSpeechlet() {
    }

    /**
     * Initializes the Spring Context...
     *
     * @param springConfigFileName Filename of the Spring Configuration. The file needs to be set on the classpath!
     */
    public HelloWorldSpeechlet(final String springConfigFileName) {
        log.info("start Spring");
        context = new FileSystemXmlApplicationContext
                ("classpath:" + springConfigFileName);
        languageService = (LanguageService) context.getBean("languageService");
        log.info("spring started");
    }

    private IntentsEnum getIntentOfRequest(final IntentRequest request) {
        final Intent intent = request.getIntent();
        final String intentName = (intent != null) ? intent.getName() : null;
        log.info("IntentName = " + intentName);
        return IntentsEnum.convertFromString(intentName);
    }

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
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {

        final SpeechletResponse result;
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        final IntentsEnum intent = getIntentOfRequest(request);

        switch (intent) {
            case HELLO_WORLD:
                result = handleHelloWorld(request);
                break;
            case HELP:
                result = createSpeechletAskResponse("txtHelp", "lblHelp");
                break;
            case START:
                result = createWelcomeAskResponse();
                break;
            default:
                log.warn("Unknown Intent: Return unknown text");
                result = createSpeechletAskResponse("txtUnknown", "lblUknown");
        }
        return result;
    }

    private SpeechletResponse handleHelloWorld(final IntentRequest request) {
        SpeechletResponse result;
        final Slot languageSlot = request.getIntent().getSlot(SlotEnum.LANGUAGE.toString());
        log.info("Intent " + IntentsEnum.HELLO_WORLD.toString() + "found. Slot = " + languageSlot);
        try {
            final SupportedLanguagesEnum requestedLanguage = SupportedLanguagesEnum.convertFromString(languageSlot.getValue());
            result = getHelloResponse(requestedLanguage);
        } catch (final IllegalArgumentException e) {
            log.warn("Language " + languageSlot.getValue() + " not supported");
            //TODO: Create own event...
            result = createSpeechletAskResponse("txtUnknown", "lblUknown");
        }
        return result;
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
    private SpeechletResponse getHelloResponse(final SupportedLanguagesEnum language) {
        final String translatedHelloWorld = languageService.getHelloWorldForLanguage(language);
        final String speechText = ResourceBundleParamUtil.getString(
                "txtHelloWorld", language.toString(), translatedHelloWorld);

        // Create the Simple card content.
        final SimpleCard card = new SimpleCard();
        card.setTitle(ResourceBundleParamUtil.getString("lblHelloWorld"));
        card.setContent(speechText);

        // Create the plain text output.
        final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    /*
    Creates an {@code SpeechletResponse} Speechleet askResponse spoken and visual.
     */
    private SpeechletResponse createSpeechletAskResponse(final String text, final String title) {
        final String speechText = ResourceBundleParamUtil.getString(text);

        // Create the Simple card content.
        final SimpleCard card = new SimpleCard();
        card.setTitle(ResourceBundleParamUtil.getString(title));
        card.setContent(speechText);

        // Create the plain text output.
        final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        final Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse createWelcomeAskResponse() {
        final String supportedLanguages = languageService.getSupportedLanguages();
        final String speechText = ResourceBundleParamUtil.getString("txtWelcome", supportedLanguages);

        // Create the Simple card content.
        final SimpleCard card = new SimpleCard();
        card.setTitle(ResourceBundleParamUtil.getString("lblWelcome"));
        card.setContent(speechText);

        // Create the plain text output.
        final PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        final Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}