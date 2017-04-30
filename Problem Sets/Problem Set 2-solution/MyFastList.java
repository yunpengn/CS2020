package sg.edu.nus.cs2020;

/**
 * MyFastList
 * 
 * A different implementation with the Move-to-Front heuristic. Uses another
 * array to maintain the number of times the element has been searched.
 * 
 * The list is maintained based on the number of times the respective element
 * has been searched. Elements that have been searched more will be at the
 * front of the list.
 * 
 * CS2020 2012/13
 * @author Goh Chun Teck
 */
public class MyFastList extends FixedLengthList{
	//Tracker array used to keep track of the number of times each element has 
	//been searched. m_tracker[i] counts the number of times m_list[i] has been
	//searched
	protected int[] m_tracker;
	
	/**
	 * Constructor
	 * Note that it calls the super class's Constructor. In this case, the super
	 * class is FixedLengthList. Initialises the tracker array to 0
	 * 
	 * @param length - the length of the list
	 */
	public MyFastList(int length){
		super(length);
		
		//Initialise the tracker to 0
		m_tracker = new int[length];
		for (int i = 0; i < length; i++){
			m_tracker[i] = 0;
		}
	}
	
	
	/**
	 * Method search
	 * Searches the list for a specific item. Shifts the item to the correct
	 * position in the list if the item is found. Correct position is calculated
	 * based on the number of times the item has been searched before
	 * 
	 * @param key - the item to be searched in the list
	 * @return true if key is in the list, false if key is not in the list
	 */
	@Override
	public boolean search(int key){
		for (int i = 0; i <= m_max; i++){
			if (m_list[i] == key){
				//Remember to increase the number of times this element has been
				//searched by 1
				m_tracker[i]++;
				
				//shift the element to the appropriate position in the list
				//based on m_tracker
				shiftElement(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method shiftElement
	 * Shifts the selected element to the correct position in the list based on
	 * the number of times it has been searched before
	 * 
	 * @param index - the index of the item to be shifted to its correct position
	 */
	private void shiftElement(int index){
		int item = m_list[index];
		int numberOfSearches = m_tracker[index];
		int newIndex = index;
		
		//Determine the new index this element will go to in the list
		for (int i = 0; i <= m_max; i++){
			if (m_tracker[i] <= numberOfSearches){
				newIndex = i;
				break;
			}
		}

		//Shift the elements into the correct place
		for (int i = index; i >= newIndex + 1; i--){
			m_list[i] = m_list[i - 1];
			m_tracker[i] = m_tracker[i - 1];
		}
		m_list[newIndex] = item;
		m_tracker[newIndex] = numberOfSearches; 
	}
}
