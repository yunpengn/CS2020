/**
 * @author Jiayee
 *
 */
public class Vessel2 {

    private static final String MESSAGE_INIT = "Initialising!";
    private double displacement;

    Vessel(double displacement) {
        this.displacement = displacement;
    }

    public double getDisplacement() {
        return displacement;
    }

    public static void printInitMessage() {
        System.out.println(MESSAGE_INIT);
    }

    public static void main(String[] args) {
        ContainerShip someShip = new ContainerShip(2017, new String[] {"C1", "C2"});
        ContainerShip anotherShip = new ContainerShip(2018);
        System.out.println(someShip.getNumContainers());
        anotherShip.printContainers();
        // System.out.println(someShip.numContainers); // not allowed
    }

}
