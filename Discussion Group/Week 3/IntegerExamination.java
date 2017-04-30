package discussion_group;

public class IntegerExamination {
	static class myInteger {
		public int m_int;

		myInteger(int k) {
			m_int = k;
		}

		public String toString() {
			return Integer.toString(m_int);
		};
	}

	public static void main(String[] args) {
		// Initialize integers
		int i = 7;
		myInteger j = new myInteger(7);
		myInteger k = new myInteger(7);

		// Add one to each integer
		addOne(7);
		myIntAddOne(j);
		myOtherIntAddOne(k);

		// Print the output
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
	}

	static public void addOne(int i) {
		i = i + 1;
	}

	static public void myIntAddOne(myInteger i) {
		i.m_int = i.m_int + 1;
	}

	static public void myOtherIntAddOne(myInteger i) {
		i = new myInteger(i.m_int + 1);
	}
}
