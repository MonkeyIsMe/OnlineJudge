package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CourseDAO;
import CSU.OnlineJudge.DAO.Impl.CourseDAOImpl;
import CSU.OnlineJudge.Model.Course;
import CSU.OnlineJudge.Service.CourseService;

@Transactional
public class CourseServiceImpl implements CourseService{

	private CourseDAO cd;
	
	public CourseDAO getCd() {
		return cd;
	}

	public void setCd(CourseDAO cd) {
		this.cd = cd;
	}

	public void AddCourse(Course course) {
		// TODO Auto-generated method stub
		cd.addCourse(course);
	}

	public void DeleteCourse(Course course) {
		// TODO Auto-generated method stub
		cd.deleteCourse(course);
	}

	public void UpdateCourse(Course course) {
		// TODO Auto-generated method stub
		cd.updateCourse(course);
	}

	public Course QueryCourse(int id) {
		// TODO Auto-generated method stub
		return cd.queryCourse(id);
	}

	public List<Course> QueryByUserAccount(String UserAccount) {
		// TODO Auto-generated method stub
		return cd.QueryCourseByUserAccount(UserAccount);
	}

	public int CountCourse() {
		// TODO Auto-generated method stub
		return cd.CountCourse();
	}

	public List<Course> QueryCourseByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return cd.QueryCourseByPageSize(row, PageSize);
	}

	public List<Course> QueryAllCourse() {
		// TODO Auto-generated method stub
		return cd.QueryAllCourse();
	}

}
