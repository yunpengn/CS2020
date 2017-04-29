package recitation;

public class Queue {
	public void add(int x) {
		System.out.println("Call add in Queue.");
	}

	public void addSet(int[] arr) {
		System.out.println("Call addSet in Queue.");
		for (int i : arr) {
			this.add(i);
		}
	}
}
