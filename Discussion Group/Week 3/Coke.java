package discussion_group;

public class Coke extends Drink {
	String cokeType;

	Coke(int temp, String c) {
		super("Coke", temp, "Black");
		System.out.println("Done with super; on to myself.");

		name = "Coke";
		cokeType = c;
		checkSoda();

		System.out.println("Coke constructor done!");
	}

	public static boolean isGood(int temp, double a) {
		System.out.println("Coke is good!");
		return true;
	}

	public void checkSoda() {
		System.out.println("Drinking a coke.");
		System.out.println("Coketype = " + cokeType);
	}

	public Coke getADrink() {
		System.out.println("Getting a coke.");
		return new Coke(5, "Black");
	}

	public static boolean isGood(int temp, String color) {
		System.out.println("Coke is always good!");
		return true;
	}

	public void useless() {
		System.out.printf("");
	}

	static public void main(String[] args) {
		Coke soda = new Coke(10, "black");

		soda.useless();
	}
}
