package discussion_group;

public class BadJavaStatic {
	private static String school = "National University of Singapore";
	private String name;
	
	public BadJavaStatic(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static void introduce() {
		System.out.println("I am from " + school + ".");

		// Static methods cannot use non-static variables.
		// System.out.println("My name is " + name);
	}
}
