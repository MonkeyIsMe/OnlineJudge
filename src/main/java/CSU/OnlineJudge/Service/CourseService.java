package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Course;

public interface CourseService {
	
	public void AddCourse(Course course);
	
	public void DeleteCourse(Course course);
	
	public void UpdateCourse(Course course);
	
	public Course QueryCourse(int id);
	
	public List<Course> QueryByUserAccount(String UserAccount);
	
	public int CountCourse();
	
	public List<Course> QueryCourseByPageSize(int row,int PageSize);
	
	public List<Course> QueryAllCourse();
	
	public Object addMutiplyCourse(List<Course> course);
	
}
