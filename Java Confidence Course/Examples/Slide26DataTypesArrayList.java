import java.util.ArrayList;

/**
 * @author Jiayee
 *
 */
public class Slide26DataTypesArrayList {

    public static void main(String[] args) {
        // Declare and initialise ArrayList
        ArrayList<Integer> aList = new ArrayList<>();

        // Build an ArrayList of 3 Integer objects with the same value 0
        aList.add(0); // increases the size of the ArrayList
        System.out.println("Size: " + aList.size());

        // Read the ArrayList and print each value
        System.out.println(aList.get(0)); // get(index)
        System.out.println(aList.get(1));
        System.out.println(aList.get(2));

        // Set the values of the Integer objects in ArrayList
        aList.set(0, 1); // set(index, value)
        aList.set(0, 2);
        aList.set(0, 3);

        // Prints the ArrayList
        System.out.println(aList); // prints aList.toString()
    }

}
