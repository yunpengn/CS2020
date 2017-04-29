package lectures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PeakFindingTest {
	private boolean isPeak(int[] arr, int peak) {
		int size = arr.length;

		if (peak == 0) {
			return arr[1] < arr[0];
		} else if (peak == size - 1) {
			return arr[size - 2] < arr[size - 1];
		} else {
			return arr[peak + 1] < arr[peak] && arr[peak - 1] < arr[peak];
		}
	}

	private boolean isPeak2D(int[][] arr, int[] peak) {
		int row = peak[0];
		int col = peak[1];
		boolean checkRow = isPeak(arr[row], col);
		boolean checkCol = false;
		
		if (col == 0) {
			checkCol = arr[row][col] > arr[row][col + 1];
		} else if (col == arr.length - 1) {
			checkCol = arr[row][col] > arr[row][col - 1];
		} else {
			checkCol = arr[row][col] > arr[row][col + 1]
					&& arr[row][col] > arr[row][col - 1];
		}

		return checkRow && checkCol;
	}

	@Test
	public void testSimple1D() {
		PeakFinding finder = new PeakFinding();
		int[] data = {2, 4, 3, 2, 11, 6, 23, 4, 6, 8, 17, 5};
		
		assertEquals("1S Simple Test", true, isPeak(data, finder.peak1(data)));
		assertEquals("1S Simple Test", true, isPeak(data, finder.peak2Iterative(data)));
		assertEquals("1S Simple Test", true, isPeak(data, finder.peak2Recursive(data)));
	}
	
	@Test
	public void testDuplicate1D() {
		PeakFinding finder = new PeakFinding();
		int[] data = {2, 4, 3, 2, 11, 6, 17, 17, 10, 8, 6, 5};
		
		assertEquals("1S Simple Test", true, isPeak(data, finder.peak1(data)));
		assertEquals("1S Simple Test", -1, finder.peak2Iterative(data));
		assertEquals("1S Simple Test", -1, finder.peak2Recursive(data));
	}

	@Test
	public void testSimple2D() {
		PeakFinding finder = new PeakFinding();
		int[][] data = {{3, 4, 5, 2},
						{2, 1, 2, 5},
						{1, 9, 1, 2},
						{7, 5, 3, 5}};

		assertEquals("1S Simple Test", true, isPeak2D(data, finder.peakSquare1(data)));
		assertEquals("1S Simple Test", true, isPeak2D(data, finder.peakSquare2(data)));
		assertEquals("1S Simple Test", true, isPeak2D(data, finder.peakSquare3(data)));
	}

	@Test
	public void testAdvanced2D() {
		PeakFinding finder = new PeakFinding();
		int[][] data = {
				{48, 27, 32,  7, 37, 75, 98, 14, 33, 68, 90},
				{83, 12, 75, 81, 21, 28, 67, 26, 86, 95, 16},
				{85, 74, 53, 43, 68, 21,  5, 18, 92, 40, 70},
				{98, 35, 60, 35, 59, 26, 43, 35, 28, 46,  7},
				{51, 11, 75, 69, 50, 72, 69, 11, 52, 87, 80},
				{11, 20, 96, 56,  8, 67, 91, 95, 46, 61, 69},
				{41, 98, 43, 18, 62, 32,  8, 45, 58, 26, 38},
				{95, 59, 70, 88, 43, 33, 15, 93, 35, 62, 33},
				{84, 32, 81, 23, 19, 63, 84, 39, 79,  1, 75},
				{81, 82, 85, 99, 64, 72, 65, 26,  6, 72, 35},
				{55,  3, 23, 21, 50, 38,  7, 33, 93, 20, 30}};
		
		assertEquals("1S Simple Test", true, isPeak2D(data, finder.peakSquare1(data)));
		assertEquals("1S Simple Test", true, isPeak2D(data, finder.peakSquare2(data)));
		assertEquals("1S Simple Test", true, isPeak2D(data, finder.peakSquare3(data)));
		assertEquals("1S Simple Test", true, isPeak2D(data, finder.peakSquare4(data)));
	}
}
