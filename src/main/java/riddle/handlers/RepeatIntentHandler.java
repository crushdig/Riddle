package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import riddle.model.Attributes;
import riddle.model.Person;
import riddle.model.PersonProperty;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;
import static riddle.Util.RiddleUtils.getQuestionText;

public class RepeatIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.RepeatIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE)));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
        PersonProperty personProperty = (PersonProperty) sessionAttributes.get(Attributes.RIDDLE_PROPERTY_KEY);
        Person person = (Person) sessionAttributes.get(Attributes.RIDDLE_ITEM_KEY);

        String question = getQuestionText(counter);
        return input.getResponseBuilder()
                .withSpeech(question)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();
    }

}
