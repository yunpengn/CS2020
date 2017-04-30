package coding_quiz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyListTest {
	@Test
	public void simpleTest() {
		MyList list = new MyList();
		list.add(3);
		list.add(5);
		list.add(1);
		list.add(9);
		list.add(2);
		list.add(8);
		list.add(7);
		assertEquals("Simple Test 1", "7829153", list.toString());
		System.out.println(list.toString());

		list.add(4);
		assertEquals("Simple Test 2", "4782915", list.toString());
		System.out.println(list.toString());

		list.add(3);
		assertEquals("Simple Test 3", "3478291", list.toString());
		System.out.println(list.toString());

		list.remove();
		assertEquals("Simple Test 3", "478291", list.toString());
		System.out.println(list.toString());

		assertEquals("Simple Test 3", 10, list.calculate(7));
		System.out.println("Calculate around 7: " + list.calculate(7));
	}
}
