/**
 * SessionEndedIntentHandler is used to end the game session if an error that has nothing to do with the code occurs.
 */
package riddle.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import org.slf4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import static org.slf4j.LoggerFactory.getLogger;

public class SessionEndedRequestHandler implements RequestHandler {

    private static Logger LOG = getLogger(SessionEndedRequestHandler.class);

    /**
     * Returns a boolean value
     * @param input the user speech as input
     * @return a boolean of True or False
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    /**
     * Returns no response
     * @param input the user speech as input
     * @return an empty response
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) input.getRequestEnvelope().getRequest();
        LOG.debug("Session ended with reason: " + sessionEndedRequest.getReason().toString());
        return Optional.empty();
    }
}