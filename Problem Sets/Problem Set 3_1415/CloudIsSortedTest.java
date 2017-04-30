package sg.edu.nus.cs2020;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CloudIsSortedTest {
	private CloudSort sorter = new CloudSort();
	private int serialNum = 1;

	public void testIsSorted(String inFile) {
		final int k = 459;

		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("            IsSorted Test - " + serialNum++);
		System.out.println("        Test Data - " + inFile.substring(9));
		System.out.println(
				"          Sorted or not: " + sorter.isSorted(inFile, k));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++\n");
	}

	public static void main(String[] args) {
		// Date format for debugging purpose
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());

		CloudIsSortedTest tester = new CloudIsSortedTest();
		String[] inFiles = tester.inFileNames();

		System.out.println("Report Time: " + date + "\n");
		for (int i = 0; i < 23; i++) {
			tester.testIsSorted(inFiles[i]);
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("   Congratulations! All tests finished.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	private String[] inFileNames() {
		String[] names = new String[23];
		
		names[0] = "src/data/1000000IntsAlmostSorted100.txt";
		names[1] = "src/data/1000000IntsAlmostSorted50.txt";
		names[2] = "src/data/1000000IntsAlmostSorted500.txt";
		names[3] = "src/data/1000000IntsSorted.txt";

		names[4] = "src/data/100000IntsAlmostSorted10.txt";
		names[5] = "src/data/100000IntsAlmostSorted50.txt";
		names[6] = "src/data/100000IntsSorted.txt";

		names[7] = "src/data/10000IntsAlmostSorted10.txt";
		names[8] = "src/data/10000IntsAlmostSorted100.txt";
		names[9] = "src/data/10000IntsAlmostSorted5.txt";
		names[10] = "src/data/10000IntsSorted.txt";

		names[11] = "src/data/1000IntsAlmostSorted1.txt";
		names[12] = "src/data/1000IntsAlmostSorted10.txt";
		names[13] = "src/data/1000IntsAlmostSorted5.txt";
		names[14] = "src/data/1000IntsSorted.txt";

		names[15] = "src/data/100IntsAlmostSorted10.txt";
		names[16] = "src/data/100IntsAlmostSorted100.txt";
		names[17] = "src/data/100IntsAlmostSorted50.txt";
		names[18] = "src/data/100IntsSorted.txt";

		names[19] = "src/data/10IntsAlmostSorted1.txt";
		names[20] = "src/data/10IntsAlmostSorted20.txt";
		names[21] = "src/data/10IntsAlmostSorted5.txt";
		names[22] = "src/data/10IntsSorted.txt";

		return names;
	}
}
