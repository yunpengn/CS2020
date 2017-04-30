/**
 * Mine class for the CS2020 Coding Quiz 2017
 * 
 * This class encapsulates a mine.  
 * 
 */

// This is the default package for the Coding Quiz for CS2020 2017
package cs2020;

/**
 * Mine
 * 
 * This class encapsulates a mine.  It is immutable: you should not
 * change a mine once you have created it.
 */
public class Mine {
	
	/**
	 * Member variables
	 */
	
	// Each mine has a unique identifier
	private int m_id;
	
	// The location of the mine, in terms of x and y-coordinates.
	private int m_xcoord = -1;
	private int m_ycoord = -1;
	
	/**
	 * Constructor
	 * @param id the mine's unique identifier
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	Mine(int id, int x, int y){
		m_id = id;
		m_xcoord = x;
		m_ycoord = y;
	}
	
	/**
	 * getId
	 * @return the mine's unique id
	 */
	public int getId(){
		return m_id;
	}
	
	/**
	 * getX
	 * @return the x-coordinate of the mine
	 */
	public int getX(){
		return m_xcoord;
	}
	
	/**
	 * getY
	 * @return the y-coordinate of the mine
	 */
	public int getY(){
		return m_ycoord;
	}
	
	/**
	 * toString
	 * @param returns a string reprentation of the mine
	 */
	@Override
	public String toString(){
		return new String("(" + m_id + ", " + m_xcoord + ", " + m_ycoord + ")");
	}

}
