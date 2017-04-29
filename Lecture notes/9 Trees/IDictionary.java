package lectures;

public interface IDictionary<Key extends Comparable<Key>, Value> {
	void insert(Key k, Value v);

	Value search(Key k);

	Key successor(Key k);

	Key predecessor(Key K);

	void delete(Key k);

	boolean contains(Key k);

	int size();
}
