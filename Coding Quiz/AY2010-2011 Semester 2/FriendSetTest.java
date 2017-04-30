package coding_quiz;

public class FriendSetTest {
	public static void main(String[] args) {
		ISet setA = new FriendSet();
		boolean added = false;

		try {
			added = setA.add("Joe"); // Returns true
			added = setA.add("Mary"); // Returns true
			added = setA.add("Sue"); // Returns true
			added = setA.add("Joe"); // Returns false
		} catch (Exception e) {
			;
		}

		int setSize1 = setA.size(); // Returns 3
		String[] setList = setA.enumerateSet();
		// Returns an array containing: [Joe, Mary, Sue]
		System.out.println("Set List " + setList.length);

		ISet setB = new FriendSet(setA); // Creates a copy of setA

		try {
			added = setB.add("Joe"); // Returns false
			added = setB.add("Leo"); // Returns true
		} catch (Exception e) {
			;
		}
		int setSize2 = setB.size();

		ISet setC = setA.intersection(setB);
		// Returns setC containing [Joe, Mary, Sue]
		int setSize3 = setC.size(); // returns 3

		ISet setD = new FriendSet();
		String John = "John";

		try {
			for (int i = 1; i <= 100; i++) {
				setD.add(John + Integer.toString(i, 10)); // Returns true
			}
		} catch (Exception e) {
			;
		}

		int setSize4 = setD.size(); // returns 100

		try {
			added = setD.add("John101"); // Throws an exception!
		} catch (Exception e) {
			System.err.println("There is an exception");
		}

		System.out.printf("%d %d %d %d\n", setSize1, setSize2, setSize3, setSize4);
		System.out.println(added);
	}
}
