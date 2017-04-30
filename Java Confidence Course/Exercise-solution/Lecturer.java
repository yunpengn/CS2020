/**
 * @author Jiayee
 *
 */
public class Lecturer extends Speaker {

    private String department;
    private static String LECTURE_SUFFIX = "You should be taking notes.";

    Lecturer(String name, String department) {
        super(name);
        this.setDepartment(department);
    }

    Lecturer(String name, String department, int birthYear) {
        this(name, department);
        this.setBirthYear(birthYear);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void lecture(String message) {
        super.say(message);
        System.out.println(LECTURE_SUFFIX);
    }

}
