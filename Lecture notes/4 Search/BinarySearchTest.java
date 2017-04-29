package lectures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchTest {
	@Test
	public void testSimpleSearch() {
		BinarySearch tool = new BinarySearch();
		int[] data = {3, 4, 5, 6, 8, 9, 19, 20, 45, 87, 193, 5678};
		int target = 20;
		
		assertEquals("Simple Test", 7, tool.searchFirstIterative(data, target));
		assertEquals("Simple Test", 7, tool.searchFirstRecursive(data, target));
		assertEquals("Simple Test", 7, tool.searchLastIterative(data, target));
		assertEquals("Simple Test", 7, tool.searchLastRecursive(data, target));
	}

	@Test
	public void testDuplicate() {
		BinarySearch tool = new BinarySearch();
		int[] data = {3, 4, 6, 9, 20, 20, 20, 87, 193, 5678};
		int target = 20;

		assertEquals("Simple Test", 4, tool.searchFirstIterative(data, target));
		assertEquals("Simple Test", 4, tool.searchFirstRecursive(data, target));
		assertEquals("Simple Test", 6, tool.searchLastIterative(data, target));
		assertEquals("Simple Test", 6, tool.searchLastRecursive(data, target));
	}

	@Test
	public void testNotFound() {
		BinarySearch tool = new BinarySearch();
		int[] data = {3, 4, 6, 9, 20, 20, 20, 87, 193, 5678};
		int target = 123;

		assertEquals("Simple Test", -1, tool.searchFirstIterative(data, target));
		assertEquals("Simple Test", -1, tool.searchFirstRecursive(data, target));
		assertEquals("Simple Test", -1, tool.searchLastIterative(data, target));
		assertEquals("Simple Test", -1, tool.searchLastRecursive(data, target));
	}
}
