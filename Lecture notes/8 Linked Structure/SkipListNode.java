package lectures;

public class SkipListNode<Key, Value> extends ListNode<Key, Value> {
	private SkipListNode<Key, Value> m_up;
	private SkipListNode<Key, Value> m_down;

	public SkipListNode(Key key, Value value) {
		super(key, value);
		m_up = null;
		m_down = null;
	}

	public SkipListNode<Key, Value> get_up() {
		return m_up;
	}

	public SkipListNode<Key, Value> get_down() {
		return m_down;
	}

	public void setUp(SkipListNode<Key, Value> newUp) {
		m_up = newUp;
	}

	public void setDown(SkipListNode<Key, Value> newDown) {
		m_down = newDown;
	}

	public SkipListNode<Key, Value> searchUp() {
		SkipListNode<Key, Value> upNode = null;
		SkipListNode<Key, Value> iterator = this;

		while (upNode == null && iterator != null) {
			upNode = iterator.get_up();
			iterator = (SkipListNode<Key, Value>) iterator.get_prev();
		}

		return upNode;
	}
}
