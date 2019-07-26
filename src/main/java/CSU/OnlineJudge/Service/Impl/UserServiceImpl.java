package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.UserDAO;
import CSU.OnlineJudge.DAO.Impl.UserDAOImpl;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Service.UserService;

@Transactional
public class UserServiceImpl implements UserService{

	private UserDAO ud;
	
	public UserDAO getUd() {
		return ud;
	}

	public void setUd(UserDAO ud) {
		this.ud = ud;
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
		ud.addUser(user);
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		ud.deleteUser(user);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		ud.updateUser(user);
	}

	public User queryUser(int id) {
		// TODO Auto-generated method stub
		return ud.queryUser(id);
	}

	public List<User> QueryUserByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return ud.QueryUserByPageSize(row, PageSize);
	}

	public User QueryUserByName(String UserAccount) {
		// TODO Auto-generated method stub
		List<User> UserList = ud.QueryUserByName(UserAccount);
		if(UserList.size() == 0)
			return null;
		else return UserList.get(0); 
	}

}
