package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * HerbertLogBonusTest
 *
 * Description: A set of tests to ensure that the correct salary is calculated
 * and the minimum number of minutes for selected goalIncomes are correct
 * 
 * Notice: The method used here is inefficient. You may need to wait up to 10 -
 * 15 seconds for the program to finish execution, depending on situations of
 * your hardware devices.
 *
 * @author Goh Chun Teck
 */
public class HerbertLogBonusFastTest {

	@Test
	/**
	 * Verify the correct salary calculated in veryShortNames
	 */
	public void testVeryShortNamesTotalSalary() {
		HerbertLogFast log = new HerbertLogFast("src/data/veryShortNamesHerbert.txt");

		long salary = log.calculateSalaryFast();
		assertEquals("veryShortNamesTotalSalary", 52, salary);
	}

	@Test
	/**
	 * Verify the correct salary calculated in shortNames
	 */
	public void testShortNamesTotalSalary() {
		HerbertLogFast log = new HerbertLogFast("src/data/shortNamesHerbert.txt");

		long salary = log.calculateSalaryFast();
		assertEquals("shortNamesTotalSalary", 401, salary);
	}

	@Test
	/**
	 * Verify the correct salary calculated in manyNames
	 */
	public void testManyNamesTotalSalary() {
		HerbertLogFast log = new HerbertLogFast("src/data/manyNamesHerbert.txt");

		long salary = log.calculateSalaryFast();
		assertEquals("manyNamesTotalSalary", 47803, salary);
	}

	@Test
	/**
	 * Verify the correct salary calculated in longNames
	 */
	public void testLongNamesTotalSalary() {
		HerbertLogFast log = new HerbertLogFast("src/data/longNamesHerbert.txt");

		long salary = log.calculateSalaryFast();
		assertEquals("longNamesTotalSalary", 2796254, salary);
	}

	@Test
	public void testVeryShortNamesGoalIncome() {
		HerbertLogFast log = new HerbertLogFast("src/data/veryShortNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkFast(52);
		assertEquals("veryShortNamesGoalIncome - $52", 2, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(30);
		assertEquals("veryShortNamesGoalIncome - $30", 1, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(53);
		assertEquals("veryShortNamesGoalIncome - $53", -1, numMinutes);
	}

	@Test
	public void testShortNamesGoalIncome() {
		HerbertLogFast log = new HerbertLogFast("src/data/shortNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkFast(401);
		assertEquals("shortNamesGoalIncome - $401", 5, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(279);
		assertEquals("shortNamesGoalIncome - $279", 2, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(280);
		assertEquals("shortNamesGoalIncome - $280", 3, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(402);
		assertEquals("shortNamesGoalIncome - $402", -1, numMinutes);
	}

	@Test
	public void testManyNamesGoalIncome() {
		HerbertLogFast log = new HerbertLogFast("src/data/manyNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkFast(47803);
		assertEquals("manyNamesGoalIncome - $47803", 20, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(13320);
		assertEquals("manyNamesGoalIncome - $13320", 3, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(25567);
		assertEquals("manyNamesGoalIncome - $25567", 6, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(47804);
		assertEquals("manyNamesGoalIncome - $47804", -1, numMinutes);
	}

	@Test
	public void testLongNamesGoalIncome() {
		HerbertLogFast log = new HerbertLogFast("src/data/longNamesHerbert.txt");

		long numMinutes = log.calculateMinimumWorkFast(2796254);
		assertEquals("longNamesGoalIncome - $2796254", 42399, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(1);
		assertEquals("longNamesGoalIncome - $1", 1, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(1937821);
		assertEquals("longNamesGoalIncome - $1937821", 17698, numMinutes);

		numMinutes = log.calculateMinimumWorkFast(2796255);
		assertEquals("longNamesGoalIncome - $2796255", -1, numMinutes);
	}
}
