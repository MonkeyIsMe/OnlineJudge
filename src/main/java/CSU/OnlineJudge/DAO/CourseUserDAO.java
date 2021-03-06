package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.CourseUser;

public interface CourseUserDAO {
	
	public void addCourseUser(CourseUser cu);
	
	public void updateCourseUser(CourseUser cu);
	
	public void deleteCourseUser(CourseUser cu);
	
	public CourseUser queryCourseUser(int id);
	
	public List<CourseUser> QueryCourseUserByUserID(int UserId);
	
	public List<CourseUser> QueryCourseUserByUserAccount(String UserAccount);
	
	public Object addMutiplyCourseUser(List<CourseUser> cu);
	
	public Object deleteMutiplyCourseUser(List<CourseUser> cu);
}
