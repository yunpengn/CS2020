import java.util.Calendar;

/**
 * 
 */

/**
 * @author Jiayee
 *
 */
public class Speaker3 {

    private String name;
    private int birthYear;

    Speaker(String name) {
        this(name, -1);
    }

    Speaker(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getAge() {
        if (birthYear < 0) {
            return -1;                          // Age is unknown
        }
        Calendar now = Calendar.getInstance();  // Gets the current date and time
        int year = now.get(Calendar.YEAR);      // The current year
        return year - birthYear;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Speaker jiayee = new Speaker("Jiayee", 1996);
        System.out.printf("Hello, I am %s and I am %d this year.", jiayee.getName(), jiayee.getAge());
        System.out.printf("Hello, I am %s and I am %d this year.", jiayee.name, jiayee.getAge());
        // Directly accessing fields is allowed because the main method is within private access (same class)
    }

}
