package riddle.Util;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import riddle.model.Attributes;
import riddle.model.Person;
import riddle.model.PersonProperty;
import riddle.riddle_Generator.GenerateRiddles;
import riddle.riddle_Generator.Riddle;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;

import static riddle.model.Constants.START_RIDDLE_GAME_MESSAGE;

public class RiddleUtils
{
    private Riddle riddle;
    private Person person;
    private GenerateRiddles riddles;


    public RiddleUtils() throws IOException {
        riddles = new GenerateRiddles();
        riddle = riddles.randomiseRiddles();
//        person =

    }
    public static Optional<Response> generateSentence(HandlerInput input) throws IOException {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        int counter  = (int) sessionAttributes.get(Attributes.COUNTER_KEY);

        if(counter == 0)
        {
            sessionAttributes.put(Attributes.RESPONSE_KEY, START_RIDDLE_GAME_MESSAGE + " ");
        }

        counter++;

//        Person person = getPerson();
//        sessionAttributes.put(Attributes.RIDDLE_ITEM_KEY, person);
        sessionAttributes.put(Attributes.COUNTER_KEY, counter);

        RiddleUtils val = new RiddleUtils();
        String question = val.getRiddle(counter);
        String speechText = sessionAttributes.get(Attributes.RESPONSE_KEY) + question;

        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speechText)
                .withSpeech(speechText)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();

    }

    public static Vector< String > getPropertyOfPerson(PersonProperty personProperty, Person person) {
        switch (personProperty) {
            case CHARACTER:
                return person.getCharacter();
            case AKA:
                return person.getAKA();
            case CANONICAL_NAME:
                return person.getCanonicalName();
        }
        throw new IllegalStateException("Invalid stateProperty");
    }



    public String getRiddle(int counter) {
        return "Here is your " + counter + "th question. " + riddle.getQuestion() +
                " Answer " + riddle.getAnswer();
    }

//    public Person getPerson()
//    {
//        return CHARACTER_DETAILS.get(0);
//    }

}
