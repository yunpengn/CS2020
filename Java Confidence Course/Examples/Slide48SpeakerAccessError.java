/**
 * @author Jiayee
 *
 */
public class Slide48SpeakerAccessError {

    private String name;
    private int birthYear;

    Slide48SpeakerAccessError(String name) {
        this(name, -1);
    }

    Slide48SpeakerAccessError(String name, int birthYear) {
        this.name = name;
        if (birthYear >= 0) {
            this.birthYear = birthYear;
        }
    }

    /* public static void main(String[] args) {
        // System.out.println(birthYear); // compile error because never declared (birthYear is instance variable)
        Slide48SpeakerAccessError jiayee = new Slide48SpeakerAccessError("Jiayee");
        Slide48SpeakerAccessError martin = new Slide48SpeakerAccessError("Prof Martin", 0);
        System.out.println(jiayee.birthYear); // declared + not initialised and thus has a default value
        System.out.println(martin.name);
        jiayee.name = "Not Jiayee";
    } */

}
