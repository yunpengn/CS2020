package sg.edu.nus.cs2020;

public class HerbertLogFast extends HerbertLog {
	public HerbertLogFast(String fileName) {
		super(fileName);
	}

	/**
	 * Public Method: long calculateSalaryFast()
	 * 
	 * Description: Following the idea of binary search, we divide the interval
	 * by 2 recursively. In case that all the names in the interval are the
	 * same, we actually do not need to calculate the items in this interval
	 * anymore since they are paid by the same employer. Notice that all the
	 * names of the employers are unique, so we only need to check whether the
	 * first and last name of an interval is the same.
	 * 
	 * Property: In case that one employer just ends at the middle point, we
	 * need to include that record in both the first and second half since it
	 * may be ignored if all the names in the first half is the same.
	 * 
	 * Inspiration: When we look at longNamesHerbert.txt, we may notice that the
	 * employer name of the first 4969 lines are the same "Jean-Louis". So, what
	 * about ignoring the lines in between and directly reading the value on the
	 * Line 4969? If this can be implemented, the efficiency will improve a lot.
	 * 
	 * Disadvantage: If the data are too large, maybe the space complexity is
	 * too bad such that it may cause stack overflow.
	 * 
	 * @return the total sum of salaries in the file.
	 */
	public long calculateSalaryFast() {
		RecordFast last = getFast(m_numMinutes - 1);

		// In the most extreme case, the whole file has only 1 person name, so
		// binaryCalculate will return 0. Therefore, we need to calculate the
		// value on the last line separately.
		return binaryCalculate(getFast(0), last) + last.getWages();
	}

	/**
	 * Private Method: long binaryCalculate(RecordFast start, RecordFast end)
	 * 
	 * Description: Executes the operation of dividing by 2 and calculate
	 * different intervals according to the rules stated in calculateSalaryFast.
	 * 
	 * @param start
	 *            The start record of this interval
	 * 
	 * @param end
	 *            The last record of this interval
	 * @return 0 if the whole interval has only 1 person name, otherwise, divide
	 *         the interval by 2 again and returns sum of these two intervals.
	 */
	private long binaryCalculate(RecordFast start, RecordFast end) {
		if (start.getName().equals(end.getName())) {
			// All the names are the same, ignore them.
			return 0;
		} else if (end.getIndex() - start.getIndex() == 1) {
			// There are only two names and they are different. Thus, the first
			// name must be the last minute of that employer, return its wages.
			return start.getWages();
		} else {
			// Else, recursively divide by 2.
			RecordFast mid = getFast((start.getIndex() + end.getIndex()) / 2);
			return binaryCalculate(start, mid) + binaryCalculate(mid, end);
		}
	}

	/**
	 * Public Method: long calculateMinimumWorkFast(long goalIncome)
	 * 
	 * Inspiration: Using wishful thinking, we can divide this problem into two
	 * steps: 1) set a time limit; 2) check the income under this time limit.
	 * 
	 * For Part One, we should know the range is [0, m_numMinutes]. One way to
	 * decide the limit efficiently may be binary search (recursively divide
	 * m_numMinutes by 2). However, when we look through the data, we may find
	 * that the result we are looking for is relatively quite small (usually
	 * smaller than 10). Thus, another way is to start from 1 and recursively
	 * multiply by some ratio.
	 * 
	 * For Part Two, we need to avoid query line by line to improve efficiency.
	 * Notice that we can find the last minute of each employer easily by the
	 * method in calculateSalaryFast(), so we can also find the first minute of
	 * each employer (since it is just the next line of the last minute of last
	 * employer). If we know which line is this employer's first minute, we can
	 * know on which line Herbert reaches the working limit.
	 */
	public long calculateMinimumWorkFast(long goalIncome) {
		if (goalIncome <= 0) {
			return 0;
		} else if (goalIncome > calculateSalaryFast()) {
			return -1;
		} else {
			// Range for the result consists of upper bound and lower bound.
			long start = 0, end = 0, mid = 0;
			final long RATIO = 2;

			// We first narrow down the range
			for (long i = 1; i < m_numMinutes * RATIO; i *= RATIO) {
				if (salaryByLimit(i) >= goalIncome) {
					start = i / RATIO;
					end = i;
					break;
				}
			}

			// Finds the exact value in the range using binary search
			while (start < end) {
				mid = (start + end) / 2;

				if (salaryByLimit(mid) < goalIncome) {
					start = mid + 1;
				} else {
					end = mid;
				}
			}

			return start;
		}
	}

	/**
	 * Private Method: long salaryByLimit(long limit)
	 * 
	 * @param limit
	 *            specifies the maximum working time for each employer.
	 * 
	 * @return the total salary under this limit restriction.
	 */
	private long salaryByLimit(long limit) {
		if (limit <= 0) {
			return 0;
		} else {
			// In the most extreme scenario, there is only 1 employer. The left
			// operand returns 0, and right operand returns the wages paid by
			// that employer.
			return calculateSalaryByLimit(getFast(0), getFast(m_numMinutes - 1), limit)
					+ firstPersonSalaryByLimit(getFast(0), limit);
		}
	}

	// Performs the calculation for salaryByLimit(long limit)
	private long calculateSalaryByLimit(RecordFast start, RecordFast end, long limit) {
		if (start.getName().equals(end.getName())) {
			return 0;
		} else if (end.getIndex() - start.getIndex() == 1) {
			return firstPersonSalaryByLimit(end, limit);
		} else {
			long midIndex = (start.getIndex() + end.getIndex()) / 2;
			RecordFast mid = getFast(midIndex);
			RecordFast midAfter = getFast(midIndex + 1);

			if (mid.getName().equals(midAfter.getName())) {
				return calculateSalaryByLimit(start, mid, limit) + calculateSalaryByLimit(midAfter, end, limit);
			} else {
				return calculateSalaryByLimit(start, mid, limit) + calculateSalaryByLimit(midAfter, end, limit)
						+ firstPersonSalaryByLimit(midAfter, limit);
			}
		}
	}

	// Given the first record of a certain person, calculate the wage from
	// him/her under the working time limit.
	private long firstPersonSalaryByLimit(RecordFast start, long limit) {
		// The first line already means the money paid for the 1st minute.
		limit = limit - 1;

		long startIndex = start.getIndex();
		String startName = start.getName();
		long endIndex = startIndex + limit;

		// If this is at the end of the whole database, we should take special
		// care: ends at the last record.
		if (endIndex > m_numMinutes - 1) {
			endIndex = m_numMinutes - 1;
		}

		RecordFast end = getFast(endIndex);
		long midIndex = 0;
		RecordFast mid = null;

		if (start.getName().equals(end.getName())) {
			// This employer reaches the limit, so should be capped there.
			return end.getWages();
		} else {
			// This employer does not reach the limit, so should calculate the
			// whole length without restriction.
			while (startIndex < endIndex) {
				midIndex = (startIndex + endIndex + 1) / 2;
				mid = getFast(midIndex);

				if (mid.getName().equals(startName)) {
					startIndex = midIndex;
				} else {
					endIndex = midIndex - 1;
				}
			}

			return getFast(startIndex).getWages();
		}
	}
}
