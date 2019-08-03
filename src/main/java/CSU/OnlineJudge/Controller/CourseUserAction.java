package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Course;
import CSU.OnlineJudge.Model.CourseUser;
import CSU.OnlineJudge.Service.CourseService;
import CSU.OnlineJudge.Service.CourseUserService;
import CSU.OnlineJudge.Service.Impl.CourseServiceImpl;
import CSU.OnlineJudge.Service.Impl.CourseUserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CourseUserAction extends ActionSupport{
	
	private CourseUser cu = new CourseUser();
	private CourseUserService CourseUserService;
	private CourseService CourseService;
	
	

	public CourseUserService getCourseUserService() {
		return CourseUserService;
	}

	public void setCourseUserService(CourseUserService courseUserService) {
		CourseUserService = courseUserService;
	}

	public CourseService getCourseService() {
		return CourseService;
	}

	public void setCourseService(CourseService courseService) {
		CourseService = courseService;
	}

	//查询指定学生的课程
	public void QuerySingleCourseUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("user_account");
		
		List<CourseUser> CourseUserList = CourseUserService.QueryCourseUserByUserAccount(user_account);
		JSONArray ja = new JSONArray();
		
		for(CourseUser courseuser : CourseUserList) {
			int cid = courseuser.getCourseId();
			Course course = CourseService.QueryCourse(cid);
			JSONObject jo = JSONObject.fromObject(course);
			ja.add(jo);
		}
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//查询学生的是否属于课程
	public void QueryCourseToUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_account = request.getParameter("user_account");
		String course_id = request.getParameter("course_id");
		
		List<CourseUser> CourseUserList = CourseUserService.QueryCourseUserByUserAccount(user_account);
		int cid = Integer.valueOf(course_id);
		
		boolean flag = false;
		
		for(CourseUser courseuser : CourseUserList) {
			int courseid = courseuser.getCourseId();
			if(courseid == cid) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			out.println("Success");
			out.flush(); 
			out.close(); 
		}
		else {
			out.println("Fail");
			out.flush(); 
			out.close(); 
		}
		
	}
	
	//添加学生课程关联
	public void AddCourseUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_account = request.getParameter("user_account");
		String course_id = request.getParameter("course_id");
		String user_id = request.getParameter("user_id");
		
		int cid = Integer.valueOf(course_id);
		int uid = Integer.valueOf(user_id);
		
		cu.setCourseId(cid);
		cu.setUserAccount(user_account);
		cu.setCourseUserId(uid);
		
		CourseUserService.addCourseUser(cu);
		
	}
	
	//删除学生课程关联
	public void DeleteCourseUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String courseuser_id = request.getParameter("courseuser_id");
		
		int cuid = Integer.valueOf(courseuser_id);
		
		cu = CourseUserService.queryCourseUser(cuid);
		
		if(cu == null) {
			out.println("Fail");
			out.flush(); 
			out.close(); 
			return ;
		}
		
		CourseUserService.deleteCourseUser(cu);
	}
}
