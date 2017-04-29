package lectures;

public class IntBagTest {
	public static void main(String[] args) {
		IntBag myBag = new IntBag();
		myBag.add(12);
		myBag.add(34);
		myBag.add(56);
		myBag.add(78);
		myBag.add(90);

		System.out.println(myBag.size());

		for (int x : myBag.iterate()) {
			System.out.println(x);
		}
	}
}
