package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Course;

public interface CourseDAO {
	
	public void addCourse(Course course);
	
	public void deleteCourse(Course course);
	
	public void updateCourse(Course course);
	
	public Course queryCourse(int id);
	
	public int CountCourse();
	
	public List<Course> QueryAllCourse();
	
	public List<Course> QueryCourseByUserAccount(String UserAccount);
	
	public List<Course> QueryCourseByPageSize(int row,int PageSize);
}
