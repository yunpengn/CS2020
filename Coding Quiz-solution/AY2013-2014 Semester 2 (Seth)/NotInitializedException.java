package cs2020;

public class NotInitializedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotInitializedException() {
		super("CA is not initialized");
	}
}
