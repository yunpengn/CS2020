package sg.edu.nus.cs2020;

/**
 * @author ary
 */
public class Activity implements Comparable<Activity> {
	
	// Activity Attributes
	private String name;
	private int startTime;
	private int endTime;
	
	// Constructor
	Activity(String name, int startTime, int endTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	// Gettter methods
	public int getStartTime() {
		return this.startTime;
	}
	
	public int getEndTime() {
		return this.endTime;
	}
	
	@Override
	public int compareTo(Activity other) {
		// Compare activities based on ascending order of ending time
		return this.endTime - other.endTime;
	}
}
