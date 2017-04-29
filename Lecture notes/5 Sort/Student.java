package lectures;

public class Student implements Comparable<Student> {
	private String name = null;
	private double cap = 0;
	private String matricNo = null;

	public Student(String name, double cap, String matricNo) {
		this.name = name;
		this.cap = cap;
		this.matricNo = matricNo;
	}

	public String getName() {
		return name;
	}

	public double getCap() {
		return cap;
	}

	public String getMatricNo() {
		return matricNo;
	}

	public int compareTo(Student other) {
		if (this.getCap() > other.getCap()) {
			return 1;
		} else if (this.getCap() == other.getCap()) {
			return 0;
		} else {
			return -1;
		}
	}
}
