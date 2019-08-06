package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Course;
import CSU.OnlineJudge.Service.CourseService;
import CSU.OnlineJudge.Service.Impl.CourseServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CourseAction extends ActionSupport{

	private Course course = new Course();
	private CourseService CourseService;

	public CourseService getCourseService() {
		return CourseService;
	}

	public void setCourseService(CourseService courseService) {
		CourseService = courseService;
	}

	//添加课程
	public void AddCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String course_name = request.getParameter("course_name");
		String course_info = request.getParameter("course_info");
		String course_time = request.getParameter("course_time");
		String course_teacher = request.getParameter("course_teacher");
		
		course.setCourseName(course_name);
		course.setCourseInfo(course_info);
		course.setCourseTime(course_time);
		course.setCourseTeacher(course_teacher);
		
		CourseService.AddCourse(course);
		
	}
	
	//删除课程
	public void DeleteCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String course_id = request.getParameter("course_id");
		int cid = Integer.valueOf(course_id);
		
		course = CourseService.QueryCourse(cid);
		if(course == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		CourseService.DeleteCourse(course);
		
	}
	
	//更新课程
	public void UpdateCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String course_name = request.getParameter("course_name");
		String course_info = request.getParameter("course_info");
		String course_time = request.getParameter("course_time");
		String course_id = request.getParameter("course_id");
		String course_teacher = request.getParameter("course_teacher");
		int cid = Integer.valueOf(course_id);
		
		course = CourseService.QueryCourse(cid);
		if(course == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		course.setCourseName(course_name);
		course.setCourseInfo(course_info);
		course.setCourseTime(course_time);
		course.setCourseTeacher(course_teacher);
		System.out.println(course_name+" " + course_teacher);
		CourseService.UpdateCourse(course);
		
	}
	
	//查询指定课程
	public void QuerySingleCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String course_id = request.getParameter("course_id");
		int cid = Integer.valueOf(course_id);
		
		course = CourseService.QueryCourse(cid);
		if(course == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(course);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//查询课程总数
	public void CountCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int cnt = CourseService.CountCourse();
		
		JSONObject jo = new JSONObject();
		jo.put("CourseCount", cnt);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//分页查询总课程
	public void QueryCourseByPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Course> CourseList = CourseService.QueryCourseByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(CourseList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	}
	
}
