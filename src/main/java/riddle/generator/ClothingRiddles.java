package riddle.generator;

import javafx.scene.control.RadioMenuItem;

import java.util.*;

public class ClothingRiddles
{
  private static final Random RANDOM = new Random();
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

      Vector<String> fictive_Status = NOC.getFieldValues("Fictive Status", character);
      Vector<String> enemies = NOC.getFieldValues("Opponent", character);
      Vector<String> clothes = NOC.getFieldValues("Seen Wearing", character);
      Vector<String> address;

      Vector<String> address1 = NOC.getFieldValues("Address 1", character);
      Vector<String> address2 = NOC.getFieldValues("Address 2", character);
      Vector<String> address3 = NOC.getFieldValues("Address 3", character);

      if(address1 != null && address2 != null)
      {
        address = address1 ;
        address.addAll(address2);
      }
      else if(address1 != null && address3 != null)
      {
        address = address1;
        address.addAll(address3);
      }
      else if(address2 != null && address3 != null)
      {
        address = address2;
        address.addAll(address3);
      }
      else
      {
        address = address3;
      }

      int k;
      for(k = 0; k < 10; k++)
      {

        String s_Enemies = "", s_Clothes = "", c_Determiner;

        StringBuilder s_Address = new StringBuilder();

        if(enemies != null && clothes !=  null && address != null)
        {
          int j;

          for(j = 0; j < enemies.size(); j++)
          {
            s_Enemies = enemies.elementAt(j);
          }
          enemies.remove(s_Enemies);

          for(j = 0; j < clothes.size(); j++)
          {
            ArrayList<String> clothesContainer = new ArrayList<>(clothes);

            if(clothesContainer.size() == 1)
            {
              s_Clothes = clothesContainer.get(0);
            }
            else if(clothesContainer.size() == 2) // 2 items
            {
              s_Clothes = clothesContainer.get(0) + " and " + clothesContainer.get(1);
            }
            else if(clothesContainer.size() == 3) // 3 items
            {
              s_Clothes = clothesContainer.get(0) + ", " + clothesContainer.get(1) + " and " + clothesContainer.get(2);
            }
            else if(clothesContainer.size() >= 4)
            {
              s_Clothes = clothesContainer.get(0) + ", " + clothesContainer.get(1) + ", " + clothesContainer.get(2) + " and " + clothesContainer.get(3);
            }
          }

          for(j = 0; j < address.size(); j++)
          {
            if(j != address.size()-1)
            {
              s_Address.append(address.elementAt(j)).append(", ");
            }
            else
            {
              s_Address.append(address.elementAt(j));
            }
          }

          if(s_Enemies == null || s_Clothes == null)
          {
            continue;
          }

          c_Determiner = CLOTHES.getFirstValue("Determiner", s_Clothes);

          if(fictive_Status != null)
          {
            if(c_Determiner == null)
            {
              riddle = getRandomItem(GenerateRiddles.FICTIONAL_STATUS_EXPRESSIONS) + "You can spot me wearing " + s_Clothes + " lurking around " + s_Address +
                      ". If I had a choice, I would make " + s_Enemies + " public enemy number 1. Who am I?";
              riddleAnswer = character;
              riddles.put(riddle, riddleAnswer);
//            getHint(riddle, character/*, enemies, clothes, address*/);
            }
            else
            {
              riddle = getRandomItem(GenerateRiddles.FICTIONAL_STATUS_EXPRESSIONS) + "You can spot me wearing " + c_Determiner + " " + s_Clothes + " lurking around " + s_Address +
                      ". If I had a choice, I would make " + s_Enemies + " public enemy number 1. Who am I?";
              riddleAnswer = character;
              riddles.put(riddle, riddleAnswer);
//            getHint(riddle, character/*, enemies, clothes, address*/);
            }
          }
          else
          {
            if(c_Determiner == null)
            {
              riddle = getRandomItem(GenerateRiddles.NON_FICTIONAL_STATUS_EXPRESSIONS) + "You can spot me wearing " + s_Clothes + " lurking around " + s_Address +
                      ". If I had a choice, I would make " + s_Enemies + " public enemy number 1. Who am I?";
              riddleAnswer = character;
              riddles.put(riddle, riddleAnswer);
//            getHint(riddle, character/*, enemies, clothes, address*/);
            }
            else
            {
              riddle = getRandomItem(GenerateRiddles.NON_FICTIONAL_STATUS_EXPRESSIONS) + "You can spot me wearing " + c_Determiner + " " + s_Clothes + " lurking around " + s_Address +
                      ". If I had a choice, I would make " + s_Enemies + " public enemy number 1. Who am I?";
              riddleAnswer = character;
              riddles.put(riddle, riddleAnswer);
//            getHint(riddle, character/*, enemies, clothes, address*/);
            }
          }
        }
      }
    }
    return riddles;
  }

//  public void getHint(String riddle, String character/*, Vector<String> enemies, Vector<String> clothes, Vector<String> address*/)
//  {
//    String hint;
//    Vector<String> category = NOC.getFieldValues("Category", character);
//    Vector<String> domain = NOC.getFieldValues("Domains", character);
//    Vector<String> ficWorld = NOC.getFieldValues("Fictional World", character);
//
//    ArrayList<String> hints = new ArrayList<String>();
//
//    Random random = new Random();
//    int val;
//    for(int i  =  0; i < 10; i++)
//    {
//      if(!riddle.contains(category.elementAt(i)) &&  !category.isEmpty())
//      {
//        val =  random.nextInt(category.size());
//        hint = "I am " + getIndefiniteArticleFor(category.elementAt(val)) + " " +
//                category.elementAt(val);
//        hints.add(hint);
//
//      }
//      if(!riddle.contains(domain.elementAt(i)) && !domain.isEmpty())
//      {
//        val =  random.nextInt(domain.size());
//        hint = "I'm usually present in " + domain.elementAt(val);
//        hints.add(hint);
//      }
//      if(!riddle.contains(ficWorld.elementAt(i)) && !ficWorld.isEmpty())
//      {
//        val  =  random.nextInt(ficWorld.size());
//        hint  = "You've probably seen me in " + ficWorld.elementAt(val);
//        hints.add(hint);
//      }
//
//      Riddle riddleHint = new Riddle(hints);
//    }
//  }

  private  <T> T getRandomItem(List<T> list) {
    return list.get(RANDOM.nextInt(list.size()));
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
