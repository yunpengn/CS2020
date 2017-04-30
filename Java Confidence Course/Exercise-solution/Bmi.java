import java.util.Scanner;

/**
 * @author Jiayee
 *
 */
public class Bmi {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Input height: ");
        double h = myScanner.nextDouble();
        System.out.print("Input weight: ");
        double w = myScanner.nextDouble();
        System.out.printf("BMI: %.2f", w / Math.pow(h, 2));
    }

}
