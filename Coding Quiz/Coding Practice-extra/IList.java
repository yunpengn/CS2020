package coding_quiz;

public interface IList extends Iterable<Integer> {
	void add(int x);

	void remove();

	int calculate(int k);
}
