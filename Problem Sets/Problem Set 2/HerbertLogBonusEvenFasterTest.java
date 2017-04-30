package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HerbertLogBonusEvenFasterTest {
	@Test
	/**
	 * Verify the correct salary calculated in veryShortNames
	 */
	public void testVeryShortNamesTotalSalary() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/veryShortNamesHerbert.txt");

		long salary = log.calculateSalaryEvenFaster();
		assertEquals("veryShortNamesTotalSalary", 52, salary);
	}

	@Test
	/**
	 * Verify the correct salary calculated in shortNames
	 */
	public void testShortNamesTotalSalary() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/shortNamesHerbert.txt");

		long salary = log.calculateSalaryEvenFaster();
		assertEquals("shortNamesTotalSalary", 401, salary);
	}

	@Test
	/**
	 * Verify the correct salary calculated in manyNames
	 */
	public void testManyNamesTotalSalary() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/manyNamesHerbert.txt");

		long salary = log.calculateSalaryEvenFaster();
		assertEquals("manyNamesTotalSalary", 47803, salary);
	}

	@Test
	/**
	 * Verify the correct salary calculated in longNames
	 */
	public void testLongNamesTotalSalary() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/longNamesHerbert.txt");

		long salary = log.calculateSalaryEvenFaster();
		assertEquals("longNamesTotalSalary", 2796254, salary);
	}

	@Test
	public void testVeryShortNamesGoalIncome() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/veryShortNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkEvenFaster(52);
		assertEquals("veryShortNamesGoalIncome - $52", 2, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(30);
		assertEquals("veryShortNamesGoalIncome - $30", 1, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(53);
		assertEquals("veryShortNamesGoalIncome - $53", -1, numMinutes);

		System.out.println("veryShortNamesGoalIncome - " + log.numGets());
	}

	@Test
	public void testShortNamesGoalIncome() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/shortNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkEvenFaster(401);
		assertEquals("shortNamesGoalIncome - $401", 5, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(279);
		assertEquals("shortNamesGoalIncome - $279", 2, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(280);
		assertEquals("shortNamesGoalIncome - $280", 3, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(402);
		assertEquals("shortNamesGoalIncome - $402", -1, numMinutes);

		System.out.println("shortNamesGoalIncome - " + log.numGets());
	}

	@Test
	public void testManyNamesGoalIncome() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/manyNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkEvenFaster(47803);
		assertEquals("manyNamesGoalIncome - $47803", 20, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(13320);
		assertEquals("manyNamesGoalIncome - $13320", 3, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(25567);
		assertEquals("manyNamesGoalIncome - $25567", 6, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(47804);
		assertEquals("manyNamesGoalIncome - $47804", -1, numMinutes);

		System.out.println("manyNamesGoalIncome - " + log.numGets());
	}

	@Test
	public void testLongNamesGoalIncome() {
		HerbertLogEvenFaster log = new HerbertLogEvenFaster("src/data/longNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkEvenFaster(2796254);
		assertEquals("longNamesGoalIncome - $2796254", 42399, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(1);
		assertEquals("longNamesGoalIncome - $1", 1, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(1937821);
		assertEquals("longNamesGoalIncome - $1937821", 17698, numMinutes);

		numMinutes = log.calculateMinimumWorkEvenFaster(2796255);
		assertEquals("longNamesGoalIncome - $2796255", -1, numMinutes);

		System.out.println("longNamesGoalIncome - " + log.numGets());
	}
}
