package sg.edu.nus.cs2020;

/**
 * Public Class: SampleCloudClient
 * 
 * Description: This is an example of how to use the Cloud Manager library.
 * 
 * @author DCSSLG
 */
public class SampleCloudClient {
	/**
	 * Public Static Method: void main(String[])
	 */
	public static void main(String[] args){
		// Start up a cloud server using Amazon EC2. Each server should handle
		// 1000 records. Notice: Interface fields are static implicitly.
		CloudManager manager = new CloudManager(
				ICloudManager.CloudProvider.AmazonEC2, 5);
		
		// Start the cloud, load the resource "datafile.txt". Use 10 servers.
		manager.initiliazeCloud("src/data/10Ints.txt", 10);
		
		// Get the current status
		manager.getStatus();
		System.out.println();
		
		// You can now use scheduleServer and executePhase to perform sorting.
		// Notice: This implementation is wrong temporarily. Data is spoiled.
		manager.scheduleSort(0, 0, 1);
		manager.executePhase();

		// Get the current status
		manager.getStatus();
		System.out.println();
		
		if (manager.isSorted()) {
			System.out.println("The data have been sorted.\n");
		} else {
			System.out.println("Error. The data have not been sorted.\n");
		}

		// Shut down the cloud server. Write the output to "data-out.txt".
		manager.shutDown("src/result/10Ints.txt");
	}
}
