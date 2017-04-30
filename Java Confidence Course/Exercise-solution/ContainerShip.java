/**
 * @author Jiayee
 *
 */
public class ContainerShip extends Vessel {

    private String[] containers;
    private static int numContainers;

    ContainerShip(double displacement) {
        super(displacement);
    }

    ContainerShip(double displacement, String[] containers) {
        super(displacement);
        this.containers = containers;
        numContainers += containers.length;
    }

    public int getNumContainers() {
        return numContainers;
    }

    public void printContainers() {
        if (containers == null) {
            System.out.println("NIL");
        } else {
            for (String c : containers) {
                System.out.println(c);
            }
        }
    }

}
