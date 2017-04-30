package coding_quiz;

public interface IUserDB {

	boolean addUser(String name) throws Exception;
	
	boolean addFriend(String userOne, String userTwo) throws Exception;
	
	String[] findFriendsInCommon(String userOne, String userTwo) throws Exception;
	
	String findMostPopularUser() throws Exception;
}
