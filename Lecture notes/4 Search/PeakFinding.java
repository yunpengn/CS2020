package lectures;

/**
 * Class: PeakFinding
 * 
 * Description: This class implements a few useful ways to find a peak (local
 * maximum) in an 1D or 2D array.
 * 
 * Public Methods:
 * 		int peak1(int[] arr);
 * 		int peak2Iterative(int[] arr);
 * 		int peak2Recursive(int[] arr);
 * 		int[] peakSquare1(int[][] arr);
 * 
 * Private Methods:
 * 		int peak2Helper(int[] arr, int start, int end);
 * 		boolean isPeak(int[] arr, int peak);
 * 		int findMax(int[] arr);
 * 
 * @author Niu Yunpeng
 * based on
 * @author Seth Gilbert
 * 
 * @category under GNU General Public License 3.0
 */
public class PeakFinding {
	// Find a local maximum and return its index in a given array.
	public int peak1(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (isPeak(arr, i)) {
				return i;
			}
		}

		return -1;
	}

	public int peak2Iterative(int[] arr) {
		int start = 0, end = arr.length - 1, mid = -1;

		while (start < end) {
			mid = (start + end) / 2;

			if (isPeak(arr, mid)) {
				return mid;
			} else if (arr[mid + 1] > arr[mid]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		return -1;
	}

	public int peak2Recursive(int[] arr) {
		return peak2Helper(arr, 0, arr.length - 1);
	}

	private int peak2Helper(int[] arr, int start, int end) {
		if (start >= end) {
			return -1;
		} else {
			int mid = (start + end) / 2;

			if (isPeak(arr, mid)) {
				return mid;
			} else if (arr[mid + 1] > arr[mid]) {
				return peak2Helper(arr, mid + 1, end);
			} else {
				return peak2Helper(arr, start, mid);
			}
		}
	}

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

	public int[] peakSquare1(int[][] arr) {
		int size = arr.length;
		int maxIndexs[] = new int[size];
		int maxValues[] = new int[size];
		int[] result = new int[2];

		for (int i = 0; i < size; i++) {
			maxIndexs[i] = findMax(arr[i]);
			maxValues[i] = arr[i][maxIndexs[i]];
		}
		
		result[0] = peak2Iterative(maxValues);
		result[1] = maxIndexs[result[0]];
		
		return result;
	}

	private int findMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		int index = -1;
		
		for(int i = 0;i<arr.length;i++) {
			if (arr[i] > max) {
				max = arr[i];
				index = i;
			}
		}
		
		return index;
	}

	public int[] peakSquare2(int[][] arr) {
		int[] result = new int[2];
		int size = arr.length;
		int start = 0, end = size - 1, mid = -1, midIndex = -1;
		int left, leftIndex, right, rightIndex, now;

		while (start < end) {
			mid = (start + end) / 2;
			midIndex = findMax(arr[mid]);
			leftIndex = findMax(arr[mid - 1]);
			rightIndex = findMax(arr[mid + 1]);
			now = arr[mid][midIndex];
			left = arr[mid - 1][leftIndex];
			right = arr[mid + 1][rightIndex];
			result[0] = mid;
			result[1] = midIndex;

			if (now > left && now > right) {
				break;
			} else if (right > now) {
				start = mid + 1;
			} else {
				end = start;
			}
		}

		return result;
	}

	public int[] peakSquare3(int[][] arr) {
		int[] result = new int[2];
		int size = arr.length;
		int start = 0, end = size - 1, mid = -1, maxIndex = -1;

		while (start < end) {
			mid = (start + end) / 2;
			maxIndex = findMax(arr[mid]);
			result[0] = mid;
			result[1] = maxIndex;
			
			if(isPeak2D(arr, result)) {
				break;
			} else if (arr[mid + 1][maxIndex] > arr[mid][maxIndex]) {
				start = mid + 1;
			} else {
				end = start;
			}
		}

		return result;
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

	public int[] peakSquare4(int[][] arr) {
		int[] range = {0, arr.length - 1, 0, arr.length - 1};

		return peakSquareHelper(arr, range);
	}

	public int[] peakSquareHelper(int[][] arr, int[] range) {
		int startRow = range[0], endRow = range[1];
		int startCol = range[2], endCol = range[3];
		int[] result = new int[2];

		if (startRow == endRow || startCol == endCol) {
			result[0] = startRow;
			result[1] = startCol;

			return result;
		} else {
			result = maxBorderAndCross(arr, range);
			
			if (isPeak2D(arr, result)) {
				return result;
			} else {
				return peakSquareHelper(arr, nextRange(arr, result, range));
			}
		}
	}
	
	private int[] maxBorderAndCross(int[][] arr, int[] range) {
		int startRow = range[0], endRow = range[1];
		int startCol = range[2], endCol = range[3];
		int[] result = new int[2];
		int size = endRow - startRow;
		int max = Integer.MIN_VALUE;
		int mid = size / 2;
		
		for (int i = 0; i < size; i++) {
			if (arr[startRow][i] > max) {
				max = arr[startRow][i];
				result[0] = startRow;
				result[1] = i;
			}

			if (arr[endRow][i] > max) {
				max = arr[endRow][i];
				result[0] = endRow;
				result[1] = i;
			}
			
			if (arr[mid][i] > max) {
				max = arr[mid][i];
				result[0] = mid;
				result[1] = i;
			}

			if (arr[i][startCol] > max) {
				max = arr[i][startCol];
				result[0] = i;
				result[1] = startCol;
			}

			if (arr[i][endCol] > max) {
				max = arr[i][endCol];
				result[0] = i;
				result[1] = endCol;
			}

			if (arr[i][mid] > max) {
				max = arr[i][mid];
				result[0] = i;
				result[1] = mid;
			}
		}
		
		return result;
	}

	private int[] nextRange(int[][] arr, int[] max, int[] range) {
		int startRow = range[0], endRow = range[1];
		int startCol = range[2], endCol = range[3];
		int maxRow = max[0], maxCol = max[1];
		int size = endRow - startRow;
		int mid = size / 2;
		int maxValue = arr[maxRow][maxCol];
		int[] result = new int[4];
		
		if (maxRow == mid) {
			if (arr[maxRow - 1][maxCol] > maxValue) {
				result[0] = startRow;
				result[1] = mid - 1;
			} else {
				result[0] = mid + 1;
				result[1] = endRow;
			}
		} else if (maxRow == 0) {
			result[0] = startRow;
			result[1] = mid - 1;
		} else {
			result[0] = mid + 1;
			result[1] = endRow;
		}

		if (maxRow == mid) {
			if (arr[maxRow][maxCol - 1] > maxValue) {
				result[2] = startCol;
				result[3] = mid - 1;
			} else {
				result[2] = mid + 1;
				result[3] = endCol;
			}
		} else if (maxCol == 0) {
			result[2] = startCol;
			result[3] = mid - 1;
		} else {
			result[2] = mid + 1;
			result[3] = endCol;
		}

		return result;
	}
}
