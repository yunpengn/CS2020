package sg.edu.nus.cs2020;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;

public class RealTest {

	@Test
	public void sampleRealTest() {
		ArrayList<Activity> activities = new ArrayList<Activity>(Arrays.asList(
			new Activity("Software Engineering Project", 0, 8),
			new Activity("Watch Movie", 4, 10),
			new Activity("Meditate", 9, 15),
			new Activity("Dinner", 13, 15),
			new Activity("Sleep", 15, 22)
		));
		Real r = new Real();
		assertEquals(r.findMaximumActivities(activities), 3);
	}

}
