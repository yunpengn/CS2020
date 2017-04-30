package discussion_group;

public class RodCutting {
	public int cut(int[] prices, int length) {
		if (length == 0) {
			return 0;
		} else {
			int max = -1;

			for (int i = 0; i < prices.length; i++) {
				int remainLength = length - i;

				if (remainLength >= 0) {
					int now = prices[i] + cut(prices, remainLength);

					if (now > max) {
						max = now;
					}
				}
			}

			return max;
		}
	}
}
