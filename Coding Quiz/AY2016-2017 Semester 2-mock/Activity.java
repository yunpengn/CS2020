package sg.edu.nus.cs2020;

public class Activity implements Comparable<Activity> {
	public int startTime;
	public int endTime;
	public String name;
	
	/**
	 * constructor for the activity class
	 * @param name - the name of the activity
	 * @param startTime - time for starting
	 * @param endTime - time for ending
	 */
	public Activity(String name, int startTime, int endTime) {
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Activity other) {
		return this.endTime - other.endTime;
	}
}
