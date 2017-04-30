package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BellmanFordTest {
	@Test
	public void testIncremental(){
		String fileName = "src/data/testFileIncremental.txt";
		AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph(fileName);
		BellmanFord finder = new BellmanFord(amg);
		finder.constructPath(0, 6);
	    
		assertEquals("0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6", finder.getPath());
	}
	
	@Test
	public void testIncrementalCycles() {
		String fileName = "src/data/testFileIncremental.txt";
		AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph(fileName);
		BellmanFord finder = new BellmanFord(amg);
		boolean actual = finder.hasNegativeCycle();
	    
	    assertEquals(false, actual);
	}
	
	@Test
	public void testNegative() {
		String fileName = "src/data/testFileWithNegativeCycle.txt";
		AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph(fileName);
		BellmanFord finder = new BellmanFord(amg);
		try {
			finder.constructPath(0, 6);
		} catch(Exception e) {
			assert (true);
		}
	}

	@Test
	public void testNegativeCycles(){
		String fileName = "src/data/testFileWithNegativeCycle.txt";
		AdjacencyMatrixGraph amg = new AdjacencyMatrixGraph(fileName);
		BellmanFord finder = new BellmanFord(amg);
		boolean actual = finder.hasNegativeCycle();
	    
	    assertEquals(true, actual);
	}
}
