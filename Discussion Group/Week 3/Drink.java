package discussion_group;

public class Drink {
	protected String name;
	private int temperature;
	private String color;

	public Drink(String n, int temp, String c) {
		System.out.println("Drink constructor.");

		name = n;
		temperature = temp;
		color = c;

		isGood(temp, "clear");
		checkSoda();
	}

	public void checkSoda() {
		System.out.println("Drinking a drink, with name: " + name);
	}

	public Drink getADrink() {
		System.out.println("Getting a drink.");
		return new Drink("Unknown", 10, "Opaque");
	}

	public static boolean isGood(int temp, String color) {
		if (color == "black") {
			System.out.println("It's good! ");
			return true;
		} else {
			System.out.println("Ugh! ");
			return false;
		}
	}

	public int getTemperature() {
		return temperature;
	}

	public String getColor() {
		return color;
	}
}