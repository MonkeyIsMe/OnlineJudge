package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CourseUserDAO;
import CSU.OnlineJudge.DAO.Impl.CourseUserDAOImpl;
import CSU.OnlineJudge.Model.CourseUser;
import CSU.OnlineJudge.Service.CourseUserService;

@Transactional
public class CourseUserServiceImpl implements CourseUserService{

	private CourseUserDAO cud;
	
	
	public CourseUserDAO getCud() {
		return cud;
	}

	public void setCud(CourseUserDAO cud) {
		this.cud = cud;
	}

	public void addCourseUser(CourseUser cu) {
		// TODO Auto-generated method stub
		cud.addCourseUser(cu);
	}

	public void updateCourseUser(CourseUser cu) {
		// TODO Auto-generated method stub
		cud.updateCourseUser(cu);
	}

	public void deleteCourseUser(CourseUser cu) {
		// TODO Auto-generated method stub
		cud.deleteCourseUser(cu);
	}

	public CourseUser queryCourseUser(int id) {
		// TODO Auto-generated method stub
		return cud.queryCourseUser(id);
	}

	public List<CourseUser> QueryCourseUserByUserID(int UserId) {
		// TODO Auto-generated method stub
		return cud.QueryCourseUserByUserID(UserId);
	}

	public List<CourseUser> QueryCourseUserByUserAccount(String UserAccount) {
		// TODO Auto-generated method stub
		return cud.QueryCourseUserByUserAccount(UserAccount);
	}

	public Object AddMutiplyCourseUser(List<CourseUser> cu) {
		// TODO Auto-generated method stub
		return cud.addMutiplyCourseUser(cu);
	}

	public Object DeleteMutiplyCourseUser(List<CourseUser> cu) {
		// TODO Auto-generated method stub
		return cud.deleteMutiplyCourseUser(cu);
	}

}
