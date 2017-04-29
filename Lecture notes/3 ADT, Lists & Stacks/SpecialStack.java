package lectures;

public class SpecialStack extends IntStack {
	@Override
	public void push(int x) {
		super.push(x);
		System.out.println("Harbin Beer! We are happy together!");
	}

	public static void main(String[] args) {
		SpecialStack test = new SpecialStack();
		test.push(1);
		test.push(2);
		test.push(3);
		System.out.println(test.pop());
		System.out.println("This is the end");
	}
}
