package cs2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AthleteEnumerator implements IAthleteDatabase {
	ArrayList<Student> students;
	ArrayList<StrongStudent> strongStudents;
	
	public AthleteEnumerator() {
		students = new ArrayList<Student>();
		strongStudents = new ArrayList<StrongStudent>();
	}
	
	class StudentIterator implements Iterator<Student> {
		ArrayList<StrongStudent> ss;
		int index;
		
		StudentIterator(ArrayList<StrongStudent> ss) {
			Collections.sort(ss);
			this.ss = ss;
			index = ss.size() - 1;
		}
		
		@Override
		public boolean hasNext() {
			return index >= 0;
		}

		@Override
		public Student next() {
			StrongStudent nextStudent = ss.get(index);
			Student s = new Student(nextStudent.getName(), nextStudent.getStrength(), nextStudent.getSpeed());
			index--;
			return s;
		}
		
		public void remove() {}
	}
	
	@Override
	public Iterator<Student> iterator() {
		return new StudentIterator(strongStudents);
	}

	@Override
	public void addStudent(Student s) {
		students.add(s);
		StrongStudent newStudent = new StrongStudent(s.getName(), s.getStrength(), s.getSpeed());
		strongStudents.add(newStudent);
	}

	@Override
	public Student searchStrongStudent(int studentStrength) {
		Collections.sort(strongStudents);
		
		int start = 0,
			end = strongStudents.size() - 1,
			middle;
		
		// No students have that strength
		if (studentStrength > strongStudents.get(end).getStrength()) return null; 
		
		// Binary search for first element after student strength
		while (start < end) {
			middle = (start + end) / 2;
			if (strongStudents.get(middle).getStrength() < studentStrength) {
				start = middle + 1;
			} else if (strongStudents.get(middle).getStrength() > studentStrength) {
				end = middle;
			} else {
				return null;
			}
		}
		
		return strongStudents.get(start);
	}

	@Override
	public void printAllAthletes() {
		int maxSpeed = 0;
		Collections.sort(strongStudents);
		Iterator<Student> sIterator = iterator();
		
		while(sIterator.hasNext()) {
			Student curr = sIterator.next();
			if (curr.getSpeed() > maxSpeed) {
				maxSpeed = curr.getSpeed();
				System.out.println(curr.getName() + ": " + curr.getStrength() + ", " + curr.getSpeed());
			}
		}
		
	}
}
