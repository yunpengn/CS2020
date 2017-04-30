package coding_quiz;

/**
 * ICA
 * @author gilbert
 * This is an interface for a 1-dimensional cellular automaton.
 * It is to be used for the Coding Quiz in CS2020, 2014.
 */
public interface ICA extends Iterable<String> {
	/**
	 * initialize
	 * @param rule
	 * This specifies the rule set of the cellular automaton.
	 * It also reinitializes the cellular automaton to all zeros,
	 * with a single 1 in the middle position.
	 */
	public void initialize(int[] rule);
	
	/**
	 * step This iterates the cellular automaton one time, applying the rule
	 * once. If the cellular automaton has not yet been initialized, then this
	 * should throw an exception.
	 * 
	 * @throws UninitializedException
	 */
	public void step() throws UninitializedException;
		
}
