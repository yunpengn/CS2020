package sg.edu.nus.cs2020;

public class StringInitialization {

	public static void main(String[] args) {
		String a = "Singapore";
		String b = "Singapore";

		String c = new String("China");
		String d = new String("China");

		System.out.println(a == b);
		System.out.println(c == d);
		// You should see the difference between ways of string initialization.
	}

}
