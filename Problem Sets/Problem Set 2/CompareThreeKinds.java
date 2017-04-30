package sg.edu.nus.cs2020;

public class CompareThreeKinds {
	public static void main(String[] args) {
		System.out.println("Capability of FixedLengthList:");
		FixedLengthList.compare();
		System.out.println();

		System.out.println("Capability of MoveToFrontList:");
		MoveToFrontList.compare();
		System.out.println();

		System.out.println("Capability of MyFastList:");
		MyFastList.compare();
		System.out.println();
	}
}
