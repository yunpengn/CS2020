package cs2020;

public class FastStudent extends Student implements Comparable<FastStudent>{
	public FastStudent(String name, int strength, int speed) {
		super(name, strength, speed);
	}

	@Override
	public int compareTo(FastStudent st) {
		if (this.getSpeed() > st.getSpeed()) {
			return 1;
		} else if (this.getSpeed() < st.getSpeed()) {
			return -1;
		} else {
			return 0;
		}
	}
}
