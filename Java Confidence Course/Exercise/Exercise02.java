package jcc;

public class Exercise02 {
	public static void main(String[] args) {
		int age = 18;
		char favouriteLetter = 'P';
		boolean hasEaten = true;
		double height = 1.78;
		String name = "Neil";
		String[] modulesTaken = new String[] {"CS2020", "MA1506", "CG1108", "ES1531"};
		System.out.println("Age: " + age);
		System.out.println("Favourite Letter: " + favouriteLetter);
		System.out.println("Height: " + height);
		System.out.println("Has eaten or not: " + hasEaten);
		System.out.println("Name: " + name);
		System.out.println("Modules Taken: ");
		for (String module : modulesTaken) {
			System.out.println(module);
		}
	}
}
