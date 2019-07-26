package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.CourseUser;

public interface CourseUserService {

	public void addCourseUser(CourseUser cu);
	
	public void updateCourseUser(CourseUser cu);
	
	public void deleteCourseUser(CourseUser cu);
	
	public CourseUser queryCourseUser(int id);
	
	public List<CourseUser> QueryCourseUserByUserID(int UserId);
	
	public List<CourseUser> QueryCourseUserByUserAccount(String UserAccount);
}
