/**
 * RiddleStreamHandler is used to execute all the intents enabling all available commands to be processed
 */
package riddle;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import riddle.handlers.*;

import java.io.IOException;


public class RiddleStreamHandler extends SkillStreamHandler
{
  @SuppressWarnings("unchecked")
  private static Skill getSkill() {
    return Skills.standard()
        .addRequestHandlers(
            new LaunchRequestHandler(),
            new SessionEndedRequestHandler(),
            new HelpIntentHandler(),
            new AnswerIntentHandler(),
            new RiddleAndStartOverIntentHandler(),
            new RepeatIntentHandler(),
            new SkipIntentHandler(),
            new NoAnswerIntentHandler(),
            new ExitSkillHandler())


        .withSkillId("amzn1.ask.skill.5dabbdfb-e2c1-4191-bda7-dc3a6bf61f30")
        .build();
  }
  
  public RiddleStreamHandler() {
    super(getSkill());
  }
}