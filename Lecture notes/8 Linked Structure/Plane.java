package lectures;

public class Plane {
	private String code = "";
	private String airlineCompany = "";
	private String destination = "";

	public Plane(String code, String airlineCompany, String destination) {
		this.code = code;
		this.airlineCompany = airlineCompany;
		this.destination = destination;
	}

	public String getCode() {
		return code;
	}

	public String getAirlineCompany() {
		return airlineCompany;
	}

	public String getDestination() {
		return destination;
	}
}
