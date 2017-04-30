import java.util.Calendar;

/**
 * 
 */

/**
 * @author Jiayee
 *
 */
public class Speaker5 {

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

    public void say(String message) {
        System.out.println(message);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Lecturer martin = new Lecturer("Prof Martin", "Computer Science");
        martin.say("We will be talking about abstraction today.");
        martin.lecture("Abstraction is key!");
    }

}
