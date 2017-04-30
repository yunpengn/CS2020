package jcc;

public class Student {
	String name = "LiLei";
	static String className = "Student";
	
	public void introduce() {
		System.out.println("My name is " + name);
	}
	
	public static void classIntroduce() {
		System.out.printf("This is a %s class.", className);
	}
}
