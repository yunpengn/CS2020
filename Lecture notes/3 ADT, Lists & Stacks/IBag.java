package lectures;

public interface IBag<Item> {
	// Adds a new item to the bag
	public void add(Item x);

	// Returns whether the bag is empty or not
	public boolean isEmpty();

	// Returns the number of items currently in the bag
	public int size();
}
