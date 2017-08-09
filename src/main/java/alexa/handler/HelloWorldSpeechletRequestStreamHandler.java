package alexa.handler;


import alexa.speechlet.HelloWorldSpeechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * This class could be the alexa.handler for an AWS Lambda function powering an Alexa Skills Kit
 * experience. To do this, simply set the alexa.handler field in the AWS Lambda console to
 * "HelloWorldSpeechletRequestStreamHandler".
 * <p>
 * For this to work, you'll also need to build
 * this project using maven shade plugin (fat jar) and upload the resulting jar file to power
 * your function.
 *
 * Implementation of the abstract class {@link SpeechletRequestStreamHandler}.
 * Setting the {@link HelloWorldSpeechlet} into the Handler in the constructor.
 *
 */
public final class HelloWorldSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<>();

    static {
        /*
        
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        /* supportedApplicationIds.add("ADD_ID_HERE"); */

    }

    /**
     * Setting {@link HelloWorldSpeechlet} by overwriting the constructor.
     */
    public HelloWorldSpeechletRequestStreamHandler() {
        super(new HelloWorldSpeechlet("spring.xml"), supportedApplicationIds);
    }
}
