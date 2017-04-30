package coding_quiz;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ShuffleTest {
	private Shuffle tester = new Shuffle();

	@Test
	public void isShuffledTest() {
		int[] arr1 = {1, 2, 3};
		int[] arr2 = {3, 1, 2};
		boolean result = tester.isShuffled(arr1, arr2);

		assertEquals("isShuffled Test", true, result);
	}

	@Test
	public void shuffleTest() {
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] origin = Arrays.copyOf(arr, arr.length);
		tester.shuffle(arr);
		boolean isShuffled = tester.isShuffled(arr, origin);

		assertEquals("Shuffle Test", true, isShuffled);
	}
}
