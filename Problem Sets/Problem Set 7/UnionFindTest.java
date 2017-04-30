package sg.edu.nus.cs2020;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnionFindTest {
	@Test
	public void simpleTest1() {
		UnionFind ufds = new UnionFind(10);
		ufds.unionSet(5, 7);
		ufds.unionSet(1, 5);
		ufds.unionSet(6, 7);

		assertEquals("Simple Test 1", true, ufds.isSameSet(1, 6));
	}

	@Test
	public void simpleTest2() {
		UnionFind ufds = new UnionFind(10);
		ufds.unionSet(5, 7);
		ufds.unionSet(1, 5);
		ufds.unionSet(6, 7);

		assertEquals("Simple Test 2-1", 4, ufds.setSize(5));
		assertEquals("Simple Test 2-2", 7, ufds.numSet());
	}

	@Test
	public void largerTest() {
		UnionFind ufds = new UnionFind(10);
		ufds.unionSet(3, 4);
		ufds.unionSet(4, 9);
		ufds.unionSet(8, 0);
		ufds.unionSet(2, 3);
		ufds.unionSet(5, 6);
		ufds.unionSet(5, 9);
		ufds.unionSet(7, 3);
		ufds.unionSet(4, 8);
		ufds.unionSet(6, 1);

		assertEquals("Larger Test 1", 1, ufds.numSet());
		assertEquals("Larger Test 2", 10, ufds.setSize(6));
		assertEquals("Larger Test 3", true, ufds.isSameSet(8, 5));
		assertEquals("Larger Test 4", 3, ufds.findSet(9));
	}
}
