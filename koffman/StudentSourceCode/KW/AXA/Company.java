package KW.AXA;

// Insert solution to programming exercise 2, section 10, chapter A here

/*<listing chapter="A" number="3">*/
/** Company is a class that represents a company.
 *  The data field employees provides storage for
 *  an array of Person objects.
 *  @author Koffman and Wolfgang
 */
public class Company {
    // Data Fields

    /** The array of employees */
    private Person[] employees;
    /** The default size of the array */
    private static final int DEFAULT_SIZE = 100;

    // Methods
    /** Creates a new array of Person objects.
     *  @param size The size of array employees
     */
    public Company(int size) {
        employees = new Person[size];
    }

    public Company() {
        employees = new Person[DEFAULT_SIZE];
    }

    /** Sets field employees.
     *  @param emp The array of employees
     */
    public void setEmployees(Person[] emp) {
        employees = emp;
    }

    /** Gets field employees.
     *  @return employees array
     */
    public Person[] getEmployees() {
        return employees;
    }

    /** Sets an element of employees.
     *  @param index The position of the employee
     *  @param emp The employee
     */
    public void setEmployee(int index, Person emp) {
        if (index >= 0 && index < employees.length) {
            employees[index] = emp;
        }
    }

    /** Gets an employee.
     *  @param index The position of the employee
     *  @return The employee object or null if not defined
     */
    public Person getEmployee(int index) {
        if (index >= 0 && index < employees.length) {
            return employees[index];
        } else {
            return null;
        }
    }

    /** Builds a string consisting of all employee's
     *  data, with newline characters between employees.
     *  @return The object's state
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < employees.length; i++) {
            result.append(employees[i] + "\n");
        }
        return result.toString();
    }

// Insert solution to programming exercise 2, section 8, chapter A here

// Insert solution to programming exercise 2, section 10, chapter A here
}
/*</listing>*/

