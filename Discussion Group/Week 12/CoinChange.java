package discussion_group;

public class CoinChange {
	public int change(int[] cents, int amount) {
		if (amount == 0) {
			return 0;
		} else if (amount < 0) {
			return Integer.MAX_VALUE;
		} else {
			int min = Integer.MAX_VALUE;

			for (int i = 0; i < cents.length; i++) {
				int now = 1 + change(cents, amount - cents[i]);

				if (now < min) {
					min = now;
				}
			}

			return min;
		}
	}
}
