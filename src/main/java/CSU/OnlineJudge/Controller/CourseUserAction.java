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
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Service.CourseService;
import CSU.OnlineJudge.Service.CourseUserService;
import CSU.OnlineJudge.Service.UserService;
import CSU.OnlineJudge.Service.Impl.CourseServiceImpl;
import CSU.OnlineJudge.Service.Impl.CourseUserServiceImpl;
import CSU.OnlineJudge.Utils.HtmlUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CourseUserAction extends ActionSupport{
	
	private CourseUser cu = new CourseUser();
	private CourseUserService CourseUserService;
	private CourseService CourseService;
	private UserService UserService;
	private User user = new User();
	private Course course = new Course();
	
	
	public UserService getUserService() {
		return UserService;
	}

	public void setUserService(UserService userService) {
		UserService = userService;
	}

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
	
	//分页查询学生课程列表
	public void QueryUserCourseByPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<User> UserList = UserService.QueryUserByPageSize(row, PageSize);

		JSONArray ja = new JSONArray();
		
		for(User u : UserList) {
			int uid = u.getUserId();
			String name = "";
			JSONObject jo = new JSONObject();
			List<CourseUser> CourseUserList = CourseUserService.QueryCourseUserByUserID(uid);
			for(CourseUser cu : CourseUserList) {
				int cid = cu.getCourseId();
				course = CourseService.QueryCourse(cid);
				if(course == null) continue;
				String cname = course.getCourseName();
				if(name.equals("")) name = name + cname;
				else name = name + "," + cname;
			}
			jo.put("userid", uid);
			jo.put("uname", u.getUserName());
			jo.put("uclassroom", u.getStudentClassroom());
			jo.put("uaccount", u.getUserAccount());
			jo.put("cname", name);
			ja.add(jo);
		}
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//通过用户Id查询指定学生的课程
	public void QueryCourseByUserId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int uid = Integer.valueOf(user_id);
		
		List<CourseUser> CourseUserList = CourseUserService.QueryCourseUserByUserID(uid);
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
		
		if(course_id == null || course_id == "" || course_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
		
		if(course_id == null || course_id == "" || course_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
		
		if(courseuser_id == null || courseuser_id == "" || courseuser_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
	
	//更新学生课程关联
	public void UpdateCourseUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String user_id = request.getParameter("user_id");
		String course_info = request.getParameter("course_info");
		
		
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int uid = Integer.valueOf(user_id);
		
		List<CourseUser> delete_list = CourseUserService.QueryCourseUserByUserID(uid);
		CourseUserService.DeleteMutiplyCourseUser(delete_list);
		
		List<CourseUser> list = CourseUserService.QueryCourseUserByUserID(uid);
		
		JSONArray ja = JSONArray.fromObject(course_info);
		JSONArray add_ja = new JSONArray();
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String course_id = jo.getString("courseId");
			int cid = Integer.valueOf(course_id);
			cu.setCourseId(cid);
			cu.setUserId(uid);
			//System.out.println(cu.toString());
			add_ja.add(cu);
		}
		
		
		List<CourseUser> cu_list = JSONArray.toList(add_ja, CourseUser.class);
		CourseUserService.AddMutiplyCourseUser(cu_list);
		
	}
	
	//excel批量导入学生课程信息
	public void AddMutiplyCourseUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String course_id = request.getParameter("course_id");
		String user_info = request.getParameter("user_info");
		
		
		if(course_id == null || course_id == "" || course_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int cid = Integer.valueOf(course_id);
		
		JSONArray ja = JSONArray.fromObject(user_info);
		JSONArray add_ja = new JSONArray();
		
		for(int i = 0; i < ja.size(); i ++) {
			
			JSONObject jo = ja.getJSONObject(i);
			String user_account = jo.getString("学号");
			
			user = UserService.QueryUserByName(user_account);
			if(user == null) continue;
			int uid = user.getUserId();
			CourseUser new_cu = new CourseUser();
			
			new_cu.setCourseId(cid);
			new_cu.setUserAccount(user_account);
			new_cu.setCourseUserId(uid);
			
			add_ja.add(new_cu);
			
		}
		
		List<CourseUser> cu_list = JSONArray.toList(add_ja,User.class);
		CourseUserService.AddMutiplyCourseUser(cu_list);
	}
	
}
