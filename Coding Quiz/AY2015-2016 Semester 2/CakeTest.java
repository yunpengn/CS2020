package coding_quiz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CakeTest {
	@Test
	public void simpleTest() {
		Cake birthday = new Cake(2, 3, 12, 10);
		birthday.removePiece(5, 5, 9, 8);
		int xPos = birthday.findHalf();
		
		assertEquals("simpleTest", xPos, 7);
	}
}
