package riddle.model;

public enum PersonProperty
{
    CHARACTER("name"),
    AKA("aka"),
    CANONICAL_NAME("cname"),
    GENDER("gender"),
    ADDRESS_ONE("Address 1"),
    ADDRESS_TWO("Address 2"),
    ADDRESS_THREE("Address 3"),
    POLITICS("politics"),
    MARITAL_STATUS("marital status"),
    OPPONENT("opponent"),
    TYPICAL_ACTIVITY("typical activity"),
    VEHICLE_OF_CHOICE("vehicle of choice"),
    WEAPON_OF_CHOICE("weapon of choice"),
    SEEN_WEARING("seen wearing"),
    DOMAINS("domains"),
    GENRES("genres"),
    FICTIVE_STATUS("fictive status"),
    PORTRAYED_BY("portrayed by"),
    CREATOR("creator"),
    CREATION("creation"),
    GROUP_AFFILIATION("group affiliation"),
    FICTIONAL_WORLD("fictional world"),
    CATEGORY("category"),
    NEGATIVE_TALKING_POINTS("negative talking points"),
    POSITIVE_TALKING_POINTS("positive talking points");

    private String value;

    /**
     * Returns a value of type string
     * @return a string value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets up value with respective element
     * @param value
     */
    PersonProperty(String value) {
        this.value = value;
    }
}
