package sg.edu.nus.cs2020;

import java.util.ArrayList;

/**
 * Public Class: QuestionPlayer
 * 
 * Description: This is you, the player. The player is given the entire database
 * of objects to begin with. Then, the player asks a sequence of queries until
 * ready to guess. Most of the work, however, is delegated to the QuestionTree.
 * 
 * @author Gilbert
 */
public class QuestionPlayer {
	// The player maintains a tree, which keeps track of the state of the game.
	QuestionTree m_tree = null;
	
	// This constructor builds a tree based on the given questionObjects.
	public QuestionPlayer(ArrayList<QuestionObject> objects) {
		m_tree = new QuestionTree();
		m_tree.buildTree(objects);
	}
	
	// Checks whether the player is ready to guess, which means only one
	// possible object is left in the tree.
	public boolean readyToGuess() {
		return m_tree.countObjects() == 1;
	}
	
	// Returns the single object to guess when the player is ready.
	public String guessObject() {
		return readyToGuess() ? m_tree.getOneObject() : null;
	}
	
	// Delegates the query to the tree to generate the next question.
	public Query nextGuess() {
		return m_tree.findQuery();
	}
	
	// Updates the tree according to the given query and answer
	public void update(Query query, boolean answer) {
		m_tree.updateTree(query, answer);
	}

}
