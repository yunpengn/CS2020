package tutorial;

public class PrimitiveType {
	public static void main(String[] args) {
		System.out.printf("int size: %d range: {%d, %d}\n", Integer.BYTES, Integer.MIN_VALUE, Integer.MAX_VALUE);
		System.out.printf("double size: %d range: %.5f, %.5e", Double.BYTES, Double.MIN_VALUE, Double.MAX_VALUE);
	}
}
