package sg.edu.nus.cs2020;

public class TextGeneratorTest {
	public static void main(String[] args) {
		String fileName = "src/data/texts.in";
		TextGenerator generator = new TextGenerator(100, 100000, fileName);
		String result = generator.generate();
		System.out.println(result);
	}
}
