/**
 * @author Jiayee
 *
 */
public class Slide29ControlFlow {

    public static void main(String[] args) {

        String day = "Saturday";
        if (day.equals("Saturday")) {           // if (expression) {
            System.out.println("I am in JCC!");
        } else if (day.equals("Sunday")) {      // } else if (expression) { ...
            System.out.println("Sayonara weekend.");
        } else {
            System.out.println("Hell has begun.");
        }

        int[] array = {1, 2, 3};

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        int j = 0;
        while (j < array.length) {
            System.out.println(array[j++]);
        }

        int k = 0;
        // prints 1, 2, 3
        do {
            System.out.println(++k);
        } while (k < 3);

        // fast enumeration: cannot modify any element in the iterable (i.e. Array) during this loop
        for (int m : array) {
            System.out.println(m);
        }
    }

}
