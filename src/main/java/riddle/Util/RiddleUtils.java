package riddle.Util;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import riddle.model.Attributes;
import riddle.model.Person;
import riddle.model.PersonProperty;
import riddle.generator.GenerateRiddles;
import riddle.generator.Riddle;

import java.util.Map;
import java.util.Optional;
import java.util.Vector;

import static riddle.model.Constants.START_RIDDLE_GAME_MESSAGE;

public class RiddleUtils
{
    private static Riddle riddle;
    private GenerateRiddles riddles;


    public RiddleUtils() {
        riddles = new GenerateRiddles();
        riddle = riddles.randomiseRiddles();
    }
    public static Optional<Response> generateSentence(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        int counter  = (int) sessionAttributes.get(Attributes.COUNTER_KEY);

        if(counter == 0)
        {
            sessionAttributes.put(Attributes.RESPONSE_KEY, START_RIDDLE_GAME_MESSAGE + " ");
        }

        counter++;

        RiddleUtils val = new RiddleUtils();
        Person characterDetails = val.getPerson();
        PersonProperty personProperty = getProperties();

        sessionAttributes.put(Attributes.RIDDLE_ITEM_KEY, characterDetails);
        sessionAttributes.put(Attributes.RIDDLE_PROPERTY_KEY, personProperty);
        sessionAttributes.put(Attributes.COUNTER_KEY, counter);


        String question = val.getRiddle(counter);
        String speechText = sessionAttributes.get(Attributes.RESPONSE_KEY) + question;

        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speechText)
                .withSpeech(speechText)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();

    }

    public static String getQuestionText(int counter) {
        return "Here is your " + counter + "th riddle. " + riddle.getQuestion();
    }


    public static Vector<String> getPropertyOfPerson(PersonProperty personProperty, Person person) {

        switch (personProperty) {
            case CHARACTER:
                Vector<String> toReturn = new Vector<>();
                toReturn.addAll(person.getCharacter());
                toReturn.addAll(person.getCanonicalName());

                if (person.getAKA()!=null)
                {
                    toReturn.addAll(person.getAKA());
                }
                return toReturn;
        }
        throw new IllegalStateException("Invalid personProperty");
    }





    public String getRiddle(int counter) {
        return "Here is your " + counter + "th riddle. " + riddle.getQuestion();
    }

    public Person getPerson()
    {
        return riddle.getPerson();
    }

    private static PersonProperty getProperties() {
        return PersonProperty.values()[0];
    }
}
