package sg.edu.nus.cs2020;

import java.util.ArrayList;

public class Precision implements IPrecision {
	public String calculateInverse(int n) {
		// Now we begin to find part 1 and 2 by keeping multiplication by 10
		ArrayList<Character> digits = new ArrayList<Character>();
		// The value of the 1/n
		double value = 1.0 / n;
		
		// The current digit
		int current = (int) (value * 10);
		value = value * 10 - current;
		char thisDigit = (char)(current + 48);
		
		// Check when the recurrence starts and ends
		while(!digits.contains(thisDigit)) {
			digits.add(thisDigit);
			current = (int) (value * 10);
			value = value * 10 - current;
			thisDigit = (char)(current+48);
			
			if(value == 0) {
				break;
			}
		}
		
		return findRecur(digits, thisDigit);
	}
	
	private String findRecur(ArrayList<Character> digits, char lastDigit) {
		String result = "";
		int i = 0;
		while(i < digits.size() && digits.get(i) != lastDigit) {
			i++;
		}
		
		int j = i;
		String pre = "";
		
		while(i < digits.size()) {
			result += digits.get(i);
			i++;
		}
		
		if(result.length() > 0) {
			result = "[" + result +"]";
		}
		
		for(int k = 0; k < j; k++) {
			pre += digits.get(k);
		}
		
		return "0." + pre + result;
	}
	
	public static void main(String[] args) {
		Precision test = new Precision();
		System.out.println(test.calculateInverse(5));
	}
}
