package sg.edu.nus.cs2020;

import java.util.Random;

/**
 * Class: CloudSort
 * 
 * Description: This class implements a distributed parallel sorting algorithm.
 * It uses the idea of odd-even sorting network, based on the inspiration of
 * bubble sort. However, different servers will "bubble" at the different part
 * of the "array" to avoid parallel crash.
 * 
 * @author Niu Yunpeng
 */
public class CloudSort {
	/**
	 * Public Method: void cloudSort(int, int, int)
	 * 
	 * Description: This method uses odd-even sorting network to implement a
	 * dynamic distributed parallel sorting algorithm. It will change the way of
	 * sorting when the server resource provided changes.
	 * 
	 * @param inFile
	 *            is the path to the file where unsorted data is stored.
	 * @param outFile
	 *            is the path to the file where sorted data will be sorted.
	 * @param numServers
	 *            is the number of servers that can do parallel computing.
	 */
	public void cloudSort(String inFile, String outFile, int numServers) {
		// Installation of cloud servers
		CloudManager manager = new CloudManager(
				ICloudManager.CloudProvider.AmazonEC2, 1000);
		if (!manager.initiliazeCloud(inFile, numServers)) {
			System.err.println("Error: Fails to initialize the servers.");
		}
		int pages = manager.numPages();
		
		if (pages == 1) {
			int numElements = manager.numElements();

			// The first server has no job. Shut down.
			System.out.println("    >>>Data size too small!");
			System.out.println("    >>>Change to another server.\n");
			manager.shutDown(outFile);

			// There are so few elements that they even cannot fill a whole
			// page. Therefore, we need to decrease the size of the page.
			smallSort(inFile, outFile, numServers, numElements);
		} else {
			// Sorting algorithm: Odd-even sorting network
			while (!manager.isSorted()) {
				int unscheduled = numServers;

				// Odd sort mechanism: 1) If pages are even, sort in pair of
				// [0,1], [2,3], [4,5], ..., [n-2,n-1]; 2) If pages are odd,
				// sort in pair of [0,1], [2,3], ..., [n-3,n-2].
				for (int i = 0; i < pages - 1; i += 2) {
					manager.scheduleSort(--unscheduled, i, i + 1);

					if (unscheduled == 0) {
						manager.executePhase();
						unscheduled = numServers;
					}
				}
				manager.executePhase();
				unscheduled = numServers;

				// Check whether the sorting has already finished.
				if (manager.isSorted()) {
					break;
				}

				// Even sort mechanism: 1) If pages are even, sort in pair of
				// [0,n-1], [1,2], [3,4]...; 2) If pages are odd, sort in pair
				// of [1,2], [3,4], ..., [n-2,n-1].
				if (pages % 2 == 0) {
					// Special consideration for even number of pages.
					manager.scheduleSort(--unscheduled, 0, pages - 1);

					if (unscheduled == 0) {
						manager.executePhase();
						unscheduled = numServers;
					}
				}

				for (int i = 1; i < pages - 1; i += 2) {
					manager.scheduleSort(--unscheduled, i, i + 1);

					if (unscheduled == 0) {
						manager.executePhase();
						unscheduled = numServers;
					}
				}
				manager.executePhase();
			}

			// Work done. Prints the status and saves the data.
			manager.getStatus();
			System.out.println("The data have been sorted.\n");
			manager.shutDown(outFile);
		}
	}
	
	/**
	 * Private Method: void smallSort(String, String, int, int);
	 * 
	 * Description: Because the server cannot sort a single page, we decrease
	 * the size of the pages to present the data into two pages.
	 */
	private void smallSort(String inFile, String outFile, int numServers, int size) {
		// Installation of cloud servers
		CloudManager manager = new CloudManager(
				ICloudManager.CloudProvider.AmazonEC2, (size + 1) / 2);
		if (!manager.initiliazeCloud(inFile, numServers)) {
			System.err.println("Error: Fails to initialize the servers.");
		}

		if (size <= 1) {
			// Do nothing where there is no element or only one element.
		} else {
			manager.scheduleSort(0, 0, 1);
			manager.executePhase();
		}

		// Work done. Prints the status and saves the data.
		manager.getStatus();
		System.out.println("The data have been sorted.\n");
		manager.shutDown(outFile);
	}

	/**
	 * Public Method: boolean isSorted(int[], int);
	 * 
	 * Description: This method is a random algorithm to test whether a given
	 * array is sorted for a certain percentage. The result may not always be
	 * correct. It has a certain confidence level determined by k.
	 * 
	 * @param arr
	 *            is the array for testing whether it is sorted.
	 * @param k
	 *            is the number of testings in the loop.
	 * 
	 * @return false 99% of the time when the array is <99% sorted.
	 */
	public boolean isSorted(String file, int k) {
		CloudManager manager = new CloudManager(
				ICloudManager.CloudProvider.AmazonEC2, 1000);
		if (!manager.initiliazeCloud(file, 10)) {
			System.err.println("Error: Fails to initialize the servers.");
		}
		
		// Used for generating a random index.
		int size = manager.numElements();
		Random generator = new Random();

		for (int m = 0; m < k; m++) {
			// Check whether the binary search result of arr[i] is i.
			int i = generator.nextInt(size);
			int j = binarySearch(manager, manager.getElement(i), 0, size - 1);

			if (i != j) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Private Method: int binarySearch(int[], int, int, int)
	 * 
	 * Description: This method implements a simple binary search to find the
	 * index of the first element whose value is equal to key in the given
	 * array.
	 * 
	 * @return the index of the first element whose value is equal to key.
	 */
	private int binarySearch(CloudManager manager, int key, int start, int end) {
		if (start >= end) {
			return manager.getElement(start) == key ? start : -1;
		} else {
			int mid = (start + end) / 2;

			if (manager.getElement(mid) < key) {
				return binarySearch(manager, key, mid + 1, end);
			} else {
				return binarySearch(manager, key, start, mid);
			}
		}
	}
}
