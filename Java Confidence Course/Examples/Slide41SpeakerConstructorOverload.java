/**
 * @author Jiayee
 *
 */
public class Slide41SpeakerConstructorOverload {

    private String name;
    private int birthYear;

    Slide41SpeakerConstructorOverload(String name) {
        this(name, -1);
    }

    Slide41SpeakerConstructorOverload(String name, int birthYear) {
        this.name = name;
        if (birthYear >= 0) {
            this.birthYear = birthYear;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
