package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.fasterxml.jackson.databind.ObjectMapper;
import riddle.model.Attributes;
import riddle.model.Person;
import riddle.model.PersonProperty;
import riddle.riddle_Generator.GenerateRiddles;
import riddle.riddle_Generator.Riddle;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;
import static riddle.Util.RiddleUtils.getPropertyOfPerson;

public class GiveAnswerIntentHandler implements RequestHandler {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Riddle riddle;
    private GenerateRiddles riddles;

    public GiveAnswerIntentHandler() throws IOException {
        riddles = new GenerateRiddles();
        riddle = riddles.randomiseRiddles();
    }

    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GiveAnswerIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE)));
    }

    @Override
    public Optional< Response > handle(HandlerInput input) {
        Map< String, Object > sessionAttributes = input.getAttributesManager().getSessionAttributes();

        boolean correctAnswer;
        String speechText, response;

        Map< String, String > riddleItem = (LinkedHashMap< String, String >) sessionAttributes.get(Attributes.RIDDLE_ITEM_KEY);
        Person person = MAPPER.convertValue(riddleItem, Person.class);

        PersonProperty personProperty = PersonProperty.valueOf((String) sessionAttributes.get(Attributes.RIDDLE_PROPERTY_KEY));

        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
        int riddleGameScore = (int) sessionAttributes.get(Attributes.RIDDLE_SCORE_KEY);

        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        correctAnswer = compareSlots(intentRequest.getIntent().getSlots(), getPropertyOfPerson(personProperty, person));


        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(true)
                .build();
    }

    private boolean compareSlots(Map< String, Slot > slots, Vector< String > correctAnswer) {
        for (Slot slot : slots.values()) {
            if (slot.getValue() != null && slot.getValue().toLowerCase().equals(correctAnswer.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}