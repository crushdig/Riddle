package riddle.generator;

import java.util.*;

public class GenerateRiddles
{

    public Riddle randomiseRiddles()
    {
        Random RND = new Random();
        ClothingRiddles E = new ClothingRiddles();
        GenreRiddles I = new GenreRiddles();
        EnemyRiddles O = new EnemyRiddles();
        String question = null, answer = null;

        HashMap<String, String> riddle_And_answer_Collection = new HashMap<String, String>();
//        HashMap<String, String> clothing_Riddle_and_Answer_Collection = E.setupClothingRiddles();
        HashMap<String, String> genre_Riddle_and_Answer_Collection = I.setupGenreRiddles();
//        HashMap<String, String> enemy_Riddle_and_Answer_Collection = O.setupEnemyRiddles();


//        riddle_And_answer_Collection.putAll(clothing_Riddle_and_Answer_Collection);
        riddle_And_answer_Collection.putAll(genre_Riddle_and_Answer_Collection);
//        riddle_And_answer_Collection.putAll(enemy_Riddle_and_Answer_Collection);

        Set<String>riddles = riddle_And_answer_Collection.keySet();

        ArrayList<String> L_riddleCollection = new ArrayList<String>(riddles);
        int riddlePos = RND.nextInt(riddles.size());

        question = L_riddleCollection.get(riddlePos);
        answer = riddle_And_answer_Collection.get(L_riddleCollection.get(riddlePos));

        return new Riddle(question, answer);
    }

    public static List<String> FICTIONAL_STATUS_EXPRESSIONS = Arrays.asList("You'll know me from the fictional world. ", "A legendary character, I am. ",
            "I live in a fictional world. ");
    public static List<String> NON_FICTIONAL_STATUS_EXPRESSIONS = Arrays.asList("I'm a famous public figure. ", "I'm a renowned individual. ",
            "I am a big deal. ");
}
