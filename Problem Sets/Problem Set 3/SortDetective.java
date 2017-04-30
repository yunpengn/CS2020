package sg.edu.nus.cs2020;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SortDetective {
	/************************************************
	 * Sorters, helpers, constants and initialization
	 ***********************************************/
	// Date format for debugging purpose
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = dateFormat.format(new Date());

	// Timer for measuring speed
	StopWatch timer = new StopWatch();

	// Constants for testing purpose.
	final int BOUND = 100;
	final int TIMES = 10;

	// Instantiate sorters for use.
	ISort sortA = new SorterA();
	ISort sortB = new SorterB();
	ISort sortC = new SorterC();
	ISort sortD = new SorterD();
	ISort sortE = new SorterE();
	ISort sortF = new SorterF();

	// Generates an array of integers with specified length.
	private Integer[] RandomDataGenerator(int size, int bound) {
		Random generator = new Random();
		Integer[] data = new Integer[size];

		for (int i = 0; i < size; i++) {
			data[i] = generator.nextInt(bound);
		}

		return data;
	}


	/***********************************************
	 * This part checks whether the result is sorted
	 ***********************************************/
	private boolean checkSorted(ISort sorter, int size) {
		Integer[] data = RandomDataGenerator(size, BOUND);
		sorter.sort(data);
		
		for (int times = 0; times < TIMES; times++) {
			for (int i = 0; i < size - 1; i++) {
				if (data[i] > data[i + 1]) {
					return false;
				}
			}
		}
		
		return true;
	}

	public void checkSortedMultiplySizes() {
		int[] sizes = {10, 100, 1000, 10000, 100000};
		System.out.println("==========================================================");
		System.out.println("              CheckSorted - Multiply sizes");
		System.out.println("                    Running Time: " + date);
		System.out.println(
				"Size    SortA    SortB    SortC   SortD   SortE   SortF");
		for (int i : sizes) {
			System.out.printf("%6d", i);
			System.out.printf(" %7b", checkSorted(sortA, i));
			System.out.printf(" %7b", checkSorted(sortB, i));
			System.out.printf(" %7b", checkSorted(sortC, i));
			System.out.printf(" %7b", checkSorted(sortD, i));
			System.out.printf(" %7b", checkSorted(sortE, i));
			System.out.printf(" %7b", checkSorted(sortF, i));
			System.out.println();
		}
		System.out.println("==========================================================");
	}


	/***********************************************
	 * This part measures the speed of sorting
	 ***********************************************/
	private double sortingTimer(ISort sorter, int size) {
		Integer[] data = RandomDataGenerator(size, BOUND);
		timer.reset();
		timer.start();
		sorter.sort(data);
		timer.stop();
		
		return timer.getTime();
	}

	public void measureTimeMultiplySizes() {
		int[] sizes = {10, 100, 1000, 10000, 100000};
		System.out.println("==========================================================");
		System.out.println("              Measure Time - Multiply sizes");
		System.out.println("                    Running Time: " + date);
		System.out.println(
				" Size    SortA      SortB      SortC     SortD     SortE     SortF");
		for (int i : sizes) {
			System.out.printf("%6d", i);
			System.out.printf(" %9.6f", sortingTimer(sortA, i));
			System.out.printf(" %9.6f", sortingTimer(sortB, i));
			System.out.printf(" %9.6f", sortingTimer(sortC, i));
			System.out.printf(" %9.6f", sortingTimer(sortD, i));
			System.out.printf(" %9.6f", sortingTimer(sortE, i));
			System.out.printf(" %9.6f", sortingTimer(sortF, i));
			System.out.println();
		}
		System.out.println("==========================================================");
	}


	/***********************************************
	 * This part tests whether the sorting is stable
	 ***********************************************/
	private boolean isStable(ISort sorter, int size) {
		Integer[] data = RandomDataGenerator(size, BOUND);
		Probe[] myData = new Probe[size];

		for (int i = 0; i < size; i++) {
			myData[i] = new Probe(data[i], i);
		}

		sorter.sort(myData);

		for (int i = 0; i < size - 1; i++) {
			if (myData[i].getValue() == myData[i + 1].getValue()
					&& myData[i].getIndex() > myData[i + 1].getIndex()) {
				return false;
			}
		}

		return true;
	}

	public void isStableMultiplySizes() {
		int[] sizes = {10, 100, 1000, 10000, 100000};
		System.out.println(
				"==========================================================");
		System.out.println("              Measure Time - Multiply sizes");
		System.out.println("                    Running Time: " + date);
		System.out.println(
				" Size   SortA     SortB   SortC   SortD   SortE   SortF");
		for (int i : sizes) {
			System.out.printf("%6d", i);
			System.out.printf(" %7b", isStable(sortA, i));
			System.out.printf(" %7b", isStable(sortB, i));
			System.out.printf(" %7b", isStable(sortC, i));
			System.out.printf(" %7b", isStable(sortD, i));
			System.out.printf(" %7b", isStable(sortE, i));
			System.out.printf(" %7b", isStable(sortF, i));
			System.out.println();
		}
		System.out.println("==========================================================");
	}


	/***********************************************
	 * Tests the capability for almost sorted ones
	 ***********************************************/
	private double testAlmostSorted(ISort sorter, int size) {
		Integer[] data = new Integer[size];

		for (int i = 0; i < size - 1; i++) {
			data[i] = i + 1;
		}
		data[size - 1] = 1;

		timer.reset();
		timer.start();
		sorter.sort(data);
		timer.stop();

		return timer.getTime();
	}

	public void testAlmostSortedMultipleSizes() {
		int[] sizes = {10, 100, 1000, 10000, 100000};
		System.out.println("==========================================================");
		System.out.println("              Measure Time - Multiply sizes");
		System.out.println("                    Running Time: " + date);
		System.out.println(
				" Size    SortA      SortB      SortC     SortD     SortE     SortF");
		for (int i : sizes) {
			System.out.printf("%6d", i);
			System.out.printf(" %9.6f", testAlmostSorted(sortA, i));
			System.out.printf(" %9.6f", testAlmostSorted(sortB, i));
			System.out.printf(" %9.6f", testAlmostSorted(sortC, i));
			System.out.printf(" %9.6f", testAlmostSorted(sortD, i));
			System.out.printf(" %9.6f", testAlmostSorted(sortE, i));
			System.out.printf(" %9.6f", testAlmostSorted(sortF, i));
			System.out.println();
		}
		System.out.println("==========================================================");
	}


	/***********************************************
	 * Main method for sorting detective
	 ***********************************************/
	public static void main(String[] args) {
		SortDetective detector = new SortDetective();

		// Remainder: For the below 4 lines, you may opt to run only 1 line each
		// time and comment the else. They can be quite slow and each of them
		// may take up to 2 minutes depending on your hardware condition.
		// Special Notice: For checkSortedMultiplySizes, it seems that Dr.Evil
		// is using some randomized algorithm. Thus, you may not catch him all
		// the times. Please be patient and try a few more times.
		detector.checkSortedMultiplySizes();
		detector.measureTimeMultiplySizes();
		detector.isStableMultiplySizes();
		detector.testAlmostSortedMultipleSizes();
	}
}