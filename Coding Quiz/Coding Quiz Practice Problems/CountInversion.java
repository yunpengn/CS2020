package coding_quiz;

import java.util.ArrayList;

public class CountInversion<T extends Comparable<T>> {
	public int count(ArrayList<T> arr) {
		return count(arr, 0, arr.size() - 1);
	}

	private int count(ArrayList<T> arr, int start, int end) {
		if (start == end) {
			return 0;
		} else {
			int mid = (start + end) / 2;
			
			return count(arr, start, mid) + count(arr, mid + 1, end) + merge(arr, start, mid, end);
		}
	}

	private int merge(ArrayList<T> arr, int start, int mid, int end) {
		int indexA = start;
		int indexB = mid + 1;
		int count = 0;
		int size = end - start + 1;
		ArrayList<T> temp = new ArrayList<T>();

		for (int i = 0; i < size; i++) {
			if (indexA == mid + 1) {
				temp.add(arr.get(indexB++));
			} else if (indexB == end + 1) {
				temp.add(arr.get(indexA++));
			} else if (arr.get(indexA).compareTo(arr.get(indexB)) > 0) {
				count += (mid - indexA + 1);
				temp.add(arr.get(indexB++));
			} else {
				temp.add(arr.get(indexA++));
			}
		}

		for (int i = 0; i < size; i++) {
			arr.set(i + start, temp.get(i));
		}

		return count;
	}
}
