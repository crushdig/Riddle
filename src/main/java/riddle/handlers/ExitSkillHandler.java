/**
 * ExitSkillHandler is used to end the game session.
 */
package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import riddle.model.Constants;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ExitSkillHandler implements RequestHandler {

    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent")
                .or(intentName("AMAZON.PauseIntent")
                        .or(intentName("AMAZON.CancelIntent"))));
    }

    /**
     * Returns a response using the builder object
     * @param input the user speech as input
     * @return a response
     */
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder().withSpeech(Constants.EXIT_SKILL_MESSAGE).build();
    }

}
