/**
 * @author Jiayee
 *
 */
public class Slide47SpeakerAccess {

    private String name;
    private int birthYear;

    Slide47SpeakerAccess(String name) {
        this(name, -1);
    }

    Slide47SpeakerAccess(String name, int birthYear) {
        this.name = name;
        if (birthYear >= 0) {
            this.birthYear = birthYear;
        }
    }

    public static void main(String[] args) {
        // System.out.println(birthYear); // compile error because never declared (birthYear is instance variable)
        Slide47SpeakerAccess jiayee = new Slide47SpeakerAccess("Jiayee");
        Slide47SpeakerAccess martin = new Slide47SpeakerAccess("Prof Martin", 0);
        System.out.println(jiayee.birthYear); // declared + not initialised and thus has a default value
        System.out.println(martin.name);
        jiayee.name = "Not Jiayee";
    }

}
