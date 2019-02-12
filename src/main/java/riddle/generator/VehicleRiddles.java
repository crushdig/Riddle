package riddle.generator;

import java.util.HashMap;
import java.util.Vector;

public class VehicleRiddles
{
  private KnowledgeBaseModule NOC;
  private KnowledgeBaseModule CATEGORIES = null;
  private KnowledgeBaseModule CLOTHES    = null;
  private KnowledgeBaseModule VEHICLES   = null;

  public Vector<String> character1;

  public VehicleRiddles()
  {
    NOC = new KnowledgeBaseModule("./TSV Lists/Veale's The NOC List.txt", 0);
    CATEGORIES = new KnowledgeBaseModule("./TSV Lists/Veale's Category Hierarchy.txt");
    VEHICLES = new KnowledgeBaseModule("./TSV Lists/Veale's vehicle fleet.txt", 1);
    CLOTHES = new KnowledgeBaseModule("./TSV Lists/Veale's clothing line.txt", 1);
  }

  public HashMap<String, String> setupVehicleRiddles()
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

      Vector<String> vehicles = NOC.getFieldValues("Vehicle of Choice", character);
      Vector<String> occupations = NOC.getFieldValues("Category", character);
      Vector<String> enemies = NOC.getFieldValues("Opponent", character);
      Vector<String> clothes = NOC.getFieldValues("Seen Wearing", character);

      int k = 0;
      for(k = 0; k < 10; k++)
      {

        String s_Vehicles = null, s_Occupations = null, s_Enemies = null, s_Clothes = null;
        String c_Determiner, determiner, covers, affordances;

        if (vehicles != null && enemies != null && occupations != null && clothes !=  null)
        {

          int j, i = 0;
          for(j = 0; j < vehicles.size(); j++)
          {
            s_Vehicles = vehicles.elementAt(j);
          }
          vehicles.remove(s_Vehicles);


          for(j = 0; j < occupations.size(); j++)
          {
            s_Occupations = occupations.elementAt(j);
          }
          occupations.remove(s_Occupations);
//          while(enemies_Set.contains(occupations.elementAt(i)) && i < occupations.size())
//          {
//            s_Occupations = occupations.elementAt(i);
//            occupations.remove(i);
//            i++;
//
//            break;
//          }
//          i = 0;
//          
//          enemies_Set.add(s_Occupations);

          for(j = 0; j < enemies.size(); j++)
          {
            s_Enemies = enemies.elementAt(j);
          }
          enemies.remove(s_Enemies);

          for(j = 0; j < clothes.size(); j++)
          {
            s_Clothes = (String) clothes.elementAt(j);
          }
          clothes.remove(s_Clothes);

          if(s_Vehicles == null || s_Occupations == null || s_Enemies == null || s_Clothes == null)
          {
            continue;
          }

          c_Determiner = CLOTHES.getFirstValue("Determiner", s_Clothes);
          covers = CLOTHES.getFirstValue("Covers", s_Clothes);
          determiner =  VEHICLES.getFirstValue("Determiner", s_Vehicles);
          affordances = VEHICLES.getFirstValue("Affordances", s_Vehicles);

          if(affordances == null && determiner == null && c_Determiner == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". I usually wear " + s_Clothes +
                    " on my " + covers + " and I am sometimes seen in " + possessive_Pronoun + " " + s_Vehicles +
                    ". " + s_Enemies + " is to me as water is to oil. Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }

          if(affordances == null && determiner == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". I usually wear " + c_Determiner +
                    " " + s_Clothes + " on my " + covers + " and I am sometimes seen in " + possessive_Pronoun + " " + s_Vehicles +
                    ". " + s_Enemies + " is to me as water is to oil. Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }

          if(affordances == null && c_Determiner == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". I usually wear " + s_Clothes +
                    " on my " + covers + ". You can spot me pulling up anywhere via " + determiner + " " + s_Vehicles +
                    ". " + s_Enemies + " is the one person I can't share the same air with. Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }

          if(determiner == null && c_Determiner == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". I usually wear " + s_Clothes +
                    " on my " + covers + ". I can be seen " + affordances + " " + getIndefiniteArticleFor(s_Vehicles) + " " + s_Vehicles +
                    ". " + s_Enemies + " is the one person I can't share the same air with. Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }

          if(affordances == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". I usually wear " + c_Determiner +
                    " " + s_Clothes + " on my " + covers + ". You can spot me pulling up anywhere via " + determiner + " " + s_Vehicles +
                    ". " + s_Enemies + " is the one person I can't share the same air with. Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }

          if(determiner == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". I usually wear " + c_Determiner +
                    " " + s_Clothes + " on my " + covers + ". I can be seen " + affordances + " " + getIndefiniteArticleFor(s_Vehicles) + " " + s_Vehicles +
                    ". " + s_Enemies + " is the one person I can't share the same air with. Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }

          if(c_Determiner == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". If you keep your eyes peeled, you'll spot me wearing " + s_Clothes +
                    " on my " + covers + ". Sometimes, you'll see me "  + affordances + " " + determiner + " " + s_Vehicles +
                    ". A lot of my time is spent colliding forces with " + s_Enemies + ". Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }
          else
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". If you keep your eyes peeled, you'll spot me wearing " + c_Determiner +
                    " " + s_Clothes + " on my " + covers + ". Sometimes, you'll see me "  + affordances + " " + determiner + " " + s_Vehicles +
                    ". A lot of my time is spent colliding forces with " + s_Enemies + ". Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }
        }
      }
    }
    return riddles;
  }

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
