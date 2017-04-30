package sg.edu.nus.cs2020;

public class StringComponent {
	private String value;
	private int x;
	private int y;

	public StringComponent(int x, int y, String value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getValue() {
		return value;
	}
}
