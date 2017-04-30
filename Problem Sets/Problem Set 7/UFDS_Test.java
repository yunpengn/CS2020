package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

/**
 * Public Class: UFDS_Test
 * 
 * Description: Steven's CS3226 - Web Programming and Applications module has N
 * students. CS3226 has a very big (50%) cap-stone project at the second half of
 * the semester. This project will be done in a team of at most K students per
 * team. There were M negotiations to form project groups happening in CS3226
 * recently. These M negotiations are done in chronological order. Each
 * negotiation is in this form: Student A (and everyone else who already form a
 * group with A) wanting to join student B's current project group. To simplify
 * this problem, we will assume that the only determining factor whether a
 * negotiation is successful or not is this: Merging the current group that
 * contains student A and the current group that contains student B into one
 * bigger group will not make the size of the new group exceeds K. This exercise
 * is adapted from http://www.comp.nus.edu.sg/~stevenha/cs3226.html
 * 
 * @author Niu Yunpeng
 */
public class UFDS_Test {
	public static void main(String[] args) {
		// Class manager for negotiation.
		Negotiation manager = null;
		// Stores all the request for negotiation.
		ArrayList<int[]> request = null;
		// Helper variables
		int numStudents, maxTeamSize, numNegotiations;
		
		/***************
		 | Test case 1 |
		 ***************/
		// Problem setup
		numStudents = 10;
		maxTeamSize = 2;
		numNegotiations = 5;
		
		// Negotiation history
		request = new ArrayList<int[]>();
		request.add(new int[] { 1, 2 });
		request.add(new int[] { 3, 4 });
		request.add(new int[] { 5, 6 });
		request.add(new int[] { 7, 8 });
		request.add(new int[] { 9, 10 });

		// Your code here
		manager = new Negotiation(numStudents, maxTeamSize);
		for (int i = 0; i < numNegotiations; i++) {
			int[] now = request.get(i);
			manager.negotiate(now[0], now[1]);
		}
		
		// Expected output of number of teams: 5
		assertEquals("Test case 1", 5, manager.numTeams());
		System.out.printf("1. There are %d teams left.\n", manager.numTeams());


		/***************
		 | Test case 2 |
		 ***************/
		// Problem setup
		numStudents = 10;
		maxTeamSize = 3;
		numNegotiations = 5;
		
		// Negotiation history
		request = new ArrayList<int[]>();
		request.add(new int[] { 1, 2 });
		request.add(new int[] { 1, 3 });
		request.add(new int[] { 1, 4 });
		request.add(new int[] { 1, 5 });
		request.add(new int[] { 1, 6 });
		
		// Your code here
		manager = new Negotiation(numStudents, maxTeamSize);
		for (int i = 0; i < numNegotiations; i++) {
			int[] now = request.get(i);
			manager.negotiate(now[0], now[1]);
		}
		
		// Expected output of number of teams: 8
		assertEquals("Test case 2", 8, manager.numTeams());
		System.out.printf("2. There are %d teams left.\n", manager.numTeams());


		/***************
		 | Test case 3 |
		 ***************/
		// Problem setup
		numStudents = 57;
		maxTeamSize = 5;
		numNegotiations = 4;
		
		// Negotiation history
		request = new ArrayList<int[]>();
		request.add(new int[] { 1, 2 });
		request.add(new int[] { 1, 3 });
		request.add(new int[] { 4, 5 });
		request.add(new int[] { 3, 5 });
		
		// Your code here
		manager = new Negotiation(numStudents, maxTeamSize);
		for (int i = 0; i < numNegotiations; i++) {
			int[] now = request.get(i);
			manager.negotiate(now[0], now[1]);
		}

		// Expected output of number of teams: 53
		assertEquals("Test case 3", 53, manager.numTeams());
		System.out.printf("3. There are %d teams left.\n", manager.numTeams());
	}
}

/**
 * Class: Negotiation
 * 
 * @author Niu Yunpeng
 */
class Negotiation {
	// The model for use as union-find data structure.
	private UnionFind ufds;
	// The maximum number of students in each team.
	private int maxTeamSize;

	/**
	 * Public Constructor: Negotiation(int, int)
	 * 
	 * Description: This constructor initializes the union-find model and the
	 * maximum team size.
	 */
	public Negotiation(int numStudents, int maxTeamSize) {
		this.ufds = new UnionFind(numStudents);
		this.maxTeamSize = maxTeamSize;
	}

	/**
	 * Public Method: boolean negotiate(int, int)
	 * 
	 * Description: This method negotiates between two teams which i and j are
	 * from. If the merged size is not larger than the maximum allowed team
	 * size, merge them.
	 * 
	 * @return true if two teams are merged; false otherwise.
	 */
	public boolean negotiate(int i, int j) {
		// The index in this example starts from 1, while UFDS starts from 0.
		i--;
		j--;

		if (ufds.setSize(i) + ufds.setSize(j) <= maxTeamSize) {
			ufds.unionSet(i, j);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Public Method: int numTeams()
	 * 
	 * @return the number of teams present in the class.
	 */
	public int numTeams() {
		return ufds.numSet();
	}
}