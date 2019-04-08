/**
 * RiddleUtils is used to generate setences and setup session attributes for the alexa dev console
 */
package riddle.Util;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import riddle.generator.KnowledgeBaseModule;
import riddle.model.Attributes;
import riddle.model.Person;
import riddle.model.PersonProperty;
import riddle.generator.GenerateRiddles;
import riddle.generator.Riddle;

import java.util.*;

import static riddle.model.Constants.START_RIDDLE_GAME_MESSAGE;

public class RiddleUtils
{
    public static Riddle riddle;
    private GenerateRiddles riddles;
//    private String clientRegion = "eu-west-1";
//    private String bucketName =  "tsv-lists";
    private static KnowledgeBaseModule NOC = new KnowledgeBaseModule("eu-west-1", "tsv-lists", "Veale's The NOC List.txt", 0);



    /**
     * Set up riddles object and randomise riddle each time
     */
    public RiddleUtils() {
        riddles = new GenerateRiddles();
        riddle = riddles.randomiseRiddles();
    }

    /**
     * Returns a response using the builder object
     * @param input the user speech as input
     * @return a response
     */
    public static Optional<Response> generateSentence(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        int counter  = (int) sessionAttributes.get(Attributes.COUNTER_KEY);

        if(counter == 0)
        {
            sessionAttributes.put(Attributes.RESPONSE_KEY, START_RIDDLE_GAME_MESSAGE + " ");
        }
        String question = setupSessionAttributes(sessionAttributes, counter);

        String speechText = sessionAttributes.get(Attributes.RESPONSE_KEY) + question;

        getHint(riddle.getQuestion(), riddle.getAnswer());


        return input.getResponseBuilder()
                .withSimpleCard("RiddleSession", speechText)
                .withSpeech(speechText)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();

    }

    /**
     *
     * @param sessionAttributes
     * @param counter the numeric counter for the riddles position
     * @return a value for the position of the riddle during the game
     */
    public static String setupSessionAttributes(Map<String, Object> sessionAttributes, int counter) {
        counter++;

        RiddleUtils val = new RiddleUtils();
        Person characterDetails = val.getPerson();
        PersonProperty personProperty = getProperties();

        sessionAttributes.put(Attributes.RIDDLE_ITEM_KEY, characterDetails);
        sessionAttributes.put(Attributes.RIDDLE_PROPERTY_KEY, personProperty);
        sessionAttributes.put(Attributes.COUNTER_KEY, counter);


        return val.getRiddle(counter);
    }

    /**
     * Returns a riddle for repitition
     * @param counter the numeric counter for the number of riddles
     * @return a String containing riddle to be repeated
     */
    public static String getRiddleRepetition(int counter) {
        return "<say-as interpret-as='interjection'> With pleasure! </say-as><break strength='strong'/>" + "Here is your " + counter + "th riddle. " + riddle.getQuestion();
    }


    /**
     * Return all names associated with a character.
     * @param personProperty the property associated with a particular person
     * @param person the person to be guessed in the riddle
     * @return a vector of names
     */
    public static Vector<String> getPropertyOfPerson(PersonProperty personProperty, Person person) {

        switch (personProperty) {
            case CHARACTER:
                Vector<String> allCharacterNames = new Vector<>();
                allCharacterNames.addAll(person.getCharacter());
                allCharacterNames.addAll(person.getCanonicalName());

                if (person.getAKA()!=null)
                {
                    allCharacterNames.addAll(person.getAKA());
                }
                return allCharacterNames;
        }
        throw new IllegalStateException("Invalid personProperty");
    }

