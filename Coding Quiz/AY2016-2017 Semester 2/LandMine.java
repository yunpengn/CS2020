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
package sg.edu.nus.cs2020;

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
	LandMine(ArrayList<Mine> mines, int blastRadius) {
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

	/**
	 * This method returns an arrayList containing all the mines that will be
	 * disabled if a certain drone is put on one mine.
	 */
	public ArrayList<Mine> getDisabledMines(Mine mine) {
		// Saving all the destroyed mines
		ArrayList<Mine> result = new ArrayList<Mine>();

		for (Mine i : m_mines) {
			int dist = calculateDistance(mine, i);

			if (dist <= m_blastRadius) {
				result.add(i);
			}
		}

		return result;
	}

	private int calculateDistance(Mine a, Mine b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}

	/**
	 * Returns an arrayList of k targets
	 */
	public ArrayList<Mine> disableAllMines(int k) {
		if (k != 3) {
			return null;
		} else {
			ArrayList<Mine> targets = new ArrayList<Mine>(k);
			ArrayList<Mine> destroyed = new ArrayList<Mine>();
			ArrayList<Mine> remain1 = new ArrayList<Mine>();
			ArrayList<Mine> remain2 = new ArrayList<Mine>();
			ArrayList<Mine> remain3 = new ArrayList<Mine>();
			
			for (int i = 0; i < m_mines.size(); i++) {
				destroyed = getDisabledMines(m_mines.get(i));
				remain1 = remain(m_mines, destroyed);

				if (remain1.size() == 0) {
					targets.add(m_mines.get(i));
					targets.add(m_mines.get(i));
					targets.add(m_mines.get(i));

					return targets;
				}

				for (int m = 0; m < remain1.size(); m++) {
					// It is okay if two drones may cause the same mine to
					// explode.
					destroyed = getDisabledMines(remain1.get(m));
					remain2 = remain(remain1, destroyed);

					if (remain2.size() == 0) {
						targets.add(m_mines.get(i));
						targets.add(remain1.get(m));
						targets.add(remain1.get(m));

						return targets;
					}

					for (int n = 0; n < remain2.size(); n++) {
						destroyed = getDisabledMines(remain2.get(n));
						remain3 = remain(remain2, destroyed);

						if (remain3.size() == 0) {
							targets.add(m_mines.get(i));
							targets.add(remain1.get(m));
							targets.add(remain2.get(n));

							return targets;
						}
					}
				}
			}

			return null;
		}
	}
	
	/**
	 * This method returns the remaining mines after an explode.
	 * 
	 * @param origin
	 * @param destroyed
	 * @return
	 */
	private ArrayList<Mine> remain(ArrayList<Mine> origin, ArrayList<Mine> destroyed) {
		ArrayList<Mine> result = new ArrayList<Mine>();
		
		for (Mine i : origin) {
			if (!destroyed.contains(i)) {
				result.add(i);
			}
		}

		return result;
	}

	@Override
	public int minDrones() {
		int count = 0;

		while (disableAllMines(count) == null) {
			count++;

			// To avoid infinite loop
			if (count == m_mines.size()) {
				return count;
			}
		}

		return count;
	}
	
}