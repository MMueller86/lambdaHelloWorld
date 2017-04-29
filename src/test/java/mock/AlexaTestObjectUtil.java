package mock;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import helloWorld.constants.IntentsEnum;
import helloWorld.constants.SlotEnum;
import helloWorld.constants.SupportedLanguagesEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mimo on 29.04.2017.
 */
public class AlexaTestObjectUtil {

    public static IntentRequest getIntentRequest(final IntentsEnum intent, final SupportedLanguagesEnum language) {
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

    public static Session getSession() {
        final Session.Builder session = Session.builder();
        session.withSessionId("SessionID");

        return session.build();
    }
}
