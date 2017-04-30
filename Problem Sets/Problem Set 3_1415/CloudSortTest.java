package sg.edu.nus.cs2020;

public class CloudSortTest {
	private CloudSort sorter = new CloudSort();
	private int serialNum = 1;

	public void testSorting(String inFile, String outFile) {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("              Cloud Sort - " + serialNum++);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		sorter.cloudSort(inFile, outFile, 10);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++\n\n");
	}

	public static void main(String[] args) {
		CloudSortTest tester = new CloudSortTest();
		String[] inFiles = tester.inFileNames();
		String[] outFiles = tester.outFileNames();

		for (int i = 0; i < 8; i++) {
			tester.testSorting(inFiles[i], outFiles[i]);
		}

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("   Congratulations! All sorting finished.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	private String[] inFileNames() {
		String[] names = new String[8];
		int size = 10;

		for (int i = 0;i<6;i++) {
			names[i] = "src/data/" + String.valueOf(size) + "Ints.txt";
			size *= 10;
		}
		
		names[6] = "src/data/103Ints.txt";
		names[7] = "src/data/PiDigits.txt";

		return names;
	}

	private String[] outFileNames() {
		String[] names = new String[8];
		int size = 10;

		for (int i = 0; i < 6; i++) {
			names[i] = "src/result/" + String.valueOf(size) + "Ints.txt";
			size *= 10;
		}

		names[6] = "src/result/103Ints.txt";
		names[7] = "src/result/PiDigits.txt";

		return names;
	}
}
