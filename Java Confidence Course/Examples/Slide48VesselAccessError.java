/**
 * @author Jiayee
 *
 */
public class Slide48VesselAccessError {

    private static final String MESSAGE_INIT = "Initialising!";
    private double displacement;

    Slide48VesselAccessError(double displacement) {
        this.displacement = displacement;
    }

    public static void main(String[] args) {
        Slide48VesselAccessError vessel = new Slide48VesselAccessError(2017);
        System.out.println(vessel.displacement);

        Slide48SpeakerAccessError jiayee = new Slide48SpeakerAccessError("Jiayee");
        // jiayee.name = "Not Jiayee";
    }

}
