/**
 * RiddleStartOverIntentHandler is used to start the game over when requested for during the game session.
 */
package riddle.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import riddle.Util.RiddleUtils;
import riddle.model.Attributes;


public class RiddleAndStartOverIntentHandler implements RequestHandler
{
    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(intentName("GetRiddleIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE).negate()))
                || input.matches(intentName("AMAZON.StartOverIntent"));
    }

    /**
     * Returns a response using the builder object
     * @param input the user speech as input
     * @return a response
     */
    @Override
    public Optional<Response> handle(HandlerInput input)
    {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE);
        sessionAttributes.put(Attributes.RESPONSE_KEY, "");
        sessionAttributes.put(Attributes.COUNTER_KEY, 0);
        sessionAttributes.put(Attributes.RIDDLE_SCORE_KEY, 0);

        return RiddleUtils.generateSentence(input);
    }
}
