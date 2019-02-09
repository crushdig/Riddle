package riddle.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.io.IOException;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import riddle.riddle_generator.GenerateRiddles;
import riddle.riddle_generator.Riddle;


public class GetRiddleIntentHandler implements RequestHandler 
{
  private Riddle riddle;
  private GenerateRiddles riddles;

  public GetRiddleIntentHandler() throws IOException {
   riddles = new GenerateRiddles();
   riddle = riddles.randomiseRiddles();
  }

  public boolean canHandle(HandlerInput input)
  {
    System.out.println("Hello test");
    return input.matches(intentName("GetRiddleIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input)
  {

    String speechText, repromptText;

    boolean isAnswerResponse = false;

    if(riddle.getQuestion() != null)
    {
      speechText = String.format("Here's a riddle %s. You can ask me for a hint "
          + "by saying, give me a hint. Answer is %s ", riddle.getQuestion(), riddle.getAnswer());

      repromptText = "You can ask me for a hint by saying, give me a hint.";
//      if(input.matches(riddleAnswer))
//      {
//
//      }

    }
    else
    {
      speechText = "The answer is not correct. Try again later";

      repromptText = "Unfortunately, I was unable to retrieve a riddle. " +
                      "Restart the skill and try again";
    }

//    logger.debug("Hello this is a debug message");
//    System.out.println("Print In Log File");
//    logger.info("Hello this is a info message");

//    ResponseBuilder responseBuilder = input.getResponseBuilder();
//
//            responseBuilder.withSimpleCard("RiddleSession", speechText)
//            .withSpeech(speechText)
//            .withReprompt(repromptText)
//            .withShouldEndSession(false)
//            .build();
//
//    if(isAnswerResponse)
//    {
//      responseBuilder.withShouldEndSession(false)
//              .withReprompt(repromptText);
//    }
//
//    return responseBuilder.build();
    return input.getResponseBuilder()
            .withSimpleCard("RiddleSession", speechText)
            .withSpeech(speechText)
            .withReprompt(repromptText)
            .withShouldEndSession(true)
            .build();
  }
}
