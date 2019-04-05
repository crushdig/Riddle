/**
 * SkipIntentHandler is used to skip a riddle in the game when requested for during the game session.
 */
package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import riddle.Util.RiddleUtils;
import riddle.model.Attributes;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class SkipIntentHandler implements RequestHandler {

    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SkipIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE)));
    }

    /**
     * Returns a response using the builder object
     * @param input the user speech as input
     * @return a response
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);

        String question = RiddleUtils.setupSessionAttributes(sessionAttributes, counter);
        String speech=  "No Problem!" + question;

        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speech)
                .withSpeech(speech)
                .withReprompt(speech)
                .withShouldEndSession(false)
                .build();
    }
}
