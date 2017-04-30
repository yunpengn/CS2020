package sg.edu.nus.cs2020;

/**
 * Public Class: DataAnalysisEnhancedTest
 * 
 * Description: This class is the test class for DataAnalysisEnhanced. It runs
 * the analyzer on all databases provided, records the time used, and checks
 * whether the result is correct.
 * 
 * @author Niu Yunpeng
 */
public class DataAnalysisEnhancedTest {
	public static void main(String[] args) {
		// The path and file names of respective databases.
		String folder = "src/data/";
		String[] names = { "smallExample.in", "example.in", "QR.in", 
				"2.in", "4.in", "5.in", "6.in", "7.in", "8.in" };
		long[] results = { 6, 2573, 0, 17765, 47485, 1667, 45871, 324, 1057527 };

		DataAnalysisEnhanced processor = null;
		StopWatch timer = new StopWatch();
		
		for (int i = 0; i < names.length; i++) {
			// Initialize the analysis tool for the current database.
			String name = folder + names[i];
			processor = new DataAnalysisEnhanced(name);

			timer.reset();
			timer.start();
			long result = processor.analyze();
			timer.stop();
			
			if (results[i] != result) {
				System.err.println("Incorrect result");
			} else {
				System.out.println("Result for Database " + names[i] + ": " + result);
				System.out.println("Time used: " + timer.getTime());
				System.out.println("Checked: The result is correct!\n");
			}
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("All analysis completed.");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}
