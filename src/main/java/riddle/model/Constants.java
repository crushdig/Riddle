package riddle.model;

import riddle.generator.KnowledgeBaseModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants
{
    private static KnowledgeBaseModule NOC;
    private String clientRegion = "eu-west-1";
    private String bucketName =  "tsv-lists";

    public Constants() {
        NOC = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's The NOC List.txt", 0);
    }


    public static String WELCOME_MESSAGE = "Welcome to Blank's Riddle Game! You can ask me to start a riddle game.  If you need more instructions, " +
            "you can ask for help. What would you like to do?";

    public static String START_RIDDLE_GAME_MESSAGE = "OK.  I will ask you 10 riddles based on real and fictional people.";

    // This is the message a user will hear when they try to cancel or stop the skill, or when they finish a riddle.
    public static String EXIT_SKILL_MESSAGE = "Thank you for playing!  Let's play again soon!";

    public static String REPROMPT_MESSAGE = "If you'd like to start the riddle game, simply ask me to start the riddle game";

    // This is the message a user will hear when they ask Alexa for help in your skill.
    public static String HELP_MESSAGE = "I have a lot of riddles in store for you. You can test your knowledge by asking me to start a riddle game.  What would you like to do?";

    public static List<String> CORRECT_RESPONSES = Arrays.asList("Booya", "All righty", "Bam", "Bazinga", "Bingo", "Boom", "Bravo", "Cha Ching", "Cheers", "Dynomite",
            "Hip hip hooray", "Hurrah", "Hurray", "Huzzah", "Oh dear.  Just kidding.  Hurray", "Kaboom", "Kaching", "Oh snap", "Phew",
            "Righto", "Way to go", "Well done", "Whee", "Woo hoo", "Yay", "Wowza", "Yowsa");

    public static List<String> INCORRECT_RESPONSES = Arrays.asList("Argh", "Aw man", "Blarg", "Blast", "Boo", "Bummer", "Darn", "D'oh", "Dun dun dun", "Eek", "Honk", "Le sigh",
            "Mamma mia", "Oh boy", "Oh dear", "Oof", "Ouch", "Ruh roh", "Shucks", "Uh oh", "Wah wah", "Whoops a daisy", "Yikes");

    public static List<String> CORRECT_ANSWER_RESPONSES = Arrays.asList("I have an interesting ", "That's right, even I'm in awe of my own ",
            "Do you want to know the secret behind my ");

    public static List<String> INCORRECT_ANSWER_RESPONSES = Arrays.asList("How couldn't you guess my ", "How disappointing. You forgot my ", "Well, it sure is difficult to state my ",
            "How dare you forget my ");

    public static final ArrayList<String> CHARACTER_NAMES()
    {
        Person person = null;
        ArrayList<String> CHARACTER_NAMES = new ArrayList<>(NOC.getAllFrames());

        return CHARACTER_NAMES;
    }

}
