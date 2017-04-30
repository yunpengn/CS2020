package tutorial;

public class Array {
	public static void main(String[] args) {
		int[][] test = new int[2][];
		test[0] = new int[3];
		test[1] = new int[2];

		for (int num = 100, i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++, num--) {
				test[i][j] = num;
			}
		}
		for (int[] is : test) {
			for (int i : is) {
				System.out.printf("%d ", i);
			}
			System.out.println();
		}
	}
}
