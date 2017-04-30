package coding_quiz;

import org.junit.Test;

public class BasketballSimTest {
	BasketballSim tester = new BasketballSim();

	@Test
	public void simulateTest() {
		for (int i = 1; i < 10; i++) {
			System.out.printf("%f ", tester.simulate(i));
		}
		System.out.println();
	}
	
	@Test
	public void averageTest() {
		final int repeat = 10000;

		for (int i = 1; i < 10; i++) {
			System.out.printf("%f ", tester.average(i, repeat));
		}
		System.out.println();
	}
}
