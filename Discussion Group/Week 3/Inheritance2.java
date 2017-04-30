package discussion_group;

public class Inheritance2 extends InheritanceFood {
	private String name;

	class Art extends InheritanceArt {

	}
	
	public String getName() {
		return name;
	}

	public void getCritique() {
		new Art().getCritique();
	}

	public static void main(String[] args) {
		Inheritance2 student = new Inheritance2();
		student.getCritique();
		student.getFlavor();
	}
}
