package sg.edu.nus.cs2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Public Class: TextGeneratorWord
 * 
 * Description: The main class for Markov-word Model Text Generator.
 * 
 * @author Niu Yunpeng
 */
public class TextGeneratorWord {
	public static void main(String[] args) {
		// Initialize the text generator.
		int numOfLetters = Integer.parseInt(args[0]);
		GeneratorWord generator = new GeneratorWord(numOfLetters, args[1]);

		generator.generateAndPrint();
	}
}

/**
 * Class: GeneratorWord
 * 
 * Description: Given a text file, this class uses Markov-word Model to generate
 * a new text based on the given file.
 * 
 * @author Niu Yunpeng
 */
class GeneratorWord {
	// Markov model to generate new text
	private MarkovModelWord model = null;

	// The first word in the given text, served as the starting point of the new
	// text generated.
	private String start = "";

	// The number of words to generate.
	private int numOfWords = 0;

	// The indicator for the word after a non-existing word.
	private final String NOWORD = "";

	/**
	 * Public Constructor: TextGenerator(int, int, String)
	 * 
	 * @param n
	 *            is the number of characters to generate.
	 * @param fileName
	 *            is the path to the original text file.
	 */
	public GeneratorWord(int n, String fileName) {
		if (n < 0) {
			String message = "The number of words generated ";
			message += "must be non-negative. ";
			throw new IllegalArgumentException(message);
		} else {
			numOfWords = n;

			// Reads in the text file.
			String text = readFile(fileName);

			// Pre-process the whole text file, and build the frequency table.
			model = new MarkovModelWord(text);

			// Stores the first preceding string as the starting point.
			start = model.getFirstWord();
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
	 * the Markov-word model.
	 * 
	 * @return the string of specific number of words that has been generated.
	 */
	public String generate() {
		String kgram = start;
		String newWord = "";

		// Keep a counter to record how many WORDS have been generated.
		int count = 0;

		// Efficient to use stringBuilder rather than string directly.
		StringBuilder result = new StringBuilder(kgram);

		while (count < numOfWords) {
			newWord = model.nextWord(kgram);

			if (newWord != NOWORD) {
				result.append(newWord);
				kgram = newWord;
			} else {
				kgram = start;
				result.append(kgram);
			}

			result.append(' ');
			count++;
		}

		return result.toString();
	}

	/**
	 * Public Method: String generateAndPrint()
	 * 
	 * Description: This method generates a new text of determined number of
	 * words using the Markov-word model and prints to the standard output.
	 */
	public void generateAndPrint() {
		// Initialize the preceding word.
		String kgram = start;

		// The next word generated.
		String newWord = "";

		// Keep a counter to record how many words have been generated.
		int count = 0;

		// Prints the initial string first.
		System.out.print(kgram);
		System.out.print(" ");

		while (count < numOfWords) {
			if (kgram.length() == 0) {
				kgram = start;
			}

			newWord = model.nextWord(kgram);

			if (newWord != NOWORD) {
				System.out.print(newWord);
				kgram = newWord;
			} else {
				kgram = start;
				System.out.print(kgram);
			}

			System.out.print(" ");
			count++;
		}
	}
}
