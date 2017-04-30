package coding_quiz;

import java.util.Arrays;

/**
 * Public Class: FriendSet
 * 
 * Description: This class implements an abstract data structure to store a
 * collection of someone's all friends.
 * 
 * @author Niu Yunpeng
 */
public class FriendSet implements ISet {
	// Stores the names of all friends
	private String[] friends = null;

	// The number of friends in this set
	private int size = 0;

	public FriendSet() {
		friends = new String[100];
		size = 0;
	}

	public FriendSet(ISet other) {
		friends = new String[100];
		size = other.size();
		System.arraycopy(other.enumerateSet(), 0, friends, 0, size);
	}

	/**
	 * Public Method: boolean add(String)
	 * 
	 * Description: This method adds a new name to this set. It will throw an
	 * exception if it has already met the maximum size.
	 * 
	 * @return true if the addition is successful; false if the name already
	 *         exists or the name is an empty string.
	 */
	public boolean add(String name) throws Exception {
		if (size == 100) {
			throw new Exception();
		} else if (name.length() == 0 || contains(friends, name)) {
			return false;
		}

		friends[size++] = name;
		return true;
	}

	/**
	 * Public Method: ISet intersection(ISet)
	 * 
	 * @return the intersection of two friend sets.
	 */
	public ISet intersection(ISet otherSet) {
		FriendSet result = new FriendSet();
		String[] otherNames = otherSet.enumerateSet();
		
		for (int i = 0; i < size; i++) {
			if (!contains(otherNames, friends[i])) {
				try {
					result.add(friends[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * Private Method: boolean contains(String)
	 * 
	 * @return whether an array of names contains a certain name.
	 */
	private boolean contains(String[] names, String name) {
		for (int i = 0; i < names.length; i++) {
			if (name.equals(names[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Public Method: int size()
	 * 
	 * Description: This method returns the number of friends.
	 */
	public int size() {
		return size;
	}

	/**
	 * Public Method: String[] enumerateSet()
	 * 
	 * Description: This method returns an array containing all the friends
	 */
	public String[] enumerateSet() {
		String[] result = Arrays.copyOf(friends, size);

		return result;
	}
}
