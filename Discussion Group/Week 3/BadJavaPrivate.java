package discussion_group;

public class BadJavaPrivate {
	private String myName = "MyNameIsASecret";

	public String getName() {
		return myName;
	}

	public static void main(String[] args) {
		BadJavaPrivate privacy = new BadJavaPrivate();

		System.out.println(privacy.getName());

		// Private variables cannot be accessed from outside the class.
		// System.out.println(privacy.name);
	}
}
