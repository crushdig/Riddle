package riddle.riddle_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Riddle
{
  private String question;
  private String answer;

  public Riddle(String question, String answer)
  {
    this.question = question;
    this.answer = answer;
  }

  public String getQuestion()
  {
    return question;
  }

  public String getAnswer()
  {
    return answer;
  }

  public static void main(String args[]) throws IOException {
    //have set
    ArrayList<String> riddleSet = new ArrayList<String>(); // set ensures no repetition with riddles occurs, all values are unique
    GenerateRiddles d = new GenerateRiddles();

//    Riddle h = d.randomiseRiddles();
//    //while h in set
//    while(riddleSet.contains(h))
//    {
//      h = d.randomiseRiddles();
//    }
//    riddleSet.add(h);

    Scanner s = new Scanner(System.in);
    String f = "y";
    System.out.println(d);
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
