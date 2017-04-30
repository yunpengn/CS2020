/**
 * @author Jiayee
 *
 */
public class Vessel {

    private static final String MESSAGE_INIT = "Initialising!";
    private double displacement;

    Vessel(double displacement) {
        this.displacement = displacement;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Vessel vessel = new Vessel(2017);
        System.out.println(vessel.displacement);
    }

}
