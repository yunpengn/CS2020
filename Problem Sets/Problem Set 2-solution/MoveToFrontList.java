package sg.edu.nus.cs2020;

/**
 * MoveToFrontList
 * A simple array implementation of a fixed-length list of integers with the
 * Move-to-Front heuristic
 * 
 * CS2020 2012/13
 * @author Goh Chun Teck
 */
public class MoveToFrontList extends FixedLengthList {

	/**
	 * Constructor
	 * Note that this calls the super class constructor from Fixed Length List
	 * 
	 * @param length - the length of the list.
	 */
	MoveToFrontList(int length){
		super(length);
	}
	
	/**
	 * Method search
	 * Performs a linear search in the list. If the item is found, move the item
	 * to the front of the list
	 * 
	 * @param key - the item to be searched in the list
	 * @return true if key is in the list, false otherwise
	 */
	@Override
	public boolean search(int key){
		for(int i = 0; i <= m_max; i++)
			if(m_list[i] == key)
			{
				/* Method 1: Swap the found item with the head of the list 
				// Move key to the front by swapping m_list[i] and m_list[0]
				int temp = m_list[0];
				m_list[0] = m_list[i];
				m_list[i] = temp; */
				
				// Method 2: Slide the found item to the front of the list
				// Though the cost of sliding an element to the front is
				// more expensive, it turns out that method 2 performs better
				// than method 1 due to the improved locality
				slideToFront(i);
				
				return true;
			}
		return false;
	}
	
	/**
	 * method slideToFront
	 * slide the item at foundIndex to the front of the list
	 * 
	 * @param foundIndex - the index of the item to be shifted
	 * 
	 */
	private void slideToFront(int foundIndex)
	{
		int insertItem = m_list[foundIndex];
		
		for(int i = foundIndex - 1; i >= 0; i--){
			m_list[i + 1] = m_list[i];
		}
		
		m_list[0] = insertItem;
	}
	
	
}

