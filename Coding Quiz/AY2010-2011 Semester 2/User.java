package coding_quiz;

import java.util.Arrays;

public class User {
	private String name;
	private FriendSet friends = null;

	public User(String name) {
		this.name = name;
		friends = new FriendSet();
	}

	public void add(String name) {
		try {
			friends.add(name);
		} catch (Exception e) {
			System.err.println("Cannot add friends to " + name);
		}
	}

	public String getName() {
		return name;
	}

	public String[] findCommon(User other) {
		String[] commonFriends = new String[friends.size()];
		int count = 0;

		String[] myFriends = friends.enumerateSet();
		String[] otherFriends = other.friends.enumerateSet();

		for (String user : myFriends) {
			if (contains(otherFriends, user)) {
				commonFriends[count++] = user;
			}
		}
		
		return Arrays.copyOf(commonFriends, count);
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

	public int numFriends() {
		return friends.size();
	}
}
