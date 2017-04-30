package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.Collections;

public class Real implements IReal {
	
	/**
	 * Description: Computes the maximum number activities Mr. A can participate in
	 * 				Uses a greedy algorithm where we repeatedly take the next activity
	 * 				that ends the earliest provided it doesn't clash with any activities
	 * 				beforehand (i.e. participated in / participating). This maximizes the
	 * 				number of activities we can participate in the future.
	 * @param activities - A list of activities with name, startTime and endTime
	 * @return the maximum number of non-overlapping activities Mr. A can participate in
	 */
	public int findMaximumActivities(ArrayList<Activity> activities) {
		// Sort activities based on their finishing time
		Collections.sort(activities);
		// Keep track of number of activities we will participate in
		int numActivities = 0;
		// Keep track of when our last activity ends
		int lastActivityFinishTime = Integer.MIN_VALUE;
		// Greedy Algorithm - Pick the next activity Mr. A can participate in
		// --> Guaranteed to end earliest (since it is sorted by finishing time)
		// 		--> Maximizes number of activities Mr. A can participate in
		for (int i = 0; i < activities.size(); i++) {
			Activity newActivity = activities.get(i);
			// Participate in this activity if my last activity has finished
			if (lastActivityFinishTime <= newActivity.getStartTime()) {
				// Increment number of activities
				numActivities++;
				// Update to track this activitiy's finishing time
				lastActivityFinishTime = newActivity.getEndTime();
			}
		}
		// Return number of activities as result
		return numActivities;
	}
}
