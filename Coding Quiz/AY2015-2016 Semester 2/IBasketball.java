/**
 * CS2020 Coding Quiz 2016:
 * Michael Jordan Plays Basketball
 * @author sgilbert
 *
 */
package coding_quiz;

/*
 * Interface for simulating a basketball player.
 */
public interface IBasketball {

	// Simulate n shots, assuming s1=success and s2=failure.
	// If sn=success, then return the success probability.  Otherwise, return -1.
	public double simulate(int n);
	
	// Repeat the process of simulating n shots R times, and return the average success probability
	// for the case where sn=success.
	public double average(int n, int R);
}
