package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.User;

public interface UserService {
	
	public void addUser(User user);
	
	public void deleteUser(User user);
	
	public void updateUser(User user);
	
	public User queryUser(int id);
	
	public List<User> QueryUserByPageSize(int row,int PageSize);
	
	public User QueryUserByName(String UserAccount);
	
}
