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

    private static KnowledgeBaseModule CLOTHES = new KnowledgeBaseModule("eu-west-1", "tsv-lists", "Veale's clothing line.txt", 0);
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
        String hint1 = "", hint2 ="", hint3="", hint4="", hint5="", hint6="", hint7;
        Vector<String> category = NOC.getFieldValues("Category", character);
        Vector<String> domain = NOC.getFieldValues("Domains", character);
        Vector<String> ficWorld = NOC.getFieldValues("Fictional World", character);
        Vector<String> gender = NOC.getFieldValues("Gender", character);
        Vector<String> clothes = NOC.getFieldValues("Seen Wearing", character);
        Vector<String> creation = NOC.getFieldValues("Creation", character);
        Vector<String> fictive_Status = NOC.getFieldValues("Fictive Status", character);


        ArrayList<String> hints = new ArrayList<String>();
        ArrayList<Integer> removed_Val = new ArrayList<Integer>();
        String c_Determiner = null;

        Random random = new Random();
        int val;
        if(category != null)
        {
            for(int i  =  0; i < category.size(); i++)
            {
                if (!riddle.contains(category.elementAt(i)) && !category.isEmpty()) {
                    if(category.size() > 1)
                    {
                        val = random.nextInt(category.size());

                        while(removed_Val.contains(val))
                        {
                            val = random.nextInt(category.size());
                            break;
                        }
                        removed_Val.add(val);

                        hint1 = "I am " + getIndefiniteArticleFor(category.elementAt(val)) + " " +
                                category.elementAt(val);
                    }
                    else
                    {
                        hint1 = "I am " + getIndefiniteArticleFor(category.elementAt(0)) + " " +
                                category.elementAt(0);
                    }

                }
            }
            hints.add(hint1);
        }

        if(domain != null)
        {
            for(int i = 0; i < domain.size(); i++)
            {
                if (!riddle.contains(domain.elementAt(i)) && !domain.isEmpty()) {
                    if(domain.size() > 1)
                    {
                        val = random.nextInt(domain.size());

                        while(removed_Val.contains(val))
                        {
                            val = random.nextInt(domain.size());
                            break;
                        }

                        removed_Val.add(val);
                        hint2 = "I'm usually present in " + domain.elementAt(val);
                    }
                    else
                    {
                        hint2 = "I'm usually present in " + domain.elementAt(0);
                    }
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
                    if(ficWorld.size() > 1)
                    {
                        val  =  random.nextInt(ficWorld.size());

                        while(removed_Val.contains(val))
                        {
                            val = random.nextInt(ficWorld.size());
                            break;
                        }
                        removed_Val.add(val);

                        hint3  = "You've probably seen me in " + ficWorld.elementAt(val);
                    }
                    else
                    {
                        hint3  = "You've probably seen me in " + ficWorld.elementAt(0);
                    }
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

        if(clothes != null)
        {
            for(int i = 0; i < clothes.size(); i++)
            {
                if(!riddle.contains(clothes.elementAt(i)) && !clothes.isEmpty())
                {
                    if(clothes.size() > 1)
                    {
                        val  =  random.nextInt(clothes.size());

                        c_Determiner = CLOTHES.getFirstValue("Determiner", clothes.elementAt(val));

                        while(removed_Val.contains(val))
                        {
                            val = random.nextInt(clothes.size());
                            c_Determiner = CLOTHES.getFirstValue("Determiner", clothes.elementAt(val));
                            break;
                        }
                        removed_Val.add(val);

                        if(c_Determiner != null)
                        {
                            hint5  = "I feel a little naked if i'm not wearing " + c_Determiner + " " + clothes.elementAt(val) + ".";
                        }
                        else
                        {
                            hint5  = "I feel a little naked if i'm not wearing " + clothes.elementAt(val) + ".";
                        }
                    }
                    else
                    {
                        if(c_Determiner != null)
                        {
                            hint5  = "I feel a little naked if i'm not wearing " + c_Determiner + " " + clothes.elementAt(0) + ".";
                        }
                        else
                        {
                            hint5  = "I feel a little naked if i'm not wearing " + clothes.elementAt(0) + ".";
                        }
                    }

                }
            }
            hints.add(hint5);
        }

        if(creation != null)
        {
            for(int i = 0; i < creation.size(); i++)
            {
                if(!riddle.contains(creation.elementAt(i)) && !creation.isEmpty())
                {
                    if(creation.size() > 1)
                    {
                        val  =  random.nextInt(creation.size());

                        while(removed_Val.contains(val))
                        {
                            val = random.nextInt(creation.size());
                            break;
                        }
                        removed_Val.add(val);

                        hint6 = "The creation of " + creation.elementAt(val) + " was my handiwork.";
                    }
                    else
                    {
                        hint6 = "The creation of " + creation.elementAt(0) + " was my handiwork.";
                    }
                }
            }
            hints.add(hint6);
        }

        if(fictive_Status != null)
        {
            hint7 = "I am from the fictional world.";
        }
        else
        {
            hint7 = "People from the fictional world wished they could be as real as me.";
        }
        hints.add(hint7);


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