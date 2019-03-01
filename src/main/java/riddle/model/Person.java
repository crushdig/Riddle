package riddle.model;

import java.util.Vector;

public class Person
{
    private Vector<String> character;
    private Vector<String> aka;
    private Vector<String> canonicalName;
    private Vector<String> gender;
    private Vector<String> addressOne;
    private Vector<String> addressTwo;
    private Vector<String> addressThree;
    private Vector<String> politics;
    private Vector<String> maritalStatus;
    private Vector<String> opponent;
    private Vector<String> typicalActivity;
    private Vector<String> vehicleOfChoice;
    private Vector<String> weaponOfChoice;
    private Vector<String> clothing;
    private Vector<String> domains;
    private Vector<String> genres;
    private Vector<String> fictiveStatus;
    private Vector<String> personPortrayal;
    private Vector<String> creator;
    private Vector<String> creation;
    private Vector<String> groupAffiliation;
    private Vector<String> fictionalWorld;
    private Vector<String> category;
    private Vector<String> negativeTalkingPoints;
    private Vector<String> positiveTalkingPoints;

    public Person()
    {

    }

    public Person(Vector<String> character, Vector<String> aka, Vector<String> canonical_Name, Vector<String> gender,
                  Vector<String> address_One, Vector<String> address_Two, Vector<String> address_Three,
                  Vector<String> politics, Vector<String> marital_Status, Vector<String> opponent, Vector<String> typical_Activity,
                  Vector<String> vehicle_Of_Choice, Vector<String> weapon_Of_Choice, Vector<String> seen_Wearing, Vector<String> domains,
                  Vector<String> genres, Vector<String> fictive_Status, Vector<String> portrayed_By, Vector<String> creator,
                  Vector<String> creation, Vector<String> group_Affiliation, Vector<String> fictional_World, Vector<String> category,
                  Vector<String> negative_Talking_Points, Vector<String> positive_Talking_Points) {

        this.character = character;
        this.aka = aka;
        this.canonicalName = canonical_Name;
        this.gender = gender;
        this.addressOne = address_One;
        this.addressTwo = address_Two;
        this.addressThree = address_Three;
        this.politics = politics;
        this.maritalStatus = marital_Status;
        this.opponent = opponent;
        this.typicalActivity = typical_Activity;
        this.vehicleOfChoice = vehicle_Of_Choice;
        this.weaponOfChoice = weapon_Of_Choice;
        this.clothing = seen_Wearing;
        this.domains = domains;
        this.genres = genres;
        this.fictiveStatus = fictive_Status;
        this.personPortrayal = portrayed_By;
        this.creator = creator;
        this.creation = creation;
        this.groupAffiliation = group_Affiliation;
        this.fictionalWorld = fictional_World;
        this.category = category;
        this.negativeTalkingPoints = negative_Talking_Points;
        this.positiveTalkingPoints = positive_Talking_Points;
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
        return this.canonicalName;
    }

    public Vector<String> getGender()
    {
        return this.gender;
    }

    public Vector<String> getAddressOne()
    {
        return this.addressOne;
    }

    public Vector<String> getAddressTwo()
    {
        return this.addressTwo;
    }

    public Vector<String> getAddressThree()
    {
        return this.addressThree;
    }

    public Vector<String> getPoliticalStance()
    {
        return this.politics;
    }

    public Vector<String> getMaritalStatus()
    {
        return  this.maritalStatus;
    }

    public Vector<String> getOpponent()
    {
        return this.opponent;
    }

    public Vector<String> getTypicalActivity()
    {
        return this.typicalActivity;
    }

    public Vector<String> getVehicleOfChoice()
    {
        return this.vehicleOfChoice;
    }

    public Vector<String> getWeaponOfChoice()
    {
        return this.weaponOfChoice;
    }

    public Vector<String> getClothing()
    {
        return this.clothing;
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
        return this.fictiveStatus;
    }

    public Vector<String> getPersonPortrayal()
    {
        return this.personPortrayal;
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
        return this.groupAffiliation;
    }

    public Vector<String> getFictionalWorld()
    {
        return this.fictionalWorld;
    }

    public Vector<String> getCategory()
    {
        return this.category;
    }

    public Vector<String> getNegativeTalkingPoints()
    {
        return this.negativeTalkingPoints;
    }

    public Vector<String> getPositiveTalkingPoints()
    {
        return this.positiveTalkingPoints;
    }

    public void setCharacter(Vector<String> character)
    {
        this.character = character;
    }

    public void setAKA(Vector<String> aka)
    {
        this.aka = aka;
    }

    public void setCanonicalName(Vector<String> canonicalName)
    {
        this.canonicalName = canonicalName;
    }

    public void setGender(Vector<String> gender)
    {
        this.gender = gender;
    }

    public void setAddressOne(Vector<String> addressOne)
    {
        this.addressOne = addressOne;
    }

    public void setAddressTwo(Vector<String> addressTwo)
    {
        this.addressTwo = addressTwo;
    }

    public void setAddressThree(Vector<String> addressThree)
    {
        this.addressThree =  addressThree;
    }

    public void setPoliticalStance(Vector<String> politics)
    {
        this.politics = politics;
    }

    public void setMaritalStatus(Vector<String> maritalStatus)
    {
        this.maritalStatus =  maritalStatus;
    }

    public void setOpponent(Vector<String> opponent)
    {
        this.opponent = opponent;
    }

    public void setTypicalActivity(Vector<String> typicalActivity)
    {
        this.typicalActivity = typicalActivity;
    }

    public void setVehicleOfChoice(Vector<String> vehicleOfChoice)
    {
        this.vehicleOfChoice = vehicleOfChoice;
    }

    public void setWeaponOfChoice(Vector<String> weaponOfChoice)
    {
        this.weaponOfChoice = weaponOfChoice;
    }

    public void setClothing(Vector<String> clothing)
    {
        this.clothing = clothing;
    }

    public void setDomains(Vector<String> domains)
    {
        this.domains = domains;
    }

    public void setGenres(Vector<String> genres)
    {
        this.genres = genres;
    }

    public void setFicitiveStatus(Vector<String> fictiveStatus)
    {
        this.fictiveStatus = fictiveStatus;
    }

    public void setPersonPortrayal(Vector<String> portrayedBy)
    {
        this.personPortrayal = portrayedBy;
    }

    public void setCreator(Vector<String> creator)
    {
        this.creator = creator;
    }

    public void setCreation(Vector<String> creation)
    {
        this.creation = creation;
    }

    public void getGroupAffiliation(Vector<String> groupAffiliation)
    {
        this.groupAffiliation = groupAffiliation;
    }

    public void getFictionalWorld(Vector<String> fictionalWorld)
    {
        this.fictionalWorld = fictionalWorld;
    }

    public void setCategory(Vector<String> category)
    {
        this.category = category;
    }

    public void getNegativeTalkingPoints(Vector<String> negativeTalkingPoints)
    {
        this.negativeTalkingPoints = negativeTalkingPoints;
    }

    public void getPositiveTalkingPoints(Vector<String> positiveTalkingPoints)
    {
        this.positiveTalkingPoints = positiveTalkingPoints;
    }

}
