package sg.edu.nus.cs2020;

/*
 * 
 * @author gilbert
 * Class: StopWatch
 * Description: Provides a simple way of measuring running time
 *
 */
public class StopWatch {

	// Stores the time when the stopwatch was started
	long startTime = 0;
	
	// Stores the time when the stopwatch was stopped
	long endTime = 0;
	
	// Stores the cumulative total running time since the stopwatch was last reset
	long total = 0;
	
	// Is the stopwatch running or not?
	boolean running = false;
	
	/*
	 * Constructor: initializes a new stopwatch
	 */
	StopWatch(){
		reset();
	}
	
	/* 
	 * Method: reset()
	 * Description: resets the stopwatch, stopping it (if it is running) and setting the cumulative time to zero.
	 * 
	 */
	void reset(){
		startTime = 0;
		endTime = 0;
		total = 0;
		running = false;
	}
	
	/*
	 * Method: start()
	 * Description: starts the stopwatch running.
	 */
	void start(){
		startTime = System.nanoTime();
		running = true;
	}
	
	/*
	 * Method: stop()
	 * Description: stops the stopwatch, if it is running.  If it is not running, does nothing.
	 */
	void stop(){
		if (running){
			endTime = System.nanoTime();
			total += (endTime - startTime);
			running = false;
		}
	}
	
	/*
	 * Method: getTime()
	 * Description: returns the cumulative time the stopwatch has been running since it was last reset, in seconds.
	 */
	float getTime(){
		float r = total;
		r /= 1000000000;
		return r;
	}
}
