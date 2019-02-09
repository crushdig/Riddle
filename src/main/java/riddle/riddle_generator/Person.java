package riddle_generator;

public class Person 
{
  private String name, canonical_Name, gender, address, vehicle, clothing, weapon, portrayed_By;
  
  public Person(String name, String canonical_Name, String gender, String address, String vehicle, String clothing, String weapon, String portrayed_By)
  {
    this.name = name;
    this.canonical_Name = canonical_Name;
    this.gender = gender;
    this.address = address;
    this.vehicle = vehicle;
    this.clothing = clothing;
    this.weapon =  weapon;
    this.portrayed_By = portrayed_By; 
  }
  
  // gets Name or canonical name of character based on user specification
  String getName()
  {
    return name;
  }
  
  String getCanonicalName()
  {
    return canonical_Name;
  }
  
  String getGender()
  {
    return gender;
  }
  
  String getAddress()
  {
    return address;
  }
  
  String getVehicle()
  {
    return vehicle;
  }
  
  String getClothing()
  {
    return clothing;
  }
  
  String getWeapon()
  {
    return weapon;
  }
  
  String getPortrayal()
  {
    return portrayed_By;
  }
}
