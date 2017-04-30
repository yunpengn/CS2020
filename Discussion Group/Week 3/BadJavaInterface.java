package discussion_group;

public interface BadJavaInterface {
	// Notice: Even not declared explicitly, this is a final variable
	public String NAME = "ZHANG_MING";

	public void badAndBad();

	/*
	 * All methods defined in an interface should be abstract, which means there
	 * should be no actual implementation for them.
	 * 
	 * public void reallyBad() {
	 * 
	 * }
	 */

}
