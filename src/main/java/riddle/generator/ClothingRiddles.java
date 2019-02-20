package riddle.generator;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class ClothingRiddles
{
  private String clientRegion = "eu-west-1";
  private String bucketName =  "tsv-lists";
  private KnowledgeBaseModule NOC;
  private KnowledgeBaseModule CATEGORIES = null;
  private KnowledgeBaseModule CLOTHES    = null;
  private KnowledgeBaseModule VEHICLES   = null;

  public ClothingRiddles() {
    NOC = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's The NOC List.txt", 0);
    CATEGORIES = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's Category Hierarchy.txt", 1);
    VEHICLES = new KnowledgeBaseModule(clientRegion, bucketName,"Veale's vehicle fleet.txt", 1);
    CLOTHES = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's clothing line.txt", 1);
  }

  public HashMap<String, String> setupClothingRiddles()
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
      Vector<String> address = null;
      Random RND = new Random();

      int add_Index = RND.nextInt(3);
      if(add_Index == 1 || add_Index == 2 || add_Index == 3)
      {
        address = NOC.getFieldValues("Address " + add_Index , character);
      }

      int k = 0;
      for(k = 0; k < 10; k++)
      {

        String s_Enemies = null, s_Clothes = null, s_Address = null;
        String c_Determiner;

        if(enemies != null && clothes !=  null && address != null)
        {

          int j;

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

          for(j = 0; j < address.size(); j++)
          {
            s_Address = (String) address.elementAt(j);
          }
          address.remove(s_Address);

          if(s_Enemies == null || s_Clothes == null || s_Address == null)
          {
            continue;
          }

          c_Determiner = CLOTHES.getFirstValue("Determiner", s_Clothes);

          if(c_Determiner == null)
          {
            riddle = "I am a big deal. " + "You can spot me wearing " + s_Clothes + " lurking around " + s_Address +
                    ". If I had a choice, I would make " + s_Enemies + " public enemy number 1. Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }
          else
          {
            riddle = "I am a big deal. " + "You can spot me wearing " + c_Determiner + " " + s_Clothes + " lurking around " + s_Address +
                    ". If I had a choice, I would make " + s_Enemies + " public enemy number 1. Who am I?";
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
