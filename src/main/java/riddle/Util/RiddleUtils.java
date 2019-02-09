package riddle.Util;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import riddle.model.Attributes;
import riddle.model.Constants;
import riddle.riddle_Generator.GenerateRiddles;
import riddle.riddle_Generator.Riddle;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class RiddleUtils
{
    private static Riddle riddle;
    private GenerateRiddles riddles;

    public RiddleUtils() throws IOException {
        riddles = new GenerateRiddles();
        riddle = riddles.randomiseRiddles();
    }
    public static Optional<Response> generateSentence(HandlerInput input)
    {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int counter  = (int) sessionAttributes.get(Attributes.COUNTER_KEY);

        if(counter == 0)
        {
            sessionAttributes.put(Attributes.RESPONSE_KEY, Constants.START_RIDDLE_GAME_MESSAGE);
        }


        sessionAttributes.put(Attributes.COUNTER_KEY, counter);

        String question = getRiddle(counter);
        String speechText = sessionAttributes.get(Attributes.RESPONSE_KEY) + question;

        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speechText)
                .withSpeech(speechText)
                .withReprompt(question)
                .build();

    }



    public static String getRiddle(int counter) {
        return "Here is your " + counter + "th question. " + riddle.getQuestion();
    }

}
