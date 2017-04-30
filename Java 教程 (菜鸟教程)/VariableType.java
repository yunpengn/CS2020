package tutorial;

public class VariableType {
	public static void main(String[] args) {
		Employee jack = new Employee("Jackson", 12345.67);
		Employee tim = new Employee("Tom", 13579.86);

		jack.introduce();
		tim.introduce();
		System.out.println("The next ID is " + Employee.idCount);
		System.out.println("The nam is " + jack.name);
	}
}
