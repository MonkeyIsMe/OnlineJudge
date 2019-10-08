package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Service.UserService;
import CSU.OnlineJudge.Service.Impl.UserServiceImpl;
import CSU.OnlineJudge.Utils.DesUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserAction extends ActionSupport{
	
	private UserService UserService;
	private User user = new User();
	
	public UserService getUserService() {
		return UserService;
	}

	public void setUserService(UserService userService) {
		UserService = userService;
	}

	//删除用户
	public void DeleteUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		String user_id = request.getParameter("user_id");
		
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int uid = Integer.valueOf(user_id);
		
		user = UserService.queryUser(uid);
		if(user == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		UserService.deleteUser(user);
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
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
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
		
		int uid = Integer.valueOf(user_id);
		
		user = UserService.queryUser(uid);
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
		
		UserService.updateUser(user);
	}
	
	//通过用户ID查询单个学生
	public void QuerySingleUserById() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
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
		
		int uid = Integer.valueOf(user_id);
		user = UserService.queryUser(uid);
		
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
	
	//通过账号查询单个学生
	public void QuerySingleUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_account = request.getParameter("user_account");
		//String user_account = "AC";
		//int uid = Integer.valueOf(user_id);
		
		user = UserService.QueryUserByName(user_account);
		
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
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
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
		
		int uid = Integer.valueOf(user_id);
		
		user = UserService.queryUser(uid);
		if(user == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		user.setStudentClassroom(user_classroom);
		user.setUserInfo(user_info);
		user.setUserName(user_name);
		
		UserService.updateUser(user);
		
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
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		user.setStudentClassroom(user_classroom);
		user.setUserInfo(user_info);
		user.setUserName(user_name);
		user.setSubmissionTimes(0);
		user.setAcceptTimes(0);
		user.setWrongAnswerTimes(0);
		user.setSubmissionTimes(0);
		user.setRuntimeErrorTimes(0);
		user.setCompileErrorTimes(0);
		
		UserService.addUser(user);
		
	}

	//查询所有学生数据
	public void QueryAllUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<User> UserList = UserService.QueryUserByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(UserList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}

	
	//查询学生总数
	public void CountUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		int cnt = UserService.CountUser();
		
		JSONObject jo = new JSONObject();
		jo.put("UserCount", cnt);
		
		out.println(jo.toString());
        out.flush(); 
        out.close();
		
	}
	
	//查询所有学生正确排名
	public void QueryAcceptRank() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<User> UserList = UserService.QueryAcceptNumber(row, PageSize);
		
		JSONArray ja = new JSONArray();
		for(User u : UserList) {
			
			JSONObject jo = new JSONObject();
			
			String uname = u.getUserName();
			String uclass = u.getStudentClassroom();
			String uaccount = u.getUserAccount();
			int ucnt = u.getAcceptTimes();
			int usub = u.getSubmissionTimes();
			
			jo.put("username", uname);
			jo.put("userclass", uclass);
			jo.put("useraccount", uaccount);
			jo.put("useraccept", ucnt);
			jo.put("usersubmission", usub);
			ja.add(jo);
		}
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//接收登录信息
	public void RecieveMessage() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String info = request.getParameter("token");
		
		DesUtil du = new DesUtil();
		String msg = du.strDec(info, "csu", "vlab", "214");
		
		JSONObject jo = JSONObject.fromObject(msg);
		String name = jo.getString("name");
		String useraccount = jo.getString("username");
		String role = jo.getString("role");
		
		user = UserService.QueryUserByName(useraccount);
		if(user == null) {
			User u = new User();
			u.setUserName(name);
			u.setUserRole(role);
			u.setUserAccount(useraccount);
			UserService.addUser(u);
		}
		
		request.getSession().setAttribute("username", name);
		request.getSession().setAttribute("useraccount", useraccount);
		request.getSession().setAttribute("role", role);
		//System.out.println("2.useraccount = " + useraccount);
		//System.out.println(request.getSession().getAttribute("useraccount"));
		if(role.equals("1")) {
			response.sendRedirect("http://202.197.66.200:1188/OnlineJudge/manage.html");
		}
		else {
			response.sendRedirect("http://202.197.66.200:1188/OnlineJudge/Main.html");
		}
	}
	
	//设置用户信息
	public void SetUserInfo() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		String user_name = (String) session.getAttribute("username");
		String user_role = (String) session.getAttribute("role");
		
		JSONObject jo = new JSONObject();
		jo.put("user_account", user_account);
		jo.put("user_name", user_name);
		jo.put("user_role", user_role);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
	}
	
	//从excel读取用户信息
	public void AddMutiplyUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_info = request.getParameter("user_info");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		JSONArray ja = JSONArray.fromObject(user_info);
		JSONArray add_ja = new JSONArray();
		
		for(int i = 0; i < ja.size(); i ++) {
			
			JSONObject jo = ja.getJSONObject(i);
			
			String user_name = jo.getString("姓名");
			String u_account = jo.getString("学号");
			String user_role = jo.getString("角色");
			String user_class = jo.getString("班级");
			String user_oinfo = jo.getString("备注信息");
			
			String role = "2";
			
			if(user_role.equals("老师"))  role = "1";
			
			
			user.setUserName(user_name);
			user.setUserAccount(u_account);
			user.setUserRole(role);
			user.setStudentClassroom(user_class);
			user.setUserInfo(user_oinfo);
			
			JSONObject add_jo = JSONObject.fromObject(user);
			add_ja.add(add_jo);
		}
		
		List<User> UserList = JSONArray.toList(add_ja,User.class);
		UserService.AddMutiplyUser(UserList);
		
	}
	
}
