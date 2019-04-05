/**
 * LaunchRequestHandler is used to start the game session.
 */
package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import riddle.model.Attributes;
import riddle.model.Constants;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler 
{
    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
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
        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", Constants.WELCOME_MESSAGE)
                .withSpeech(Constants.WELCOME_MESSAGE)
                .withReprompt(Constants.HELP_MESSAGE)
                .withShouldEndSession(false)
                .build();
    }
}
