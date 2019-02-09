package riddle.riddle_generator;

import riddle.riddle_generator.KnowledgeBaseModule;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class GenreRiddles
{
  private String clientRegion = "eu-west-1";
  private String bucketName =  "tsv-lists";
  private KnowledgeBaseModule NOC;
  private KnowledgeBaseModule CATEGORIES = null;
  private KnowledgeBaseModule CLOTHES    = null;
  private KnowledgeBaseModule VEHICLES   = null;

  public GenreRiddles() throws IOException {
    NOC = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's The NOC List.txt", 0);
    CATEGORIES = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's Category Hierarchy.txt", 1);
    VEHICLES = new KnowledgeBaseModule(clientRegion, bucketName,"Veale's vehicle fleet.txt", 1);
    CLOTHES = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's clothing line.txt", 1);
  }

  public HashMap<String, String> setupGenreRiddles()
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

      Vector<String> clothes = NOC.getFieldValues("Seen Wearing", character);
      Vector<String> genres = NOC.getFieldValues("Genres", character);
      Vector<String> occupations = NOC.getFieldValues("Category", character);

      int k = 0;
      for(k = 0; k < 10; k++)
      {
        String s_Genres = null, s_Clothes = null, s_Occupations = null;
        String c_Determiner;

        if(clothes != null && genres !=  null && occupations != null)
        {

          int j;

          ;
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

          for(j = 0; j < occupations.size(); j++)
          {
            s_Occupations = (String) occupations.elementAt(j);
          }
          occupations.remove(s_Occupations);

          if(s_Clothes == null || s_Genres == null || s_Occupations == null)
          {
            continue;
          }

          c_Determiner = CLOTHES.getFirstValue("Determiner", s_Clothes);

          if(c_Determiner == null)
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". You'll usually find me in " +
                    s_Genres + " wearing " + s_Clothes + ". Who am I?";
            riddleAnswer = character;
            riddles.put(riddle, riddleAnswer);
          }
          else
          {
            riddle = "I am " + getIndefiniteArticleFor(s_Occupations) + " " + s_Occupations + ". You'll usually find me in " +
                    s_Genres + " wearing " + c_Determiner + " " + s_Clothes + ". Who am I?";
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
