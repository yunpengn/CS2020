package sg.edu.nus.cs2020;

public interface ICloudManager {
	
	public enum CloudProvider {
		AmazonEC2, MicrosoftAzure, BaiduCloud
	}

	// Initializes a new sorting instance.  Any previous sorting work is lost.
	public boolean initiliazeCloud(String fileIn, int numServers);
	
	// Returns the number of pages contained in the file.
	public int numPages();
	
	// Returns the number of elements
	public int numElements();
	
	// Gets one specific element
	public int getElement(int j);
	
	// Schedules a sort
	public boolean scheduleSort(int serverID, int pageOne, int pageTwo);
	
	// Executes the schedule
	public boolean executePhase();
	
	// Prints the status of the Cloud Server
	public void getStatus();
		
	// Outputs the sorted file, completing the process
	public boolean shutDown(String outFile);
	
}
