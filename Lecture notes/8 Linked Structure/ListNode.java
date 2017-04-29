package lectures;

public class ListNode<KType, VType> {
	private KType m_key = null;
	private VType m_value = null;
	private ListNode<KType, VType> m_prev = null;
	private ListNode<KType, VType> m_next = null;
	
	public ListNode(KType key, VType value) {
		m_key = key;
		m_value = value;
	}

	public KType get_key() {
		return m_key;
	}

	public VType get_value() {
		return m_value;
	}

	public ListNode<KType, VType> get_prev() {
		return m_prev;
	}

	public ListNode<KType, VType> get_next() {
		return m_next;
	}

	public void setPrev(ListNode<KType, VType> before) {
		m_prev = before;
	}

	public void setNext(ListNode<KType, VType> after) {
		m_next = after;
	}
}
