package riddle.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class GenerateRiddles{

    public Riddle randomiseRiddles() {
        Random RND = new Random();
//    VehicleRiddles A =  new VehicleRiddles();
        ClothingRiddles E = new ClothingRiddles();
        GenreRiddles I = new GenreRiddles();
        EnemyRiddles O = new EnemyRiddles();
        String question = null, answer = null;

        HashMap<String, String> riddle_And_answer_Collection = new HashMap<String, String>();
//    HashMap<String, String> vehicle_Riddle_and_Answer_Collection = A.setupVehicleRiddles();
        HashMap<String, String> clothing_Riddle_and_Answer_Collection = E.setupClothingRiddles();
//        HashMap<String, String> genre_Riddle_and_Answer_Collection = I.setupGenreRiddles();
//        HashMap<String, String> enemy_Riddle_and_Answer_Collection = O.setupEnemyRiddles();


//    riddle_And_answer_Collection.putAll(vehicle_Riddle_and_Answer_Collection);
    riddle_And_answer_Collection.putAll(clothing_Riddle_and_Answer_Collection);
//    riddle_And_answer_Collection.putAll(genre_Riddle_and_Answer_Collection);
//    riddle_And_answer_Collection.putAll(enemy_Riddle_and_Answer_Collection);

        Set<String>riddles = riddle_And_answer_Collection.keySet();

        ArrayList<String> L_riddleCollection = new ArrayList<String>();
        L_riddleCollection.addAll(riddles);
        int riddlePos = RND.nextInt(riddles.size());

        question = L_riddleCollection.get(riddlePos);
        answer = riddle_And_answer_Collection.get(L_riddleCollection.get(riddlePos));

        Riddle riddle = new Riddle(question, answer);
        return riddle;
    }
}
