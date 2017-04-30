package sg.edu.nus.cs2020;

import java.util.ArrayList;

public class CoverageCalculator {
	private int length = -1;
	ArrayList<Tower> towers = null;

	public CoverageCalculator(int length) {
		this.length = length;
		towers = new ArrayList<Tower>();
	}

	// Adds a new tower to the arrayList.
	public void addTower(int base, int power) {
		if (base > length || base < 0) {
			System.err.println("Error: invalid location.");
			return;
		} else if (power < 0) {
			System.err.println("Error: invalid setting of power range");
			return;
		}

		int lowerBound = base - power, upperBound = base + power;

		if (lowerBound < 0) {
			lowerBound = 0;
		}

		if (upperBound > length) {
			upperBound = length;
		}

		int range[] = {lowerBound, upperBound};
		
		towers.add(new Tower(base, range));
	}

	// Calculates the signal coverage based on towers presented.
	public int getCoverage() {
		// We first sort the arrayList of towers. Notice: ArrayList.sort()
		// method needs an instance whose class has implemented Comparator
		// instead of Comparable, therefore, TowerComparator.java is used and it
		// is based on the compareTo method in Tower.java
		TowerComparator comparator = new TowerComparator();
		towers.sort(comparator);

		// The number of current towers presented.
		int size = towers.size();

		if (size == 0) {
			return 0;
		}

		// Initialization of related variables
		Tower last = towers.get(0), now = null;
		int[] lastRange = new int[2], nowRange = new int[2];
		System.arraycopy(last.getRange(), 0, lastRange, 0, 2);
		int coverage = 0;

		for (int i = 1; i < size; i++) {
			now = towers.get(i);
			System.arraycopy(now.getRange(), 0, nowRange, 0, 2);
			
			// Notice: towers[i + 1] >= towers[i]
			if (nowRange[0] > lastRange[1]) {
				coverage += (lastRange[1] - lastRange[0]);
				System.arraycopy(nowRange, 0, lastRange, 0, 2);
			} else if (nowRange[1] > lastRange[1]) {
				// If this tower overlaps the last one, extends the range of
				// last one instead of creating a new range. This is to prevent
				// double-counting.
				lastRange[1] = nowRange[1];
			}
			
			last = now;
		}
		coverage += (lastRange[1] - lastRange[0]);

		return coverage;
	}

	// For debugging purpose
	public void print() {
		int cover = getCoverage();
		
		System.out.printf("Towers converage on the road of %dkm:\n", length);
		
		for (Tower i : towers) {
			int[] range = i.getRange();
			System.out.printf("Tower - Location: %2d Range: [%2d, %2d]\n",
					i.getLocation(), range[0], range[1]);
		}

		System.out.println("Total coverage: " + cover + "km.");
	}
}
