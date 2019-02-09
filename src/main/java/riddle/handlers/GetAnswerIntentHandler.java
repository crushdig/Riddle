package riddle.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;


public class GetAnswerIntentHandler implements RequestHandler
{

  @Override
  public boolean canHandle(HandlerInput input) 
  {
    return input.matches(intentName("GetAnswerIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) 
  {
//    GenerateRiddles riddles = new GenerateRiddles();
//    Riddle answer = riddles.randomiseRiddles();
    
    String speechText = null, repromptText;
    
//    if(answer.getAnswer() != null)
//    {
//      speechText = String.format("The answer to %s is %s",
//          answer.getQuestion(), answer.getAnswer());
//      repromptText = String.format("The answer to %s is %s",
//          answer.getQuestion(), answer.getAnswer());
//    }
//    else
//    {
//      repromptText = "No answer found.";
//    }
//
    speechText = "Danielle Bregoli";
    repromptText = "Danielle Bregoli";

    return input.getResponseBuilder()
        .withSimpleCard("RiddleSession", speechText)
        .withSpeech(speechText)
        .withReprompt(repromptText)
        .withShouldEndSession(true)
        .build();
  }

}
