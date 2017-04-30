package coding_quiz;

import java.util.Arrays;
import java.util.Random;

public class Shuffle {
	public boolean isShuffled(int[] arr1, int[] arr2) throws IllegalArgumentException {
		if (arr1 == null || arr2 == null) {
			throw new IllegalArgumentException();
		} else if (arr1.length != arr2.length) {
			return false;
		} else {
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			return Arrays.equals(arr1, arr2);
		}
	}

	public void shuffle(int[] arr) {
		int size = arr.length;
		Random generator = new Random();

		for (int i = 1; i < size; i++) {
			int n = generator.nextInt(i);
			swap(arr, n, i);
		}
	}

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
