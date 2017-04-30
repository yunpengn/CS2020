package jcc;
import java.util.Scanner;

public class Exercise01 {
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		System.out.print("Please input your weight: ");
		double weight = myScanner.nextDouble();
		System.out.print("Please input your height: ");
		double height = myScanner.nextDouble();
		System.out.println("Your BMI is " + weight / height / height);
		myScanner.close();
	}
}
