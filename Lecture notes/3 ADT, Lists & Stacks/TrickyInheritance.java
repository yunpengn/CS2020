package lectures;

public class TrickyInheritance {
	public static void main(String[] args) {
		Animal a = new Animal();
		Animal b = new Dog();
		Animal c = new Dog(10);

		a.silence();
		b.silence();
		c.silence();
	}
}
