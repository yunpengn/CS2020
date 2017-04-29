package lectures;

public class SwapTest {
	public static void swap(int[] arr, int p, int q) {
		int temp = arr[p];
		arr[p] = arr[q];
		arr[q] = temp;
	}

	public static void main(String[] args) {
		int[] test = {1, 2, 3};

		for (int i : test) {
			System.out.printf("%d ", i);
		}
		System.out.println();

		swap(test, 0, 1);

		for (int i : test) {
			System.out.printf("%d ", i);
		}
		System.out.println();
	}
}
