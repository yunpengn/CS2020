package tutorial;

public class NumberCharacterString {
	public static void main(String[] args) {
		System.out.println(Math.round(5.6));
		System.out.println(Math.toRadians(90));
		System.out.println(Character.isLetterOrDigit('9'));
		System.out.println(Character.isLetterOrDigit(9));
		System.out.println("Welcome to ".concat("Harbin!"));
		System.out.println("Welcome to " + "Harbin again!");
		System.out.println("Harbin".compareToIgnoreCase("haerbin"));
		System.out.println("Harbin".compareToIgnoreCase("haerbin") > 0);
		System.out.println("This is NUS.".lastIndexOf('i'));
	}
}
