package tutorial;

public class Employee {
	public String name;
	public double salary;
	private int id;

	public static int idCount = 0;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		id = idCount;
		idCount++;
	}

	public void introduce() {
		System.out.println("Hello, my name is " + name);
		System.out.println("My salary is " + salary);
		System.out.println("My ID is " + id);
	}
}
