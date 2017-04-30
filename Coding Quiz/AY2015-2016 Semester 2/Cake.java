/**
 * CS2020 Coding Quiz 2016: Cutting a cake in half
 * 
 * @author Niu Yunpeng
 * based on
 * @author gilbert
 */
package coding_quiz;

public class Cake {
	// arr[0] is the x coordinate, arr[1] is the y coordinate.
	private int[] outer1 = null;
	private int[] outer2 = null;
	private int[] inner1 = null;
	private int[] inner2 = null;

	// The total area of the cake with removing part of it.
	private int totalSize = 0;

	/**
	 * Public Constructor: Cake(int, int, int, int);
	 * 
	 * Description: (x1, y1) and (x2, y2) are four integer coordinates for two
	 * of the corners of the rectangular cake. For a valid cake, (x1 < x2) and
	 * (y1 < y2). See the problem set for more details.
	 */
	Cake(int x1, int y1, int x2, int y2) throws IllegalArgumentException {
		if (x2 - x1 < 4 || y2 - y1 < 4) {
			throw new IllegalArgumentException();
		}

		outer1 = new int[]{x1, y1};
		outer2 = new int[]{x2, y2};
	}
	
	/**
	 * Default Method: void removePiece(int, int, int, int)
	 * 
	 * Description: Removes a piece of the cake.(x1, y1) and (x2, y2) are four
	 * integer coordinates describing two of the corners of the rectangular
	 * piece being removed. The piece can have any area (including zero). For a
	 * valid piece, both points should like within the cake or on the boundary.
	 * If the piece is invalid, it will throw an IllegalArgumentException.
	 */
	void removePiece(int x1, int y1, int x2, int y2) throws IllegalArgumentException {
		if (x1 > x2 || y1 > y2) {
			throw new IllegalArgumentException();
		}

		if (x1 < outer1[0] || x1 > outer2[0]) {
			throw new IllegalArgumentException();
		}

		if (x2 < outer1[0] || x2 > outer2[0]) {
			throw new IllegalArgumentException();
		}

		if (y1 < outer1[1] || y1 > outer2[1]) {
			throw new IllegalArgumentException();
		}

		if (y2 < outer1[1] || y2 > outer2[1]) {
			throw new IllegalArgumentException();
		}

		inner1 = new int[]{x1, y1};
		inner2 = new int[]{x2, y2};

		// Updates the total size after the cake has been cut.
		totalSize = findLeftSize(outer2[0]);
	}
	
	/**
	 * Public Method: int findLeftSize(int)
	 * 
	 * Description: This method finds (and returns) the area of the cake to the
	 * left of the vertical line y=xPos.
	 *
	 * @param xPos
	 *            is the x-axis coordinate of the vertical line.
	 * 
	 * @return the size of the area on the left of the vertical line.
	 */
	public int findLeftSize(int xPos) {
		// The size of the cake's left area without removing that part.
		int outerSize = 0;
		if (xPos <= outer1[0]) {
			outerSize = 0;
		} else if (xPos >= outer2[0]) {
			outerSize = (outer2[0] - outer1[0]) * (outer2[1] - outer1[1]);
		} else {
			outerSize = (xPos - outer1[0]) * (outer2[1] - outer1[1]);
		}

		// Imagine the removed part as another cake. This is the size of the
		// left area of that new cake.
		int innerSize = 0;
		if (xPos <= inner1[0]) {
			outerSize = 0;
		} else if (xPos >= inner2[0]) {
			outerSize = (inner2[0] - inner1[0]) * (inner2[1] - inner1[1]);
		} else {
			outerSize = (xPos - inner1[0]) * (inner2[1] - inner1[1]);
		}

		return outerSize - innerSize;
	}
	
	/**
	 * Public Method: int findHalf()
	 * 
	 * Description: Finds (and returns) the x-coordinate that best divides the
	 * cake in half. If possible, it returns an integer value xPos such that the
	 * line y=xPos exactly divides the cake in half. If that is impossible, it
	 * returns the integer that is closest to dividing the cake in half.
	 * 
	 * @return the (approximated) x-coordinate of the vertical line which best
	 *         divides the cake in half.
	 */
	public int findHalf() {
		// The range and value of xPos.
		int begin = outer1[0];
		int end = outer2[0];
		int xPos = (begin + end) / 2;

		// The difference of the size on the left and on the right.
		// left - right = left - (total - left) = 2 * left - total
		int sizeDifference = 2 * findLeftSize(xPos) - totalSize;
		
		// Use binary search to find the best value of xPos.
		// Stop immediately if the area on the left and right is equal.
		while (sizeDifference > 0 && begin < end) {
			if (sizeDifference < 0) {
				begin = xPos;
			} else {
				end = xPos - 1;
			}

			xPos = (begin + end) / 2;
			sizeDifference = 2 * findLeftSize(xPos) - totalSize;
		}

		return xPos;
	}
}
