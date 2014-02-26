package KW.CH01;

/**
 * Person is a class that represents a human being.
 *
 * @author Koffman and Wolfgang
 */
public class Person {
    // Constants

    /** The age at which a person can vote */
    private static final int VOTE_AGE = 18;
    /** The age at which a person is considered a senior citizen */
    private static final int SENIOR_AGE = 65;
    // Data Fields
    /** The first name */
    private String firstName;
    /** The last name */
    private String lastName;
    /** The ID number */
    private String IDNumber;
    /** The birth year */
    private int birthYear = 1900;

    // Constructors
    /**
     * Construct a person with given values
     * @param first The first name
     * @param last The last name
     * @param ID The ID number
     * @param birth The birth year
     */
    public Person(String first, String last, String ID, int birth) {
        firstName = first;
        lastName = last;
        IDNumber = ID;
        birthYear = birth;
    }

    /**
     * Construct a person with only an IDNumber specified.
     * @param ID The ID number
     */
    public Person(String ID) {
        IDNumber = ID;
    }

    // Modifier Methods
    /**
     * Sets the firstName field.
     *
     * @param first The first name
     */
    public void setFirstName(String first) {
        firstName = first;
    }

    /**
     * Sets the lastName field.
     *
     * @param last The last name
     */
    public void setLastName(String last) {
        lastName = last;
    }

    /**
     * Sets the birthYear field.
     *
     * @param birthYear The year of birth
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    // Accessor Methods
    /**
     * Gets the person's first name.
     *
     * @return the first name as a String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the person's last name.
     *
     * @return the last name as a String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the person's ID number.
     *
     * @return the ID number as a String
     */
    public String getIDNumber() {
        return IDNumber;
    }

    /**
     * Gets the person's year of birth.
     *
     * @return the year of birth as an int value
     */
    public int getBirthYear() {
        return birthYear;
    }

    // Other Methods
    /**
     * Calculates a person's age at this year's birthday.
     *
     * @param year The current year
     *
     * @return the year minus the birth year
     */
    public int age(int year) {
        return year - birthYear;
    }

    /**
     * Determines whether a person can vote.
     *
     * @param year The current year
     *
     * @return true if the person's age is greater than or equal to
     *         the voting age
     */
    public boolean canVote(int year) {
        int theAge = age(year);

        return theAge >= VOTE_AGE;
    }

    /**
     * Determines whether a person is a senior citizen.
     *
     * @param year the current year
     *
     * @return true if person's age is greater than or equal to the
     *         age at which a person is considered to be a senior
     *         citizen
     */
    public boolean isSenior(int year) {
        return age(year) >= SENIOR_AGE;
    }

    /**
     * Retrieves the information in a Person object.
     *
     * @return the object state as a string
     */
    @Override
    public String toString() {
        return "First name: " + firstName + "\n" + "Last name: "
                + lastName + "\n" + "ID number: " + IDNumber + "\n"
                + "Year of birth: " + birthYear + "\n";
    }

    /**
     * Compares two Person objects for equality.
     *
     * @param per The second Person object
     *
     * @return true if the Person objects have same ID number; false
     *         if they don't
     */
    public boolean equals(Person per) {
        if (per == null) {
            return false;
        } else {
            return IDNumber.equals(per.IDNumber);
        }
    }

// Insert solution to programming exercise 2, section 1, chapter 1 here
    public int compareTo(Person per) {
        if (lastName.equals(per.lastName)) {
            return firstName.compareTo(per.firstName);
        } else {
            return lastName.compareTo(per.lastName);
        }
    }

// Insert solution to programming exercise 3, section 1, chapter 1 here
    public void changeLastName(boolean justMarried, String newLast) {
        if (justMarried) {
            lastName = newLast;
        }
    }
}
