package sg.edu.nus.cs2020;

public class Probe implements Comparable<Probe> {
	private int value = 0;
	private int index = -1;

	public Probe(int value, int index) {
		this.value = value;
		this.index = index;
	}

	public int getValue() {
		return value;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public int compareTo(Probe other) {
		if (this.value < other.getValue()) {
			return -1;
		} else if (this.value == other.getValue()) {
			return 0;
		} else {
			return 1;
		}
	}
}
