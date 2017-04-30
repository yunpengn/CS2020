package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

public class SadNumberTest {
	SadNumber tester = new SadNumber();

	@Test
	public void nextValueTest() {
		int n = 15;
		int[] expected = {15, 26, 40, 16, 37, 58};
		int[] result = new int[6];

		for (int i = 0; i < 6; i++) {
			result[i] = n;
			n = tester.nextValue(n);
		}

		assertEquals("nextValue Test", true, Arrays.equals(result, expected));
	}

	@Test
	public void cycleExistTest() {
		boolean first = tester.cycleExist(15, 100);
		boolean second = tester.cycleExist(15, 2);

		assertEquals("cycleExist Test - 1", true, first);
		assertEquals("cycleExist Test - 2", false, second);
	}

	@Test
	public void smallestValueTest() {
		int first = tester.smallestValueInCycle(15, 100);
		int second = tester.smallestValueInCycle(15, 2);

		assertEquals("smallestValue Test - 1", 4, first);
		assertEquals("smallestValue Test - 2", -1, second);
	}

	@Test
	public void printSadCycleTest() {
		// tester.printSadCycle(20, 100);
	}

	@Test
	public void iteratorTest() {
		Iterator<Integer> iter = tester.iterator(10);

		for (int i = 0; i < 100; i++) {
			if (iter.hasNext()) {
				System.out.println(iter.next());
			} else {
				break;
			}
		}
	}
}
