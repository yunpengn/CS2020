package coding_quiz;

public interface ISet {

	boolean add(String name) throws Exception;
	
	ISet intersection(ISet otherSet);
	
	int size();
	
	String[] enumerateSet();
	
}
