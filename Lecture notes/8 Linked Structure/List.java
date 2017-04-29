package lectures;

public class List<Key, Value> {
	private ListNode<Key, Value> head = null;
	private ListNode<Key, Value> tail = null;
	private int length = 0;

	public List() {
		head = new ListNode<Key, Value>(null, null);
		tail = new ListNode<Key, Value>(null, null);

		head.setPrev(null);
		tail.setNext(null);

		length = 0;
	}

	public int getLength() {
		return length;
	}
	
	public boolean isEmpty() {
		return length == 0;
	}

	public void prepend(ListNode<Key, Value> newNode) {
		if (length == 0) {
			head.setNext(newNode);
			tail.setPrev(newNode);
		} else {
			ListNode<Key, Value> first = head.get_next();
			first.setPrev(newNode);
			head.setNext(newNode);
		}

		length++;
	}

	public void apend(ListNode<Key, Value> newNode) {
		if (length == 0) {
			head.setNext(newNode);
			tail.setPrev(newNode);
		} else {
			ListNode<Key, Value> last = tail.get_prev();
			last.setNext(newNode);
			tail.setPrev(newNode);
		}

		length++;
	}

	public void insertBefore(ListNode<Key, Value> newNode, ListNode<Key, Value> node) {
		if (newNode == null) {
			return;
		}

		ListNode<Key, Value> last = node.get_prev();
		newNode.setPrev(last);
		newNode.setNext(node);
		last.setNext(newNode);
		node.setPrev(newNode);

		length++;
	}

	public void insertAfter(ListNode<Key, Value> newNode, ListNode<Key, Value> node) {
		if (newNode == null) {
			return;
		}

		ListNode<Key, Value> next = node.get_next();
		newNode.setPrev(node);
		newNode.setNext(next);
		next.setPrev(newNode);
		node.setNext(newNode);

		length++;
	}
	
	public void delete(ListNode<Key, Value> node) {
		ListNode<Key, Value> prev = node.get_prev();
		ListNode<Key, Value> next = node.get_next();

		prev.setNext(next);
		next.setPrev(prev);

		// Totally destroy this node for security issues.
		// Avoid any kind of visit originating from this node.
		node.setPrev(null);
		node.setNext(null);
	}

	// This method is powerful but dangerous. The whole list will be reversed.
	public void reverseList() {
		ListNode<Key, Value> newHead = tail;
		ListNode<Key, Value> newTail = head;

		ListNode<Key, Value> first = head.get_next();
		ListNode<Key, Value> last = tail.get_prev();

		reverseOperator(first);

		first.setNext(newTail);
		last.setPrev(newHead);

		head = newHead;
		tail = newTail;
	}

	private void reverseOperator(ListNode<Key, Value> start) {
		ListNode<Key, Value> next = start.get_next();
		reverseOperator(next);
		next.setNext(start);
		start.setPrev(next);
	}

	// The index starts counting from 0.
	public Value getIndex(int k) {
		if (k < 0) {
			return null;
		} else {
			ListNode<Key, Value> next = head.get_next();

			for (int i = k; i > 0; i++) {
				next = next.get_next();
			}

			return next.get_value();
		}
	}

	// Two methods below have the access modifier "protected". They are not
	// intended to be used by other classes. They usually should only be used by
	// concat(list) method.
	protected ListNode<Key, Value> getLastNode() {
		return tail.get_prev();
	}

	protected ListNode<Key, Value> getFirstNode() {
		return head.get_next();
	}

	// Concatenates another list to the end of this list.
	public void concat(List<Key, Value> list) {
		ListNode<Key, Value> myLast = tail.get_prev();
		ListNode<Key, Value> thatLast = list.getLastNode();
		ListNode<Key, Value> thatFirst = list.getFirstNode();

		myLast.setNext(thatFirst);
		thatFirst.setPrev(myLast);
		thatLast.setNext(tail);
		tail.setPrev(thatLast);
	}

}
