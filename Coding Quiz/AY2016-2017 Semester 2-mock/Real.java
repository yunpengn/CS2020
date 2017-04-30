package sg.edu.nus.cs2020;

import java.util.ArrayList;
import java.util.Collections;

public class Real implements IReal {
	// Greedy Algorithm for Activity Selection Problem
	// Note that Greedy algorithm do not always produce optimal solutions but 
	// GREEDY-ACTIVITY-SELECTOR does.
	public int findMaximumActivities(ArrayList<Activity> activities) {
		Collections.sort(activities);
		int numActivites = 0;
		int lastFinish = -1;
		
		for (Activity now : activities) {
			if (now.startTime >= lastFinish) {
				numActivites++;
			}
			lastFinish = now.endTime;
		}

		return numActivites;
	}
}
