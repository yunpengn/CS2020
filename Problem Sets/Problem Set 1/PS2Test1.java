package sg.edu.nus.cs2020;

public class PS2Test1 {
	public static void main(String[] args) {
		int[] array = new int[9];
		array[0] = 0;
		array[1] = 1;
		array[2] = 0;
		array[3] = 1;
		array[4] = 1;
		array[5] = 1;
		array[6] = 1;
		array[7] = 0;
		array[8] = 1;

		ShiftRegister shifter = new ShiftRegister(9, 7);
		shifter.setSeed(array);

		for (int i = 0; i < 10; i++) {
			int j = shifter.shift();
			System.out.print(j);
		}
	}
}
