package riddle.model;

import java.util.Vector;

public class Person
{
    private Vector<String> character;
    private Vector<String> aka;
    private Vector<String> canonical_Name;
    private Vector<String> gender;
    private Vector<String> address_One;
    private Vector<String> address_Two;
    private Vector<String> address_Three;
    private Vector<String> politics;
    private Vector<String> marital_Status;
    private Vector<String> opponent;
    private Vector<String> typical_Activity;
    private Vector<String> vehicle_Of_Choice;
    private Vector<String> weapon_Of_Choice;
    private Vector<String> seen_Wearing;
    private Vector<String> domains;
    private Vector<String> genres;
    private Vector<String> fictive_Status;
    private Vector<String> portrayed_By;
    private Vector<String> creator;
    private Vector<String> creation;
    private Vector<String> group_Affiliation;
    private Vector<String> fictional_World;
    private Vector<String> category;
    private Vector<String> negative_Talking_Points;
    private Vector<String> positive_Talking_Points;


    public Person(Vector<String> character, Vector<String> aka, Vector<String> canonical_Name, Vector<String> gender,
                  Vector<String> address_One, Vector<String> address_Two, Vector<String> address_Three,
                  Vector<String> politics, Vector<String> marital_Status, Vector<String> opponent, Vector<String> typical_Activity,
                  Vector<String> vehicle_Of_Choice, Vector<String> weapon_Of_Choice, Vector<String> seen_Wearing, Vector<String> domains,
                  Vector<String> genres, Vector<String> fictive_Status, Vector<String> portrayed_By, Vector<String> creator,
                  Vector<String> creation, Vector<String> group_Affiliation, Vector<String> fictional_World, Vector<String> category,
                  Vector<String> negative_Talking_Points, Vector<String> positive_Talking_Points) {

        this.character = character;
        this.aka = aka;
        this.canonical_Name = canonical_Name;
        this.gender = gender;
        this.address_One = address_One;
        this.address_Two = address_Two;
        this.address_Three = address_Three;
        this.politics = politics;
        this.marital_Status = marital_Status;
        this.opponent = opponent;
        this.typical_Activity = typical_Activity;
        this.vehicle_Of_Choice = vehicle_Of_Choice;
        this.weapon_Of_Choice = weapon_Of_Choice;
        this.seen_Wearing = seen_Wearing;
        this.domains = domains;
        this.genres = genres;
        this.fictive_Status = fictive_Status;
        this.portrayed_By = portrayed_By;
        this.creator = creator;
        this.creation = creation;
        this.group_Affiliation = group_Affiliation;
        this.fictional_World = fictional_World;
        this.category = category;
        this.negative_Talking_Points = negative_Talking_Points;
        this.positive_Talking_Points = positive_Talking_Points;
    }

    // gets Name or canonical name of character based on user specification
    public Vector<String> getCharacter()
    {
        return this.character;
    }

    public Vector<String> getAKA()
    {
        return this.aka;
    }

    public Vector<String> getCanonicalName()
    {
        return this.canonical_Name;
    }

    public Vector<String> getGender()
    {
        return this.gender;
    }

    public Vector<String> getAddressOne()
    {
        return this.address_One;
    }

    public Vector<String> getAddressTwo()
    {
        return this.address_Two;
    }

    public Vector<String> getAddressThree()
    {
        return this.address_Three;
    }

    public Vector<String> getPoliticalStance()
    {
        return this.politics;
    }

    public Vector<String> getMaritalStatus()
    {
        return  this.marital_Status;
    }

    public Vector<String> getOpponent()
    {
        return this.opponent;
    }

    public Vector<String> getTypicalActivity()
    {
        return this.typical_Activity;
    }

    public Vector<String> getVehicleOfChoice()
    {
        return this.vehicle_Of_Choice;
    }

    public Vector<String> getWeaponOfChoice()
    {
        return this.weapon_Of_Choice;
    }

    public Vector<String> getClothing()
    {
        return this.seen_Wearing;
    }

    public Vector<String> getDomains()
    {
        return this.domains;
    }

    public Vector<String> getGenres()
    {
        return this.genres;
    }

    public Vector<String> getFicitiveStatus()
    {
        return this.fictive_Status;
    }

    public Vector<String> getPersonPortrayal()
    {
        return this.portrayed_By;
    }

    public Vector<String> getCreator()
    {
        return this.creator;
    }

    public Vector<String> getCreation()
    {
        return this.creation;
    }

    public Vector<String> getGroupAffiliation()
    {
        return this.group_Affiliation;
    }

    public Vector<String> getFictionalWorld()
    {
        return this.fictional_World;
    }

    public Vector<String> getCategory()
    {
        return this.category;
    }

    public Vector<String> getNegativeTalkingPoints()
    {
        return this.negative_Talking_Points;
    }

    public Vector<String> getPositiveTalkingPoints()
    {
        return this.positive_Talking_Points;
    }

}
