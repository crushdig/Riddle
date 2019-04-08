/**
 * EnemyRiddles is used to create riddles centred around a character's enemies.
 */
package riddle.generator;

import java.util.HashMap;
import java.util.Vector;

public class EnemyRiddles
{
    private String clientRegion = "eu-west-1";
    private String bucketName =  "tsv-lists";
    private KnowledgeBaseModule NOC = null;
    private KnowledgeBaseModule CLOTHES    = null;
    private KnowledgeBaseModule VEHICLES   = null;
    private KnowledgeBaseModule WEAPONS      = null;

    /**
     * Sets up all the objects needed for accessing information from the NOC
     */
    public EnemyRiddles() {
        NOC = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's The NOC List.txt", 0);
        VEHICLES = new KnowledgeBaseModule(clientRegion, bucketName,"Veale's vehicle fleet.txt", 1);
        WEAPONS = new KnowledgeBaseModule(clientRegion, bucketName,"Veale's weapon arsenal.txt", 1);
    }

    /**
     * Returns all constructed riddles.
     * @return a HashMap containing riddles
     */
    public HashMap<String, String> setupEnemyRiddles()
    {
        String riddle;
        String riddleAnswer;
        HashMap<String, String> riddles = new HashMap<>();
        Vector<String> listOfCharacters = NOC.getAllFrames();

        for(String character: listOfCharacters)
        {
            // second loop to generate multiple riddles for a character having multiple values for certain columns
            String pronoun = "he".toLowerCase();
            String possessive_Pronoun = "his".toLowerCase();

            if(NOC.hasFieldValue("Gender", character, "female"))
            {
                pronoun = "she".toLowerCase();
                possessive_Pronoun = "her".toLowerCase();
            }

            Vector<String> enemies = NOC.getFieldValues("Opponent", character);
            Vector<String> clothes = NOC.getFieldValues("Seen Wearing", character);
            Vector<String> genres = NOC.getFieldValues("Genres", character);
            Vector<String> weapons = NOC.getFieldValues("Weapon of Choice", character);

            for(int k = 0; k < 10; k++)
            {

                String s_Enemies = null, s_Clothes = null, s_Weapon = null, s_Genres = null;
                String c_Determiner, w_Determiner, w_Affordances;

                if(enemies != null && clothes != null && genres != null && weapons != null)
                {

                    int j;
                    // choosing values from NOC fields, if field has multiple values, remove the last value after it's been used in a riddle and use the incoming value for a new riddle.
                    for(j = 0; j < enemies.size(); j++)
                    {
                        s_Enemies = (String) enemies.elementAt(j);
                    }
                    enemies.remove(s_Enemies);

                    for(j = 0; j < clothes.size(); j++)
                    {
                        s_Clothes = (String) clothes.elementAt(j);
                    }
                    clothes.remove(s_Clothes);

                    for(j = 0; j < genres.size(); j++)
                    {
                        s_Genres = (String) genres.elementAt(j);
                    }
                    genres.remove(s_Genres);

                    for(j = 0; j < weapons.size(); j++)
                    {
                        s_Weapon = (String) weapons.elementAt(j);
                    }
                    weapons.remove(s_Weapon);

                    if(s_Enemies == null || s_Clothes == null || s_Genres == null || s_Weapon == null)
                    {
                        continue;
                    }

                    w_Determiner = WEAPONS.getFirstValue("Determiner", s_Weapon);
                    w_Affordances = WEAPONS.getFirstValue("Affordances", s_Weapon);


                    if(w_Determiner == null)
                    {
                        riddle = "My name is " + character + ". I'm present in a lot of " + s_Genres + ". I enjoy " + w_Affordances +
                                " " + s_Weapon + " people that I dislike but most especially, one person in particular." +
                                " Can you tell who this person is?";
                        riddleAnswer = s_Enemies;
                        riddles.put(riddle, riddleAnswer);
                    }
                    if(w_Determiner != null)
                    {
                        riddle = "My name is " + character + ". I'm present in a lot of " + s_Genres + ". I enjoy " + w_Affordances + " " + w_Determiner +
                                " " + s_Weapon + " people that I dislike but most especially, one person in particular." +
                                " Can you tell who this person is?";
                        riddleAnswer = s_Enemies;
                        riddles.put(riddle, riddleAnswer);
                    }
                }
            }
        }
        return riddles;
    }

    /**
     * Returns an Indefinite Article for a word.
     * @param word a string which requires an associated indefinite article
     * @return a string
     */
    private String getIndefiniteArticleFor(String word)
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
}
