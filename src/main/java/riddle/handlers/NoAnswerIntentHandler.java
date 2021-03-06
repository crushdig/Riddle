/**
 * NoAnswerIntentHandler is used to give further assistance when an unknown answer is given.
 */
package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import riddle.model.Attributes;
import riddle.model.Constants;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class NoAnswerIntentHandler implements RequestHandler
{

    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AnswerIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE).negate()));
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
        sessionAttributes.put(Attributes.RIDDLE_STATE_KEY, Attributes.START_STATE);

        String unknownAnswerText = "I'm sorry. That is not something I know very much about in this skill. " + Constants.HELP_MESSAGE;
        return input.getResponseBuilder()
                .withSpeech(unknownAnswerText)
                .withReprompt(unknownAnswerText)
                .withShouldEndSession(false)
                .build();
    }
}
