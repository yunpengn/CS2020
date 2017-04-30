package sg.edu.nus.cs2020;

// This class should only be used by calculateSalaryFast and binary calculate.
public class RecordFast extends Record {
	private long index = -1;

	public RecordFast(long index, String name, long wages) {
		super(name, wages);
		this.index = index;
	}

	public long getIndex() {
		return index;
	}
}
