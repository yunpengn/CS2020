package discussion_group;

import java.util.ArrayList;

public class Fibonacci {
	private ArrayList<Integer> table = new ArrayList<Integer>();
	
	public Fibonacci() {
		table.add(0);
		table.add(1);
	}

	public int fiboRecursive(int n) {
		return n < 2 ? n : fiboRecursive(n - 1) + fiboRecursive(n - 2);
	}

	public int fiboMemo(int n) {
		if (n < table.size()) {
			return table.get(n);
		} else if (n == table.size()) {
			table.add(table.get(n - 1) + table.get(n - 2));
			return table.get(n);
		} else {
			fiboMemo(n - 1);
			fiboMemo(n - 2);

			return table.get(n);
		}
	}

	public int fiboDP(int n) {
		if (n < 2) {
			return n;
		} else {
			int last1 = 1;
			int last2 = 0;
			int result = -1;
			
			for (int i = 2; i <= n; i++) {
				int temp = result;

				result = last1 + last2;
				last2 = last1;
				last1 = temp;
			}
			
			return result;
		}
	}
}
