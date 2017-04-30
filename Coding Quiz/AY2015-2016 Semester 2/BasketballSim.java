package coding_quiz;

import java.util.Random;

public class BasketballSim implements IBasketball {
	// Generator for random number.
	private Random generator = new Random();

	/**
	 * Public Method: double simulate(int)
	 * 
	 * Description: This method simulates the process of n shots, given that s1
	 * = success and s2 = failure.
	 * 
	 * @return the overall probability in [0, 1] if sn = success; otherwise,
	 *         returns -1 if sn = failure.
	 */
	public double simulate(int n) {
		if (n == 1) {
			return 1;
		}

		// Since s1 = success and s2 = failure, the initial probability is 0.5
		double rate = 0.5;

		for (int i = 3; i < n; i++) {
			int now = nextShot(rate);
			rate = (rate * (i - 1) + now) / i;
		}

		// Now compute the value of sn
		if (nextShot(rate) == 1) {
			return rate;
		} else {
			return -1;
		}
	}

	// Decides whether the next shot is successful.
	private int nextShot(double rate) {
		double result = generator.nextDouble();

		return result < rate ? 1 : 0;
	}

	/**
	 * Public Method: double average(int, int)
	 * 
	 * Description: repeat the simulation R times and return the average of all
	 * the instances in which Michael Jordan makes shot sn.
	 */
	public double average(int n, int R) {
		double sum = 0;
		int times = 0;

		for (int i = 0; i < R; i++) {
			double result = simulate(n);

			// To avoid rounding
			if (n > -0.1) {
				sum += result;
				times++;
			}
		}

		return sum / times;
	}
}
