package recitation;

public class CountQueue extends Queue {
	private int count = 0;

	@Override
	public void add(int x) {
		System.out.println("Call add in QueueCount.");

		count++;
		super.add(x);
	}

	@Override
	public void addSet(int[] arr) {
		System.out.println("Call addSet in QueueCount.");

		count += arr.length;
		super.addSet(arr);
	}

	public void printCount() {
		System.out.println("Count: " + count);
	}

	public static void main(String[] args) {
		CountQueue test = new CountQueue();
		int[] arr1 = {1, 2};
		int[] arr2 = {3, 4};

		test.addSet(arr1);
		test.addSet(arr2);
		test.addSet(arr1);
		test.addSet(arr2);
		test.printCount();
	}
}
