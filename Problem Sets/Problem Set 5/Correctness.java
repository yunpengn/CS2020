package sg.edu.nus.cs2020;

import java.util.ArrayList;

public class Correctness {
	public static void main(String[] args) {
		ArrayList<Integer> hashCodes = new ArrayList<Integer>();

		for (int i = 0; i < 128; i++) {
			int code = (0x9e3779b9 << i) ^ i;
			hashCodes.add(code);
		}
		
		ArrayList<Pair> pairSum = new ArrayList<Pair>();

		for (int i = 0; i < 128; i++) {
			for (int j = i + 1; j < 128; j++) {
				int sum = hashCodes.get(i) + hashCodes.get(j);
				pairSum.add(new Pair(hashCodes.get(i), hashCodes.get(j), sum));
			}
		}

		int count = 0;
		int total = 0;

		for (int i = 0; i < pairSum.size(); i++) {
			for (int j = i + 1; j < pairSum.size(); j++) {
				Pair a = pairSum.get(i);
				Pair b = pairSum.get(j);

				if (a.compareTo(b) == 0) {
					System.out.println(a.print() + " " + b.print());
					count++;
				}

				total++;
			}
		}
		
		System.out.println("\nTotal: " + total + " with " + count + " pairs.");
		// System.out.println(33028128 / 8377);
	}
}
