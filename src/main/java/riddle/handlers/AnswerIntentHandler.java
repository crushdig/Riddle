/**
 * AnswerIntentHandler is used to setup the intent handler for a player's correct or incorrect answer.
 */
package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.fasterxml.jackson.databind.ObjectMapper;

import riddle.Util.RiddleUtils;
import riddle.model.Attributes;
import riddle.model.Constants;
import riddle.model.Person;
import riddle.model.PersonProperty;

import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;
import static riddle.Util.RiddleUtils.getPropertyOfPerson;


public class AnswerIntentHandler implements RequestHandler
{
    private static final Random RANDOM = new Random();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    @Override
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(intentName("AnswerIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE)));
    }

    /**
     * Returns a response using the builder object
     * @param input the user speech as input
     * @return a response
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        boolean correctAnswer;
        String speechText, response;

        Map<String, String> riddleItem = (LinkedHashMap<String, String>)sessionAttributes.get(Attributes.RIDDLE_ITEM_KEY);
        Person person = MAPPER.convertValue(riddleItem, Person.class);

        PersonProperty personProperty = PersonProperty.valueOf((String) sessionAttributes.get(Attributes.RIDDLE_PROPERTY_KEY));


        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
        int riddleGameScore = (int) sessionAttributes.get(Attributes.RIDDLE_SCORE_KEY);


        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        correctAnswer = compareSlots(intentRequest.getIntent().getSlots(), getPropertyOfPerson(personProperty, person));

        if(correctAnswer)
        {
            riddleGameScore++;
            response = getSpeechExpressionCon(true);

            sessionAttributes.put(Attributes.RIDDLE_SCORE_KEY, riddleGameScore);
        }
        else
        {
            response = getSpeechExpressionCon(false);
        }

        response += getAnswerText(personProperty, person, correctAnswer);


        if(counter < 5)
        {
            response += "Your current score is " + riddleGameScore + " out of " + counter + ". ";
            sessionAttributes.put(Attributes.RESPONSE_KEY, response);
            Optional<Response> generateRiddle = null;

            generateRiddle = RiddleUtils.generateSentence(input);

            return generateRiddle;
        }
        else
        {
            response += "Your final score is " + riddleGameScore + " out of " + counter + ". ";
            speechText = response + " " + Constants.EXIT_SKILL_MESSAGE;

            return input.getResponseBuilder()
                    .withSimpleCard("RiddleSession", speechText)
                    .withSpeech(speechText)
                    .withShouldEndSession(true)
                    .build();
        }
    }

    /**
     * Returns a response depending on the condition.
     * @param personProperty the property of a character
     * @param person the person who is associated with certain properties
     * @param correctAnswer the boolean representing whether an answer is correct or not
     * @return a response dependent of a correct or incorrect answer
     */
    public String getAnswerText(PersonProperty personProperty, Person person, boolean correctAnswer) {
        switch(personProperty) {
            case CHARACTER:
                if(correctAnswer)
                {
                    return getRandomItem(Constants.CORRECT_ANSWER_RESPONSES) + personProperty.getValue() + "! I'm the one and only " + person.getCharacter() + ". ";
                }
                else
                {
                    return getRandomItem(Constants.INCORRECT_ANSWER_RESPONSES) + personProperty.getValue() + "! I'm the one and only " + person.getCharacter() + ". ";

                }
            default:
                return "I am The one and only " + person.getCharacter();
        }
    }

    /**
     * Returns a speech expression.
     * @param correctAnswer the boolean representing whether an answer is correct or not
     * @return a speech expression from a list at random
     */
    private String getSpeechExpressionCon(boolean correctAnswer) {
        if (correctAnswer) {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.CORRECT_RESPONSES) + "! </say-as><break strength='strong'/>";
        } else {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.INCORRECT_RESPONSES) + " </say-as><break strength='strong'/>";
        }
    }

    /**
     * Returns a random item.
     * @param list the list of items of the Item object
     * @param <T>
     * @return
     */
    private <T> T getRandomItem(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    /**
     * Returns a boolean if answer is present in the slot
     * @param slots the String associated with a specific Amazon slot
     * @param correctAnswer the boolean representing whether an answer is correct or not
     * @return a boolean object
     */
    private boolean compareSlots( Map<String, Slot> slots, Vector<String> correctAnswer) {
        for (Slot slot : slots.values()) {
            for(int i = 0; i < correctAnswer.size(); i++)
            {
                if (slot.getValue() != null && (slot.getValue().toLowerCase().equals(correctAnswer.elementAt(i).toLowerCase()))) {
                    return true;
                }
            }
        }
        return false;
    }
}