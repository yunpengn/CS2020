package discussion_group;

public class Inheritance1 implements InheritanceArtIF, InheritanceFoodIF {
	public void getFlavor() {
		System.out.println("I am an artist.");
	}

	public void getCritique() {
		System.out.println("I love food!");
	}

	public static void main(String[] args) {
		Inheritance1 student = new Inheritance1();
		student.getCritique();
		student.getFlavor();
	}
}
