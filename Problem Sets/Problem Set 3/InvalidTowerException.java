package sg.edu.nus.cs2020;

public class InvalidTowerException extends Exception {
	// Default serial version id
	private static final long serialVersionUID = 1L;

	public InvalidTowerException(String errorDescription) {
		System.err.print("Error: " + errorDescription);
	}
}
