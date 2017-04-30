package sg.edu.nus.cs2020;

/**
 * @author ary
 */
public class WootLinkedListNode<T> {
	
	// Attributes
	private T value;
	private WootLinkedListNode<T> next;
	
	// Constructor
	WootLinkedListNode(T value) {
		this.value = value;
		this.next = null;
	}
	
	// Accessor methods
	public T getValue() {
		return this.value;
	}
	
	public WootLinkedListNode<T> getNext() {
		return this.next;
	}
	
	// Mutator methods
	public void setNext(WootLinkedListNode<T> next) {
		this.next = next;
	}
}
