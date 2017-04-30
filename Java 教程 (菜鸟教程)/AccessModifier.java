package tutorial;

public class AccessModifier {
	private int num = 3;

	private final String TITLE = "Mastering Physics";

	public int getNum() {
		return num;
	}

	public int setNum(int num) {
		this.num = num;
		return num;
	}

	public String getTitle() {
		return TITLE;
	}

	public static void main(String[] args) {
		AccessModifier test = new AccessModifier();

		System.out.println(test.getNum());
		System.out.println(test.setNum(15));
		System.out.println(test.getTitle());
	}
}
