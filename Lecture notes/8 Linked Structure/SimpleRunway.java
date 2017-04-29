package lectures;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class SimpleRunway implements IRunway {
	private String airportName = "";
	private String runwayName = "";
	private Map<Time, Plane> landingLogs = null;

	public SimpleRunway(String airportName, String runwayName) {
		this.airportName = airportName;
		this.runwayName = runwayName;
		landingLogs = new HashMap<Time, Plane>();
	}

	public String getAirportName() {
		return airportName;
	}

	public String getRunwayName() {
		return runwayName;
	}

	public boolean requestLanding(Time time, Plane plane) {
		if (landingLogs.containsKey(time)) {
			return false;
		}
		
		landingLogs.put(time, plane);
		return true;
	}

}
