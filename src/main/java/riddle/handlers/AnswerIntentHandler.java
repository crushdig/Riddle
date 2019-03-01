package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import riddle.Util.RiddleUtils;
import riddle.model.Attributes;
import riddle.model.Constants;
import riddle.model.Person;
import riddle.model.PersonProperty;
import riddle.generator.GenerateRiddles;
import riddle.generator.Riddle;


import java.io.IOException;
import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;
import static org.slf4j.LoggerFactory.getLogger;
import static riddle.Util.RiddleUtils.getPropertyOfPerson;

public class AnswerIntentHandler implements RequestHandler {
    private static final Random RANDOM = new Random();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Riddle riddle;
    private GenerateRiddles riddles;

    private static Logger LOG = getLogger(AnswerIntentHandler.class);

    public AnswerIntentHandler() {
        riddles = new GenerateRiddles();
        riddle = riddles.randomiseRiddles();
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AnswerIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE)));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        System.out.println("This a FIRST debug");
        LOG.debug("This a FIRST debug");

        boolean correctAnswer;
        String speechText = null, response;
        System.out.println("This a SECOND debug");


        Map<String, String> riddleItem = (LinkedHashMap<String, String>)sessionAttributes.get(Attributes.RIDDLE_ITEM_KEY);
        Person person;

        System.out.println("riddleItem " + riddleItem);
        person = MAPPER.convertValue(riddleItem, Person.class); // ERROR OCCURS ON THIS LINE

        System.out.println("This a THIRD debug");
//
        PersonProperty personProperty = PersonProperty.valueOf((String) sessionAttributes.get(Attributes.RIDDLE_PROPERTY_KEY));
//        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
//        int riddleGameScore = (int) sessionAttributes.get(Attributes.RIDDLE_SCORE_KEY);
//        System.out.println("This a FOURTH debug");
//
//        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
//        correctAnswer = compareSlots(intentRequest.getIntent().getSlots(), getPropertyOfPerson(personProperty, person));
//        System.out.println("This a FIFTH debug " + correctAnswer);
//
//        if(correctAnswer)
//        {
//            riddleGameScore++;
//            response = getSpeechExpressionCon(true);
//            System.out.println("This a SIXTH debug " + response);
//
//            sessionAttributes.put(Attributes.RIDDLE_SCORE_KEY, riddleGameScore);
//        }
//        else
//        {
//            response = getSpeechExpressionCon(false);
//            System.out.println("This a SEVENTH debug " + response);
//
//        }
//
//        AnswerIntentHandler setup = new AnswerIntentHandler();
//
        if(riddle.getAnswer() != null)
        {
            speechText = "Hello " + riddle.getAnswer();
        }

        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(true)
                .build();

//
//        response += getAnswerText(personProperty, person);
//        System.out.println("This a EIGHT debug " + response);
//
//
//        if(counter < 10)
//        {
//            response += "Your current score is " + riddleGameScore + " out of " + counter + ". ";
//            sessionAttributes.put(Attributes.RESPONSE_KEY, response);
//            Optional<Response> generateRiddle = null;
//
//            generateRiddle = RiddleUtils.generateSentence(input);
//            System.out.println("This a NINTH debug " + generateRiddle);
//
//            return generateRiddle;
//        }
//        else
//        {
//            response += "Your final score is " + riddleGameScore + " out of " + counter + ". ";
//            speechText = response + " " + Constants.EXIT_SKILL_MESSAGE;
//
//            return input.getResponseBuilder()
//                    .withSimpleCard("RiddleSession", speechText)
//                    .withSpeech(speechText)
//                    .withShouldEndSession(true)
//                    .build();
//        }

//       return input.getResponseBuilder()
//                .withSimpleCard("RiddleSession", speechText)
//                .withSpeech(speechText)
//                .withShouldEndSession(true)
//                .build();
    }

    public Person getPerson()
    {
        return riddle.getPerson();
    }


    public String getAnswerText(PersonProperty personProperty, Person person) {
        switch(personProperty) {
            case CHARACTER:
                return "I am an interesting " + personProperty.getValue() + ". The one and only " + person.getCharacter();
            default:
                return "I am The one and only " + person.getCharacter();
        }
    }

//    public String getAnswerText(PersonProperty personProperty, Person person) {
//        switch(personProperty) {
//            case CHARACTER:
//                for(String character: person.getCharacter())
//                {
//                    if(character.equals(riddle.getAnswer()))
//                    {
//                        return "I am an interesting " + personProperty.getValue() + ". The one and only " + character;
//                    }
//                }
//            default:
//                return "I am The one and only " + person.getCharacter();
//        }
//    }

    private String getSpeechExpressionCon(boolean correctAnswer) {
        if (correctAnswer) {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.CORRECT_RESPONSES) + "! </say-as><break strength='strong'/>";
        } else {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.INCORRECT_RESPONSES) + " </say-as><break strength='strong'/>";
        }
    }

    private <T> T getRandomItem(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    private boolean compareSlots(Map<String, Slot> slots, Vector<String> correctAnswer) {
        for (Slot slot : slots.values()) {
            for(int i = 0; i < correctAnswer.size(); i++)
            {
                if (slot.getValue() != null && slot.getValue().toLowerCase().equals(correctAnswer.elementAt(i).toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}