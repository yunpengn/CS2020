package sg.edu.nus.cs2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 * Public Abstract Class: QuestionGameBase
 * 
 * Description: This is the main class for controlling the game. It loads the
 * object database, instantiates the player, chooses an object from the given
 * database, and then plays the game. Note that it is an abstract class, meaning
 * that it cannot be instantiated directly. To use this class with your Question
 * Tree, subclass it and write your test code.
 * 
 * @author Gilbert
 */
public abstract class QuestionGameBase {
	// The player class
	QuestionPlayer m_player;

	// The chosen object
	private QuestionObject m_chosenObject;
	
	// All the objects
	ArrayList<QuestionObject> m_objects;
	
	// Random number generator
	private Random m_gen;
	
	/**
	 * Description: The constructor reads in the objects from a specified file,
	 * and chooses a random object. It also initializes the player, sending it
	 * the list of possible objects.
	 *
	 * @param objectFileName
	 *            is the database file to be read.
	 */
	protected QuestionGameBase(String objectFileName) {
		// Get the objects
		m_objects = readObjectFile(objectFileName);
		
		// Initialize random number generator.
		m_gen = new Random();
		
		// Initialize the game state
		RestartGame();
	}
	
	/**
	 * This method reinitializes the game variables to play again. It chooses a
	 * new object at random first, then creates a new player to play the game.
	 */
	public void RestartGame() {
		m_chosenObject = m_objects.get(m_gen.nextInt(m_objects.size()));
		
		// Initialize the player
		m_player = new QuestionPlayer(m_objects);
	}
	
	/**
	 * Reads in a specially formatted file.
	 */
	private ArrayList<QuestionObject> readObjectFile(String objectFileName) {
		try{
			// Open the file
			ArrayList<QuestionObject> objArray = null;
			FileReader f = new FileReader(objectFileName);
			BufferedReader buff = new BufferedReader(f);

			// Read the first line of the file and parse the number of objects
			String line = buff.readLine().trim();
			int objCount = Integer.parseInt(line);
			objArray = new ArrayList<QuestionObject>(objCount);

			// Read in each object
			for (int i = 0; i < objCount; i++) {
				// The 1st line of the object contains its name.
				String name = buff.readLine().trim();
				// The 2nd line of the object contains the number of properties.
				String propCountLine = buff.readLine().trim();
				int propCount = Integer.parseInt(propCountLine);

				// Now we loop and read in each property
				TreeSet<String> props = new TreeSet<String>();
				for (int j = 0; j < propCount; j++) {
					String propName = buff.readLine().trim();
					if (propName != null && propName != "") {
						props.add(propName);
					}
				}

				// Once we have all the properties, create a new QuestionObject
				if (name != null && name != "" && props.size() > 0) {
					objArray.add(new QuestionObject(name, props));
				}
			}

			// Close the file and buffer after we finish reading.
			f.close();
			buff.close();
			return objArray;
		} catch (Exception e) {
			// If there is an error reading in the file, there isn't much we can do.
			// Print out an error, and later the program will exit.
			String message = "Unable to read in object database. Please check the ";
			message += "filename, the path, and that the file is formatted correctly.";

			System.out.println(e);
			System.out.println(message);
		}

		return null;
	}
	
	/**
	 * Public Method: boolean isSatisfied(Query)
	 * 
	 * Description: Checks whether the chosen object satisfies the query because
	 * the properties are in sorted order (both in the query and in the object).
	 * Since both lists are sorted, it just loops through and verifies that all
	 * the specifies properties and non-properties are present.
	 */
	private boolean isSatisfied(Query query) {
		// Error checking
		if (query == null) {
			throw new IllegalArgumentException("Null query cannot be satisfied.");
		}

		// Get iterators for the query
		Iterator<String> props = query.propertyIterator();
		Iterator<String> notProps = query.notPropertyIterator();

		// First, we check the properties specified in the query
		while (props.hasNext()) {
			if (!m_chosenObject.containsProperty(props.next())) {
				return false;
			}
		}

		// Second, we check the negative properties specified in the query
		while (notProps.hasNext()) {
			if (m_chosenObject.containsProperty(notProps.next())) {
				return false;
			}
		}

		// If we got this far, the query is satisfied
		return true;
	}
	
	/**
	 * This is the main loop for playing the game. It repeatedly asks the player
	 * for a query, checks whether it is satisfied, and updates the user. When
	 * the user is ready to guess, ask for a guess, and see if they are right.
	 *
	 * @throws Exception
	 *             if the player loses
	 * 
	 * @return the number of queries made during this gamePlay.
	 */
	int playGame() throws Exception {
		System.out.println("Chosen " + m_chosenObject);
		int countQueries = 0;
		
		// Main loop here
		while (!m_player.readyToGuess()) {
			countQueries++;

			// Get the player's query
			Query query = m_player.nextGuess();
			System.out.println(query);
			// See if the query is true or false
			boolean answer = isSatisfied(query);
			System.out.println("Answer: " + answer);
			// Now update the player
			m_player.update(query, answer);
		}

		// Okay, the player is ready to guess
		String guess = m_player.guessObject();
		System.out.println("You guess: " + guess);

		// See if they are right!
		if (guess.compareTo(m_chosenObject.getName())== 0){
			System.out.println("You win!");
		} else {
			System.out.println("Loser!");
			throw new Exception("You lost!");
		}

		System.out.println("You made " + countQueries + " guesses.");
		return countQueries;
	}
}
