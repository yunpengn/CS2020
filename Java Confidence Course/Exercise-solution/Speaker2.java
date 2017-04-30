/**
 * @author Jiayee
 *
 */
public class Speaker2 {

    private String name;
    private int birthYear;

    Speaker(String name) {
        this(name, -1);
    }

    Speaker(String name, int birthYear) {
        this.name = name;
        if (birthYear >= 0) {
            this.birthYear = birthYear;
        }
    }

    public static void main(String[] args) {
        // System.out.println(birthYear); // compile error because never declared (birthYear is instance variable)
        Speaker jiayee = new Speaker("Jiayee");
        Speaker martin = new Speaker("Prof Martin", 0);
        System.out.println(jiayee.birthYear); // declared + not initialised and thus has a default value
        System.out.println(martin.name);
    }

}
