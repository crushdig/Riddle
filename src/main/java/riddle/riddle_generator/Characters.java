//package riddle_generator;
//
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Vector;
//
//public class Characters
//{
//  public ArrayList<Person> characters = new ArrayList<Person>();
//  KnowledgeBaseModule NOC;
//
//  public Characters()
//  {
//    NOC = new KnowledgeBaseModule("C:\\Users\\bolu_\\workspace\\Riddle\\TSV Lists\\Veale's The NOC List.txt");
//
//    Vector<String> characters_In_Character =  NOC.getAllFrames();
//
//    for(String character : characters_In_Character)
//    {
//      String canonical_Name;
//      String gender;
//      String address = null;
//      String vehicle;
//      String clothing;
//      String weapon;
//      String portrayed_By;
//
//      canonical_Name = NOC.getFirstValue("Canonical Name", character);
//      gender = NOC.getFirstValue("Gender", character);
//      vehicle = NOC.getFirstValue("Vehicle of Choice", character);
//      clothing = NOC.getFirstValue("Seen Wearing", character);
//      weapon = NOC.getFirstValue("Weapon of Choice", character);
//      portrayed_By = NOC.getFirstValue("Portrayed", character);
//
//      int addressVal = 1;
//
//      while(address == null && addressVal < 4)
//      {
//        address = NOC.getFirstValue("Address " + addressVal, character);
//        addressVal++;
//      }
//
//        characters.add(new Person(character, canonical_Name, gender, address, vehicle, clothing, weapon, portrayed_By));
//    }
//
//  }
//
////  public String getCharacter()
////  {
////    for(Person person : characters)
////    {
////      return person.getName();
////    }
////
////    return null;
////  }
//
//  public Person getCharacter(String name)
//  {
//    for(Person person : characters)
//    {
//      if(person.getName() == name)
//      {
//        return person;
//      }
//    }
//
//    return null;
//  }
//
//  public Person getRandomPersonFromCharacter()
//  {
//    int i = 0;
//    Random RND = new Random();
//    ArrayList<Person> character = new ArrayList<Person>();
//
//    Person rnd_Character;
//
//    for(Person person : characters)
//    {
//      if(person.getName() == NOC.getAllFrames().elementAt(i++))
//      {
//        character.add(person);
//      }
//    }
//
//    rnd_Character = character.get(RND.nextInt(character.size()));
//
//    return rnd_Character;
//  }
//
//}
