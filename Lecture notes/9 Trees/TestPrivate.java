package lectures;

public class TestPrivate {
	private int value = 10;

	public void changeValueOfOthers(TestPrivate other, int newValue) {
		other.value = newValue;
	}

	public int getValue() {
		return value;
	}

	public static void main(String[] args) {
		TestPrivate test1 = new TestPrivate();
		TestPrivate test2 = new TestPrivate();

		test2.changeValueOfOthers(test1, 20);
		System.out.println("After the change, the value of " + test1.getClass().getName() + " is " + test1.getValue());
	}

	/*
	 * Moral of this testing program: The access modifier "private" allows
	 * access from the current class, not necessarily the current instance. In
	 * other words, private field of an object can be modified by another object
	 * instantiated by the same class.
	 */
}
