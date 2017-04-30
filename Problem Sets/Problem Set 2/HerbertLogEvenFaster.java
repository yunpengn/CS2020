package sg.edu.nus.cs2020;

import java.util.ArrayList;

public class HerbertLogEvenFaster extends HerbertLog {
	// Stores the line numbers (1st minute and last minute) of every employer.
	private ArrayList<RecordFast> startPoints = null;
	private ArrayList<RecordFast> endPoints = null;

	// The number of employers
	private long m_numEmploy = 0;

	// The total amount of salary (without time restriction)
	private long m_salary = -1;

	public HerbertLogEvenFaster(String fileName) {
		super(fileName);

		startPoints = new ArrayList<RecordFast>();
		RecordFast first = getFast(0);
		startPoints.add(first);
		getStartPoints(first, getFast(m_numMinutes - 1));
		m_numEmploy = startPoints.size();
	}

	public void getStartPoints(RecordFast start, RecordFast end) {
		if(start.getName().equals(end.getName())) {
			;
		} else if (end.getIndex() - start.getIndex() == 1) {
			startPoints.add(end);
		} else {
			RecordFast mid = getFast((start.getIndex() + end.getIndex()) / 2);
			getStartPoints(start, mid);
			getStartPoints(mid, end);
		}
	}

	// For debugging purpose
	public void printStartPoints() {
		for (RecordFast i : startPoints) {
			System.out.printf("%d ", i.getIndex());
		}
		System.out.println();
	}

	public void printEndPoints() {
		for (RecordFast i : endPoints) {
			System.out.printf("%d ", i.getIndex());
		}
		System.out.println();
	}
	
	// Since we know all the line numbers for 1st minute of each employer, we as
	// well know the line numbers for last minute of each employer. Therefore,
	// we can calculate the total salary.
	public long calculateSalaryEvenFaster() {
		endPoints = new ArrayList<RecordFast>();
		long sum = 0;

		for (int i = 1; i < m_numEmploy; i++) {
			RecordFast now = startPoints.get(i);
			RecordFast last = getFast(now.getIndex() - 1);
			endPoints.add(last);
			sum += last.getWages();
		}
		RecordFast lastOne = getFast(m_numMinutes - 1);
		endPoints.add(lastOne);
		sum += lastOne.getWages();

		return sum;
	}

	public long calculateMinimumWorkEvenFaster(long goalIncome) {
		if (goalIncome <= 0) {
			return 0;
		} else if (goalIncome > getSalary()) {
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

	// Use getSalary() instead of calculateSalaryEvenFaster() for access inside
	// the class to avoid duplicate calculation.
	private long getSalary() {
		if (m_salary == -1) {
			// Call calculation function if the value hasn't been calculated.
			m_salary = calculateSalaryEvenFaster();
		}

		return m_salary;
	}

	private long salaryByLimit(long limit) {
		if (limit <= 0) {
			return 0;
		} else {
			long sum = 0;

			for (int i = 0; i < m_numEmploy; i++) {
				RecordFast start = startPoints.get(i);
				RecordFast end = endPoints.get(i);

				if (end.getIndex() - start.getIndex() + 1 <= limit) {
					sum += end.getWages();
				} else {
					long restrictIndex = start.getIndex() + limit - 1;

					if (restrictIndex >= m_numMinutes) {
						restrictIndex = m_numMinutes - 1;
					}

					RecordFast restrict = getFast(restrictIndex);
					sum += restrict.getWages();
				}
			}

			return sum;
		}

	}
}
