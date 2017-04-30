package sg.edu.nus.cs2020;

import java.util.Comparator;

public class TowerComparator implements Comparator<Tower> {
	@Override
	public int compare(Tower o1, Tower o2) {
		return o1.compareTo(o2);
	}
}
