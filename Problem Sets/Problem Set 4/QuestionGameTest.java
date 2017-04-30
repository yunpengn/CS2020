package sg.edu.nus.cs2020;

import org.junit.Test;

public class QuestionGameTest {
	@Test
	public void test1() {
		QuestionGame gamer = new QuestionGame("src/data/smallExample");
		gamer.play(5);
	}

	@Test
	public void test2() {
		QuestionGame gamer = new QuestionGame("src/data/actors.txt");
		gamer.play(5);
	}

	@Test
	public void test3() {
		QuestionGame gamer = new QuestionGame("src/data/TestDB_1.txt");
		gamer.play(5);
	}

	@Test
	public void test4() {
		QuestionGame gamer = new QuestionGame("src/data/TestDB_2.txt");
		gamer.play(5);
	}

	@Test
	public void test5() {
		QuestionGame gamer = new QuestionGame("src/data/TestDB_3.txt");
		gamer.play(5);
	}

	@Test
	public void test6() {
		QuestionGame gamer = new QuestionGame("src/data/TestDB_4.txt");
		gamer.play(5);
	}

	@Test
	public void test7() {
		QuestionGame gamer = new QuestionGame("src/data/TestDB_5.txt");
		gamer.play(5);
	}
}
