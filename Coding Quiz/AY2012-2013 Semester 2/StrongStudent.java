package cs2020;

public class StrongStudent extends Student implements Comparable<StrongStudent>{
	public StrongStudent(String name, int strength, int speed) {
		super(name, strength, speed);
	}

	@Override
	public int compareTo(StrongStudent st) {
		if (this.getStrength() > st.getStrength()) {
			return 1;
		} else if (this.getStrength() < st.getStrength()) {
			return -1;
		} else {
			if (this.getSpeed() > st.getSpeed()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
