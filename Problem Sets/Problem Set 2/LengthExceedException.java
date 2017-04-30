package sg.edu.nus.cs2020;

public class LengthExceedException extends RuntimeException {

	// Default Serial Version ID
	private static final long serialVersionUID = 1L;

	public LengthExceedException() {
		System.err.println(toString() + ": The maximum length of this list exceeded.");
	}
}
