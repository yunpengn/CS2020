package lectures;

public class IntListTest {
	public static void main(String[] args) {
		IntList myList = new IntList();

		myList.append(1);
		myList.prepend(2);
		myList.append(3);
		myList.prepend(4);
		myList.append(5);
		myList.prepend(6);
		myList.print();

		myList.remove(3);
		myList.print();

		myList.append(4);
		myList.prepend(4);
		myList.append(4);
		myList.print();
		myList.removeAll(4);
		myList.print();
	}
}
