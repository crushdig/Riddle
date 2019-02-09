//package lambda.riddle.handlers;
//
//import com.amazon.ask.dispatcher.request.handler.HandlerInput;
//import com.amazon.ask.model.Response;
//import riddle_Generator.GenerateRiddles;
//import riddle_Generator.Riddle;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static com.amazon.ask.request.Predicates.intentName;
//
//public class GiveAnswerIntentHandler
//{
//    private Riddle riddle;
//    private GenerateRiddles riddles;
//
//    public GiveAnswerIntentHandler() throws IOException {
//        riddles = new GenerateRiddles();
//        riddle = riddles.randomiseRiddles();
//    }
//
//    public boolean canHandle(HandlerInput input)
//    {
//        return input.matches(intentName("GiveAnswerIntent"));
//    }
//
//    @Override
//    public Optional<Response> handle(HandlerInput input)
//    {
//        String speechText, repromptText;
//
//        if(in)
//        return input.getResponseBuilder()
//                .withSimpleCard("RiddleSession", speechText)
//                .withSpeech(speechText)
//                .withReprompt(repromptText)
//                .withShouldEndSession(true)
//                .build();
//    }
//}
