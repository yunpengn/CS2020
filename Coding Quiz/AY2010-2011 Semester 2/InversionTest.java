package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

public class InversionTest {
	private Random generator = new Random();

	@Test
	public void inversionTest1() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(10);
		arr.add(5);
		arr.add(3);

		int expected = 3;
		int result1 = Inversions.countInversions(arr);
		int result2 = Inversions.countInversionsFast(arr);

		assertEquals("Simple Inversion - Test 1-1", expected, result1);
		assertEquals("Simple Inversion - Test 1-2", expected, result2);
	}

	@Test
	public void inversionTest2() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		final int SIZE = 1000;
		final int BOUND = 40;
		
		for (int i = 0; i < SIZE; i++) {
			arr.add(generator.nextInt(BOUND));
		}
		
		int result1 = Inversions.countInversions(arr);
		int result2 = Inversions.countInversionsFast(arr);

		assertEquals("Simple Inversion - Test 2", result1, result2);
	}

	@Test
	public void createInversionTest() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		final int SIZE = 10;
		final int BOUND = 40;

		for (int i = 0; i < SIZE; i++) {
			arr.add(generator.nextInt(BOUND));
		}
		Collections.sort(arr);

		int numInversions = 5;
		arr = Inversions.createInversions(arr, numInversions);
		int result = Inversions.countInversions(arr);

		assertEquals("Create Inversion - Test", numInversions, result);
	}
}