    /**
     * Returns a list of hints.
     * @param riddle the riddle for a particular character
     * @param character the character whom hints need to be generated for
     * @return a Hints object
     */
    public static ArrayList<String> getHint(String riddle, String character)
    {
        String hint, hint1 = null, hint2 = null, hint3 = null, hint4;
        Vector<String> category = NOC.getFieldValues("Category", character);
        Vector<String> domain = NOC.getFieldValues("Domains", character);
        Vector<String> ficWorld = NOC.getFieldValues("Fictional World", character);
        Vector<String> gender = NOC.getFieldValues("Gender", character);

        ArrayList<String> hints = new ArrayList<String>();
        ArrayList<Integer> removed_Val = new ArrayList<Integer>();

        Random random = new Random();
        int val;
        if(category != null)
        {
            for(int i  =  0; i < category.size(); i++)
            {
                if (!riddle.contains(category.elementAt(i)) && !category.isEmpty()) {
                    val = random.nextInt(category.size());

                    while(removed_Val.contains(val))
                    {
                        val = random.nextInt(category.size());
                    }
                    removed_Val.add(val);

                    hint1 = "I am " + getIndefiniteArticleFor(category.elementAt(val)) + " " +
                            category.elementAt(val);

                }
            }
            hints.add(hint1);
        }

        if(domain != null)
        {
            for(int i = 0; i < domain.size(); i++)
            {
                if (!riddle.contains(domain.elementAt(i)) && !domain.isEmpty()) {
                    val = random.nextInt(domain.size());

                    while(removed_Val.contains(val))
                    {
                        val = random.nextInt(domain.size());
                    }

                    removed_Val.add(val);
                    hint2 = "I'm usually present in " + domain.elementAt(val);
                }
            }

            hints.add(hint2);
        }

        if(ficWorld != null)
        {
            for(int i = 0; i < ficWorld.size(); i++)
            {
                if(!riddle.contains(ficWorld.elementAt(i)) && !ficWorld.isEmpty())
                {
                    val  =  random.nextInt(ficWorld.size());

                    while(removed_Val.contains(val))
                    {
                        val = random.nextInt(ficWorld.size());
                    }
                    removed_Val.add(val);

                    hint3  = "You've probably seen me in " + ficWorld.elementAt(val);
                }
            }
            hints.add(hint3);
        }

        if(gender != null && gender.elementAt(0).equalsIgnoreCase("male"))
        {
            if(!riddle.contains(gender.elementAt(0)) && !gender.isEmpty())
            {
                hint4 = "I am a man.";
                hints.add(hint4);
            }
        }
        else
        {
            if(gender != null)
            {
                if(!riddle.contains(gender.elementAt(0)) && !gender.isEmpty())
                {
                    hint4 = "I am a woman.";
                    hints.add(hint4);
                }
            }
        }
        return hints;
    }

    /**
     * Returns an Indefinite Article for a word.
     * @param word a string which requires an associated indefinite article
     * @return a string
     */
    private static String getIndefiniteArticleFor(String word)
    {
        if(word.startsWith("hon"))
        {
            return "an";
        }
        else if(word.startsWith("eu"))
        {
            return "a";
        }
        else if("aeiou".indexOf((char)word.charAt(0)) >= 0)
        {
            return "an";
        }
        else
        {
            return "a";
        }
    }

    /**
     * Search query implementation (Under works)
     */
//    public static Optional<Person> getPerson(Map<String, Slot> slots) {
//        for (Slot slot : slots.values()) {
//            String value = slot.getValue();
//            for (PersonProperty personProperty : PersonProperty.values()) {
//                Optional<Person> person = CHARACTER_NAMES().stream()
//                        .filter(s -> getPropertyOfPerson(personProperty, s).equals(value))
//                        .findAny();
//                if (person.isPresent()) {
//                    return person;
//                }
//            }
//        }
//        return Optional.empty();
//    }


    /**
     *  Returns a riddle.
     * @param counter the numeric counter for the number of riddles
     * @return a riddle as a string
     */
    public String getRiddle(int counter) {
        return "Here is your " + counter + "th riddle. " + riddle.getQuestion();
    }

    /**
     * Returns the details of a person.
     * @return an object of person containing details of the person
     */
    public Person getPerson()
    {
        return riddle.getPerson();
    }

    /**
     * Returns a property associated with a person.
     * @return the first property of the person
     */
    public static PersonProperty getProperties() {
        return PersonProperty.values()[0];
    }
}