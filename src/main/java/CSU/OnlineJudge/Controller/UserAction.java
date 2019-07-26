package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Service.UserService;
import CSU.OnlineJudge.Service.Impl.UserServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserAction extends ActionSupport{
	
	private UserService us;
	private User user = new User();
	
	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	//删除用户
	public void DeleteUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		int uid = Integer.valueOf(user_id);
		
		user = us.queryUser(uid);
		if(user == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		us.deleteUser(user);
	}
	
	//更新用户的交题数据
	public void UpdateUserSubmission() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		String submission = request.getParameter("flag");
		int uid = Integer.valueOf(user_id);
		
		user = us.queryUser(uid);
		if(user == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		if(submission.equals("wa")) {
			int sub = user.getWrongAnswerTimes();
			sub++;
			user.setWrongAnswerTimes(sub);
			int total = user.getSubmissionTimes();
			total++;
			user.setSubmissionTimes(total);
		}
		else if(submission.equals("ac")) {
			int sub = user.getAcceptTimes();
			sub++;
			user.setAcceptTimes(sub);
			int total = user.getSubmissionTimes();
			total++;
			user.setSubmissionTimes(total);
		}
		else if(submission.equals("ce")) {
			int sub = user.getCompileErrorTimes();
			sub++;
			user.setCompileErrorTimes(sub);
			int total = user.getSubmissionTimes();
			total++;
			user.setSubmissionTimes(total);
		}
		else if(submission.equals("tle")) {
			int sub = user.getTimeLimitTimes();
			sub ++;
			user.setTimeLimitTimes(sub);
			int total = user.getSubmissionTimes();
			total++;
			user.setSubmissionTimes(total);
		}
		else if(submission.equals("rte")) {
			int sub = user.getRuntimeErrorTimes();
			sub ++;
			user.setRuntimeErrorTimes(sub);
			int total = user.getSubmissionTimes();
			total++;
			user.setSubmissionTimes(total);
		}
		
		us.updateUser(user);
	}
	
	//查询单个学生
	public void QuerySingleUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		int uid = Integer.valueOf(user_id);
		
		user = us.queryUser(uid);
		if(user == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(user);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//更新用户信息
	public void UpdateUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_classroom = request.getParameter("user_classroom");
		String user_info = request.getParameter("user_info");
		String user_name = request.getParameter("user_name");
		int uid = Integer.valueOf(user_id);
		
		user = us.queryUser(uid);
		if(user == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		user.setStudentClassroom(user_classroom);
		user.setUserInfo(user_info);
		user.setUserName(user_name);
		
		us.updateUser(user);
		
	}
	
	//添加用户信息
	public void AddUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_classroom = request.getParameter("user_classroom");
		String user_info = request.getParameter("user_info");
		String user_name = request.getParameter("user_name");
		
		user.setStudentClassroom(user_classroom);
		user.setUserInfo(user_info);
		user.setUserName(user_name);
		user.setSubmissionTimes(0);
		user.setAcceptTimes(0);
		user.setWrongAnswerTimes(0);
		user.setSubmissionTimes(0);
		user.setRuntimeErrorTimes(0);
		user.setCompileErrorTimes(0);
		
		us.addUser(user);
		
	}

	//查询所有学生数据
	public void QueryAllUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<User> UserList = us.QueryUserByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(UserList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
}
