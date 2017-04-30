package sg.edu.nus.cs2020;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class WootTest {

	@Test
	public void sampleWootTest() {
		Woot w = new Woot();
		LinkedList<Integer> original = new LinkedList<Integer>(Arrays.asList(6, 4, -10, 8, 9));
		WootLinkedList<Integer> wll = w.processWOOT(original, 8);
		assertEquals(w.computeWoot(wll), 10);
	}

}
