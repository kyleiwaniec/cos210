package KW.AXA;

/** 
 * Person is a class that represents a human being.
 * The Person2 class has been modified to include exercise solutions
 * @author Koffman and Wolfgang
 **/
public class Person2 {
    // Data Fields

    /** The given name */
    private String givenName;
    /** The family name */
    private String familyName;
    /** The ID number */
    private String IDNumber;
    /** The birth year */
    private int birthYear = 1900;
// Insert solution to programming exercise 2, section 7, chapter A here
    // Constants
    /** The age at which a person can vote */
    private static final int VOTE_AGE = 18;
    /** The age at which a person is considered a senior citizen */
    private static final int SENIOR_AGE = 65;

    // Constructors
    /** Construct a person with given values
     *  @param first The given name
     *  @param family The family name
     *  @param ID The ID number
     *  @param birth The birth year
     */
    public Person2(String first, String family, String ID, int birth) {
        givenName = first;
        familyName = family;
        IDNumber = ID;
        birthYear = birth;
    }

    /** Construct a person with only an IDNumber specified.
     *  @param ID The ID number
     */
    public Person2(String ID) {
        IDNumber = ID;
    }

    // Modifier Methods
    /** Sets the givenName field.
     *  @param given The given name
     */
    public void setGivenName(String given) {
        givenName = given;
    }

    /** Sets the familyName field.
     *  @param family The family name
     */
    public void setFamilyName(String family) {
        familyName = family;
    }

    /** Sets the birthYear field.
     *  @param birthYear The year of birth
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

// Insert solution to programming exercise 2, section 7, chapter A here

    // Accessor Methods
    /** Gets the person's given name.
     *  @return the given name as a String
     */
    public String getGivenName() {
        return givenName;
    }

    /** Gets the person's family name.
     *  @return the family name as a String
     */
    public String getFamilyName() {
        return familyName;
    }

    /** Gets the person's ID number.
     *  @return the ID number as a String
     */
    public String getIDNumber() {
        return IDNumber;
    }

    /** Gets the person's year of birth.
     *  @return the year of birth as an int value
     */
    public int getBirthYear() {
        return birthYear;
    }

// Insert solution to programming exercise 2, section 7, chapter A here

    // Other Methods
    /** Calculates a person's age at this year's birthday.
     *  @param year The current year
     *  @return the year minus the birth year
     */
    public int age(int year) {
        return year - birthYear;
    }

    /** Determines whether a person can vote.
     *  @param year The current year
     *  @return true if the person's age is greater than or
     *  equal to the voting age
     */
    public boolean canVote(int year) {
        int theAge = age(year);
        return theAge >= VOTE_AGE;
    }

    /** Determines whether a person is a senior citizen.
     *  @param year the current year
     *  @return true if person's age is greater than or
     *  equal to the age at which a person is
     *  considered to be a senior citizen
     */
    public boolean isSenior(int year) {
        return age(year) >= SENIOR_AGE;
    }

// Insert solution to programming exercise 2, section 7, chapter A here

// Insert solution to programming exercise 3, section 7, chapter A here

// Insert solution to programming exercise 4, section 7, chapter A here

// Insert solution to programming exercise 1, section 7, chapter A here
}
