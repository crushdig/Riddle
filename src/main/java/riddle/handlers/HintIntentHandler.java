/**
 * SkipIntentHandler is used to skip a riddle in the game when requested for during the game session.
 */
package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import riddle.Util.RiddleUtils;
import riddle.generator.GenerateRiddles;
import riddle.generator.GenreRiddles;
import riddle.generator.KnowledgeBaseModule;
import riddle.generator.Riddle;
import riddle.model.Attributes;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class HintIntentHandler implements RequestHandler {

    private static final Random RANDOM = new Random();


    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetHintIntent").and(sessionAttribute(Attributes.RIDDLE_STATE_KEY, Attributes.RIDDLE_STATE)));
    }

    /**
     * Returns a response using the builder object
     * @param input the user speech as input
     * @return a response
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

//        HintIntentHandler h = new HintIntentHandler();
        String hint = RiddleUtils.riddle.getHint();
        String speech =  "No Problem! " + hint;

//        RiddleUtils t = new RiddleUtils();
//        ArrayList<String> hints = RiddleUtils.getHint(riddle.getQuestion(), riddle.getAnswer());
//        int val = RANDOM.nextInt(hints.size());
//
//        String speech =  "No Problem! " + hints.get(val);

        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speech)
                .withSpeech(speech)
                .withReprompt(speech)
                .withShouldEndSession(false)
                .build();
    }

    public static void main(String[] args)
    {

//        HintIntentHandler h = new HintIntentHandler();
//        System.out.println(riddle.getQuestion());
//        String hint = riddle.getHint();
//        System.out.println(hint);
    }
}
