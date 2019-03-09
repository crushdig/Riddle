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

public class RiddleAndStartOverIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetRiddleIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE).negate()))
                || input.matches(intentName("AMAZON.StartOverIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE);
        sessionAttributes.put(Attributes.RESPONSE_KEY, "");
        sessionAttributes.put(Attributes.COUNTER_KEY, 0);
        sessionAttributes.put(Attributes.RIDDLE_SCORE_KEY, 0);

        return RiddleUtils.generateSentence(input);
    }

}
