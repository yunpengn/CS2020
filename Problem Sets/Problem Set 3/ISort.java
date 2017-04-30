package sg.edu.nus.cs2020;

/**
 * Interface: ISort
 * 
 * Description: Simple sorting interface for an object that contains a sorting
 * routine.
 * 
 * @author Niu Yunpeng
 * based on
 * @author gilbert
 */
public interface ISort {
	/**
	 * Public Method: sort
	 * 
	 * @param <T>
	 *            type of object to be sorted; must be Comparable to T
	 * @param array
	 *            items to sort
	 */
	public <T extends Comparable<T>> void sort(T[] array);
}