package tutorial;

public class Student {
	String name;
	int year;

	public Student(String n, int y) {
		name = n;
		year = y;
	}

	public Student(String n) {
		name = n;
	}

	public void introduce() {
		System.out.println("My name is " + name + ".");
		System.out.println("I am in Year " + year + ".");
	}

	public static void main(String[] args) {
		Student ming = new Student("Li Ming", 1);
		ming.introduce();

		System.out.println();

		Student zhang = new Student("Zhang Fei");
		zhang.introduce();
	}
}
