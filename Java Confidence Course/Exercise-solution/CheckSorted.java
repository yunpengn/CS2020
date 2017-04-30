import java.util.ArrayList;

/**
 * @author Jiayee
 *
 */
public class CheckSorted {

    public static void main(String[] args) {
        // Declare and initialise two ArrayList
        ArrayList<Double> arrayListDouble = new ArrayList<>();
        ArrayList<String> arrayListString = new ArrayList<>();

        //

        /* Solution below uses for-loops */
        // Build two ArrayList of 10 Double and 10 String 
        for (int i = 0; i < 30; i++) {
            arrayListDouble.add(0.0 + i);
            arrayListString.add("" + i);
        }
        
        // Check if both ArrayList are sorted
        boolean doubleSorted = true;
        for (int i = 0; i < arrayListDouble.size() - 1; i++) {
            System.out.println(arrayListDouble.get(i));
            if (arrayListDouble.get(i) > arrayListDouble.get(i + 1)) {
                doubleSorted = false;
                break;
            }
        }
        if (doubleSorted) {
            System.out.println("arrayListDouble is sorted.");
        } else {
            System.out.println("arrayListDouble is not sorted.");
        }
        
        boolean stringSorted = true;
        for (int i = 0; i < arrayListString.size() - 1; i++) {
            System.out.println(arrayListString.get(i));
            if (arrayListString.get(i).compareTo(arrayListString.get(i + 1)) == 1) {
                stringSorted = false;
                break;
            }
        }
        if (stringSorted) {
            System.out.println("arrayListString is sorted.");
        } else {
            System.out.println("arrayListString is not sorted.");
        }
    }

}
