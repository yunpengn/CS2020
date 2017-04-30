package sg.edu.nus.cs2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Public Class: TextGenerator
 * 
 * Description: The main class for Markov Model Text Generator.
 * 
 * @author Niu Yunpeng
 */
public class TextGenerator {
	public static void main(String[] args) {
		// Initialize the text generator.
		int order = Integer.parseInt(args[0]);
		int numOfLetters = Integer.parseInt(args[1]);
		Generator generator = new Generator(order, numOfLetters, args[2]);

		generator.generateAndPrint();
	}
}

/**
 * Class: Generator
 * 
 * Description: Given a text file, this class uses Markov Model to generate a
 * new text based on the given file.
 * 
 * @author Niu Yunpeng
 */
class Generator {
	// Markov model to generate new text
	private MarkovModel model = null;

	// The first few characters in the given text, served as the starting point
	// of the new text.
	private String start = "";

	// The number of letters to generate.
	private int numOfLetters = 0;

	// The indicator for the character after a non-existing string.
	final char NOCHARACTER = (char) (255);

	/**
	 * Public Constructor: TextGenerator(int, int, String)
	 * 
	 * @param k
	 *            is the order of the Markov model.
	 * @param n
	 *            is the number of characters to generate.
	 * @param fileName
	 *            is the path to the original text file.
	 */
	public Generator(int k, int n, String fileName) {
			if (n < 0) {
				String message = "The number of characters generated ";
				message += "must be non-negative.";
				throw new IllegalArgumentException(message);
			} else {
				numOfLetters = n;

				// Reads in the text file.
				String text = readFile(fileName);

				// Stores the first preceding string as the starting point.
				start = text.substring(0, k);

				// Pre-process the whole text file, and build the frequency table.
				model = new MarkovModel(text, k);
			}
		}

	/**
	 * Private Method: String readFile(String)
	 * 
	 * @param fileName
	 *            is the path to the original text file.
	 * 
	 * @return a string representing the text inside this file.
	 */
	private String readFile(String fileName) {
		List<String> allLines = null;

		try {
			allLines = Files.readAllLines(Paths.get(fileName));
		} catch (IOException e) {
			System.err.println("Cannot read the given text file.");
		}

		// Efficient to use stringBuilder rather than string directly.
		StringBuilder builder = new StringBuilder();

		for (String line : allLines) {
			builder.append(line);

			// Line breakers ('\n') will only be included for a new paragraph.
			// Two different paragraph should have an empty line between them.
			if (line.length() == 0) {
				builder.append('\n');
			}
		}

		return builder.toString();
	}

	/**
	 * Public Method: String generate()
	 * 
	 * Description: This method generates a new text of determined length using
	 * the Markov model.
	 * 
	 * @return the string of specific length that has been generated.
	 */
	public String generate() {
		String kgram = start;
		char newLetter = 0;

		// Efficient to use stringBuilder rather than string directly.
		StringBuilder result = new StringBuilder(kgram);

		while (result.length() < numOfLetters) {
			newLetter = model.nextCharacter(kgram);

			if (newLetter != NOCHARACTER) {
				result.append(newLetter);
				kgram = kgram.substring(1) + newLetter;
			} else {
				kgram = start;
				result.append(kgram);
			}
		}

		return result.toString();
	}

	/**
	 * Public Method: String generateAndPrint()
	 * 
	 * Description: This method generates a new text of determined length using
	 * the Markov model and prints to the standard output.
	 */
	public void generateAndPrint() {
		// Initialize the preceding string.
		String kgram = start;

		// The next character generated.
		char newLetter = 0;

		// Keep a counter to record how many characters have been generated.
		int count = 0;

		// Prints the initial string first.
		System.out.print(kgram);

		while (count < numOfLetters) {
			newLetter = model.nextCharacter(kgram);

			if (newLetter != NOCHARACTER) {
				System.out.print(newLetter);
				kgram = kgram.substring(1) + newLetter;
			} else {
				kgram = start;
				System.out.print(kgram);
			}

			count++;
		}
	}
}
