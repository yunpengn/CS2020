package lectures;

import java.sql.Time;

public interface IRunway {
	boolean requestLanding(Time time, Plane plane);
}
