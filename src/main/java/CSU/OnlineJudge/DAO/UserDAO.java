package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.User;

public interface UserDAO {
	
	public void addUser(User user);
	
	public void deleteUser(User user);
	
	public void updateUser(User user);
	
	public User queryUser(int id);
	
	public List<User> QueryUserByPageSize(int row,int PageSize);
	
	public List<User> QueryUserByName(String UserAccount);
	
	public int CountUser();
	
	public List<User> QueryAcceptNumber(int row,int PageSize);
	
}
