package sg.edu.nus.cs2020;

public class Tower implements Comparable<Tower> {
	private int location = -1;
	private int[] range = new int[2];

	public Tower(int location, int[] range) {
		this.location = location;
		this.range = range;
	}

	public int getLocation() {
		return location;
	}

	public int[] getRange() {
		return range;
	}

	@Override
	public int compareTo(Tower other) {
		int[] range1 = this.range;
		int[] range2 = other.getRange();
		
		// A partial order is being defined here. It only depends on the left
		// bound of the range (where the signal starts from the left).
		if (range1[0] < range2[0]) {
			return -1;
		} else if (range1[0] == range2[0]) {
			return 0;
		} else {
			return 1;
		}
	}
}
