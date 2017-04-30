package coding_quiz;

public class UserDatabaseTest {
	public static void main(String[] args) {
		IUserDB userDB = new UserDatabase();
		boolean added = false;

		try {
			added = userDB.addUser("Alice"); // Returns true
			added = userDB.addUser("Bob"); // Returns true
			added = userDB.addUser("Charlie"); // Returns true
			added = userDB.addUser("Diana"); // Returns true
			added = userDB.addUser("Eli"); // Returns true
			added = userDB.addUser("Joe"); // Returns true
		} catch (Exception e) {
			System.err.println("Fail to add user.");
		}
		
		try {
			userDB.addFriend("Alice", "Bob");
			userDB.addFriend("Bob", "Charlie");
		} catch (Exception e) {
			System.err.println("Fail to add friends.");
		}
		
		String[] commonFriends = null;
		int size = 0;
		try {
			// Returns an array containing [Bob].
			commonFriends = userDB.findFriendsInCommon("Alice", "Charlie");
			size = commonFriends.length;
			System.out.printf("There are %d common friends.\n", size);
			
			// Returns an array containing [Bob].
			commonFriends = userDB.findFriendsInCommon("Alice", "Alice");
			size = commonFriends.length;
			System.out.printf("There are %d common friends.\n", size);
		} catch (Exception e) {
			System.err.println("Fail to find friends in common.");
		}

		try {
			// Throws an exception: Jim is not a user!
			userDB.addFriend("Alice", "Jim");
		} catch (Exception e) {
			System.err.println("Cannot find such a user.");
		}

		try {
			userDB.addFriend("Charlie", "Alice");
			userDB.addFriend("Charlie", "Bob"); // Already friends, but that¡¯s okay.
			userDB.addFriend("Charlie", "Diana");
			userDB.addFriend("Charlie", "Eli");
			userDB.addFriend("Charlie", "Joe");
		} catch (Exception e) {
			System.err.println("Fail to add friends again.");
		}
		
		String popular = null; // Charlie¡¯s popularity is 5
		try {
			// Charlie is the most popular.
			popular = userDB.findMostPopularUser();
		} catch (Exception e) {
			System.err.println("Fail to find the most popular user.");
		}
		System.out.println("The most popular user is " + popular + ".");

		try {
			userDB.addUser("Kate");
			userDB.addUser("Larry");
			userDB.addFriend("Bob", "Kate");
			userDB.addFriend("Bob", "Larry");
			userDB.addFriend("Bob", "Eli");
		} catch (Exception e) {
			System.err.println("Fail to add user or friend.");
		}

		try {
			// Charlie¡¯s popularity is still 5; but, Bob¡¯s popularity is also 5.
			// This may return Bob or Charlie.
			popular = userDB.findMostPopularUser();
		} catch (Exception e) {
			System.err.println("Fail to find the most popular user again.");
		}
		System.out.println("The most popular user is " + popular + ".");

		System.out.println(added);
	}
}
