/**
 * LandMine class for the CS2020 Coding Quiz 2017
 * 
 * This class is designed to capture a field of landmines,
 * each at a specified location.  We have a set of drones,
 * and each drone can detonate one landmine.  (The drone
 * will be itself destroyed in the process.)  The goal here
 * is to determine which landmines the drone should detonate,
 * and how many drones we need.
 * 
 */

// This is the default package for the Coding Quiz for CS2020 2017
package cs2020;

//Import the ArrayList class
import java.util.ArrayList;

/**
 * LandMine
 * 
 * This class implements the ILandMine interface.
 * See the ILandMine interface for details. 
 *
 */
public class LandMine implements ILandMine{

	/**
	 * Member variables
	 */
	
	// A list of mines
	private ArrayList<Mine> m_mines = null;
	// The blast radius of each mine
	private int m_blastRadius = -1;
	
	/**
	 * Constructor
	 *  
	 *  If the (Manhattan) distance between two mines is less than the 
	 *  blastRadius, then if one mine explodes, the other will be disabled.
	 *  
	 * @param mines is a list of mines
	 * @param blastRadius is the radius of the mines
	 */
	LandMine(ArrayList<Mine> mines, int blastRadius){
		m_mines = mines;
		m_blastRadius = blastRadius;
	}
	
	/**
	 * copyArary
	 * 
	 * This method creates a (shallow) copy of a list of mines.
	 * Notice that it does not create new mines, i.e., the mines
	 * in the new list are the same as the mines in the original list.
	 *  
	 * @param list
	 * @return copy of list
	 */
	ArrayList<Mine> copyArray(ArrayList<Mine> list){
		ArrayList<Mine> newList = new ArrayList<Mine>(list.size());
		for (int i=0; i<list.size(); i++){
			newList.add(list.get(i));			
		}
		return newList;
	}
	
}