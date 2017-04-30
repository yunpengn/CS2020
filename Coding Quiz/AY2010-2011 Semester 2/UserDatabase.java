package coding_quiz;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Public Class: UserDatabase
 * 
 * Description: This class implements a simple FaceBook-like connection network
 * using HashMap. The maximum size is 500.
 * 
 * @author Niu Yunpeng
 */
public class UserDatabase implements IUserDB {
	// HashMap implementation for storing users.
	private HashMap<String, User> users = null;
	// The number of users in this database.
	private int size = 0;

	/**
	 * Public Constructor: UserDatabase
	 * 
	 * Description: Initialize all member variables.
	 */
	public UserDatabase() {
		users = new HashMap<String, User>();
		size = 0;
	}

	/**
	 * Public Method: boolean addUser(String)
	 * 
	 * Description: This method will create a new user and add it to the
	 * hashMap. It will throw an exception if the database has already reached
	 * its maximum size.
	 * 
	 * @return true if the add succeeds; otherwise, false if the name is an
	 *         empty string, or if the name already exists in the database.
	 */
	public boolean addUser(String name) throws Exception {
		if (size == 500) {
			throw new Exception();
		} else if (name.length() == 0 || users.containsKey(name)) {
			// Return false if the name is an empty string or the name already
			// exists in the database.
			return false;
		} else {
			size++;
			users.put(name, new User(name));
			return true;
		}
	}

	/**
	 * Public Method: boolean addFriend(String, String)
	 * 
	 * Description: This method connects two users by adding each other to their
	 * friend sets. It will throw an exception if either user does not exist.
	 * 
	 * @return true if the add succeeds (even if they are already friends)
	 */
	public boolean addFriend(String user1, String user2) throws Exception {
		if (!(users.containsKey(user1) && users.containsKey(user2))) {
			throw new Exception();
		} else {
			// Get two users from the hashMap
			User x = users.get(user1);
			User y = users.get(user2);

			x.add(user2);
			y.add(user1);

			return true;
		}
	}

	/**
	 * Public Method: String[] findFriendsInCommon(String, String)
	 * 
	 * Description: This method finds the common friends of two users. It will
	 * throw an exception if either user does not exist.
	 * 
	 * @return an array of strings containing all names of their common friends.
	 */
	public String[] findFriendsInCommon(String user1, String user2) throws Exception {
		if (!(users.containsKey(user1) && users.containsKey(user2))) {
			throw new Exception();
		} else {
			// Get two users from the hashMap
			User x = users.get(user1);
			User y = users.get(user2);

			return x.findCommon(y);
		}
	}

	/**
	 * Public Method: String findMostPopulatUser()
	 * 
	 * Description: This method will return the most popular user who has the
	 * most friends. It will throw an exception if there are no users. It may
	 * return any one of the users if there is a tie.
	 * 
	 * @return the name of the popular user
	 */
	public String findMostPopularUser() throws Exception {
		if (size == 0) {
			throw new Exception();
		} else {
			Set<Entry<String, User>> allUsers = users.entrySet();
			String mostPopularName = null;
			int mostFriends = -1;

			for (Entry<String, User> this1 : allUsers) {
				User now = this1.getValue();
				int numFriends = now.numFriends();

				if (numFriends > mostFriends) {
					mostPopularName = now.getName();
					mostFriends = numFriends;
				}
			}

			return mostPopularName;
		}
	}
}
