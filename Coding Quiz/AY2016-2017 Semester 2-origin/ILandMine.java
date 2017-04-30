/**
 * ILandMine interface for the Coding Quiz for CS2020 2017
 * 
 * This interface specifies how the LandMine calculator should behave.  See below for details.
 */

// This is the default package for the Coding Quiz for CS2020 2017
package cs2020;

// Import the ArrayList class
import java.util.ArrayList;

/**
 * ILandMine interface
 * 
 * This interface contains three methods for working with landmines and drones.
 * 
 * The constructor to the LandMine class should take two parameters:
 *   (1) a set of mines 
 *   (2) a blast radius
 *   
 * The three methods below perform calculations based on the set of mines
 * and the blast radius.  See below for details.
 * 
 * None of the methods below should change the initial set of mines.
 */
public interface ILandMine {
	
	/**
	 * getDisabledMines
	 * 
	 * This method tests what happens when one specific mine is detonated.
	 * 
	 * It calculates the complete set of mines that will be disabled as a result.
	 * When a mine x is detonated, it is itself disabled, and all the other mines within the 
	 * blastRadius (measured by Manhattan distance) are disabled.  (There are no chain 
	 * reactions: only the mines within the blast radius of the detonated mine are disabled.)
	 *
	 * The method should return the complete list of mines that are disabled when the given 
	 * mine is detonated.
	 * 
	 * Notice that the return list should include the mine passed as input.
	 * 
	 * Also, notice that the given mine may or may not be one of the mines that the LandMine 
	 * calculator already knows about.
	 * 
	 * @param mine
	 * @return array of exploded mines
	 */	
	ArrayList<Mine> getDisabledMines(Mine mine);
	
	/**
	 * disableAllMines
	 * 
	 * This method finds a set of at most k mines such that when these k mines are 
	 * detonated by drones, all the mines in the mine field are disabled.
	 * 
	 * It returns an array of at most k mines.
	 * 
	 * For full credit, solve the problem for the special case where k=3.
	 * If k != 3, then return null.
	 * 
	 * For extra credit, solve for any k.
	 *
	 * @param k 
	 * @return array of mines to detonate
	 */
	ArrayList<Mine> disableAllMines(int k);
	
	/**
	 * minDrones
	 * 
	 * This method finds the minimum number of drones such that if each drone
	 * detonates one mine, then all the mines in the mine field will be disabled.
	 * 
	 * For the purpose of this problem, you may assume that disableAllMines works
	 * correctly for all values of k.
	 * 
	 * @return minimum number of drones needed to disable all mines
	 */
	int minDrones();

}
