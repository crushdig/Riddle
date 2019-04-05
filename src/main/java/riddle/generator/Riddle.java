/**
 * Riddles is used to retrieve the riddle and answer of a particular character.
 */
package riddle.generator;

import riddle.model.Person;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Riddle
{
  private String question;
  private String answer;
  private String clientRegion = "eu-west-1";
  private String bucketName =  "tsv-lists";

  private KnowledgeBaseModule NOC;
  private Person characterDetails;

  /**
   * Sets up all the objects needed for accessing information from the NOC and initialises the Person object
   * @param question the riddle for a given character
   * @param answer the character associated with a riddle
   */
  public Riddle(String question, String answer) {
    NOC = new KnowledgeBaseModule(clientRegion, bucketName, "Veale's The NOC List.txt", 0);

    this.question = question;
    this.answer = answer; //character being guessed

    Vector<String> characterName = new Vector<String>(1);
    characterName.add(this.answer);

    characterDetails = new Person(characterName, NOC.getFieldValues("AKA", this.answer), NOC.getFieldValues("Canonical Name", this.answer),
            NOC.getFieldValues("Gender", this.answer), NOC.getFieldValues("Address 1", this.answer), NOC.getFieldValues("Address 2", this.answer),
            NOC.getFieldValues("Address 3", this.answer), NOC.getFieldValues("Politics", this.answer), NOC.getFieldValues("Marital Status", this.answer),
            NOC.getFieldValues("Opponent", this.answer), NOC.getFieldValues("Typical Activity", this.answer), NOC.getFieldValues("Vehicle of Choice", this.answer),
            NOC.getFieldValues("Weapon of Choice", this.answer), NOC.getFieldValues("Seen Wearing", this.answer), NOC.getFieldValues("Domains", this.answer),
            NOC.getFieldValues("Genres", this.answer), NOC.getFieldValues("Fictive Status", this.answer), NOC.getFieldValues("Portrayed By", this.answer),
            NOC.getFieldValues("Creator", this.answer), NOC.getFieldValues("Creation", this.answer), NOC.getFieldValues("Group Affiliation", this.answer),
            NOC.getFieldValues("Fictional World", this.answer), NOC.getFieldValues("Category", this.answer), NOC.getFieldValues("Negative Talking Points", this.answer),
            NOC.getFieldValues("Positive Talking Points", this.answer));
  }

  /**
   * Returns every constructed riddle
   * @return a question in the form of a riddle
   */
  public String getQuestion()
  {
    return question;
  }

  /**
   * Returns every constructed answer
   * @return an answer in the form of a character
   */
  public String getAnswer()
  {
    return answer;
  }

  /**
   * Returns
   * @return  a Character Details object
   */
  public Person getPerson()
  {
    return characterDetails;
  }


  public static void main(String[] args)
  {
    //have set
    ArrayList<String> riddleSet = new ArrayList<String>(); // set ensures no repetition with riddles occurs, all values are unique
    GenerateRiddles d = new GenerateRiddles();
    Hint t = new Hint();


//    Riddle h = d.randomiseRiddles();
//    //while h in set
//    while(riddleSet.contains(h))
//    {
//      h = d.randomiseRiddles();
//    }
//    riddleSet.add(h);e

    Scanner s = new Scanner(System.in);
    String f = "y";
//    System.out.println("Hello1 " + getPerson());
    while(f.contains("y"))
    {
      Riddle h = d.randomiseRiddles();
//      //while h in set
      while(riddleSet.contains(h.getQuestion()))
      {
        h = d.randomiseRiddles();
      }
      riddleSet.add(h.getQuestion());

      System.out.println(h.getQuestion());
      System.out.println(h.getAnswer());
      System.out.println(t.getHint());
      System.out.println("Do you want another riddle: ");
      f = s.nextLine();
    }
    s.close();
//    for(int i = 0; i < riddleSet.size(); i++)
//    {
//      if(riddleSet.)
//    }
    // call randomise



  }

}
