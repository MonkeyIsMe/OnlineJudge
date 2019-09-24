package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Model.WorkUserRecord;
import CSU.OnlineJudge.Service.UserService;
import CSU.OnlineJudge.Service.WorkUserRecordService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkUserRecordAction extends ActionSupport{

	private WorkUserRecordService WorkUserRecordService;
	private WorkUserRecord wur = new WorkUserRecord();
	private UserService UserService;
	private User user = new User();
	
	public WorkUserRecordService getWorkUserRecordService() {
		return WorkUserRecordService;
	}
	public void setWorkUserRecordService(WorkUserRecordService workUserRecordService) {
		WorkUserRecordService = workUserRecordService;
	}
	public UserService getUserService() {
		return UserService;
	}
	public void setUserService(UserService userService) {
		UserService = userService;
	}
	
	//增加记录
	public void AddWorkUserRecord() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String user_id = request.getParameter("user_id");
		String submission_result = request.getParameter("submission_result");
		String submission_time = request.getParameter("submission_time");
		String submission_code_length = request.getParameter("submission_code_length");
		String submission_code_memory = request.getParameter("submission_code_memory");
		String user_account = request.getParameter("user_account");
		String submission_type = request.getParameter("submission_type");
		String code_time = request.getParameter("code_time");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
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
		if(submission_code_memory == null || submission_code_memory == "" || submission_code_memory.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(submission_code_memory == null || submission_code_memory == "" || submission_code_memory.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(code_time == null || code_time == "" || code_time.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		int uid = Integer.valueOf(user_id);
		int clen = Integer.valueOf(submission_code_memory);
		int cmemory = Integer.valueOf(submission_code_memory);
		int ctime = Integer.valueOf(code_time);
		
		wur.setWorkId(wid);
		wur.setUserId(uid);
		wur.setSubmissionResult(submission_result);
		wur.setSubmissionTime(submission_time);
		wur.setCodeLength(clen);
		wur.setCodeMemory(cmemory);
		wur.setUserAccount(user_account);
		wur.setCodeType(submission_type);
		wur.setCodeTime(ctime);
		
		WorkUserRecordService.addWorkUserRecord(wur);
		
		
	}
	
	//根据题目编号查记录
	public void queryWorkUserRecordByPageSizeWithProblemId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_id = request.getParameter("problem_id");
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithProblemId(row, PageSize, pid);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}

	//根据用户账号查记录
	public void queryWorkUserRecordByPageSizeWithUserAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String user_account = request.getParameter("user_account");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithUserAccount(row, PageSize, user_account);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户作业查记录
	public void queryWorkUserRecordByPageSizeWithWorkId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String work_id = request.getParameter("work_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int wid = Integer.valueOf(work_id);
		
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithWorkId(row, PageSize, wid);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用题目，作业查记录
	public void queryWorkUserRecordByPageSizeWithProblemWork() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String work_id = request.getParameter("work_id");
		String problem_id = request.getParameter("problem_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int wid = Integer.valueOf(work_id);
		int pid = Integer.valueOf(problem_id);
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithProblemWork(row, PageSize, pid, wid);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户账号，作业查记录
	public void queryWorkUserRecordByPageSizeWithAccountWork() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String work_id = request.getParameter("work_id");
		String user_account = request.getParameter("user_account");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int wid = Integer.valueOf(work_id);
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithAccountWork(row, PageSize, user_account, wid);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//根据用户账号，题目，查记录
	public void queryWorkUserRecordByPageSizeWithProblemAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_id = request.getParameter("problem_id");
		String user_account = request.getParameter("user_account");
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithProblemAccount(row, PageSize, pid, user_account);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	
	//根据用户账号，题目，作业查记录
	public void queryWorkUserRecordByProblemWorkAccountPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String user_account = request.getParameter("user_account");
		String problem_id = request.getParameter("problem_id");
		String work_id = request.getParameter("work_id");
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		int wid = Integer.valueOf(work_id);
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByProblemWorkAccountPageSize(row, PageSize, pid, wid, user_account);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户编号作业查记录
	public void QueryWorkUserRecordByWorkUserIdPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String user_id = request.getParameter("user_id");
		String work_id = request.getParameter("work_id");
		
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int uid = Integer.valueOf(user_id);
		int wid = Integer.valueOf(work_id);
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithUserIdWork(row, PageSize, uid, wid);
		
		JSONArray ja = JSONArray.fromObject(ResultList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//根据作业查用户
	public void QueryUserInWorkUserRecordPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String work_id = request.getParameter("work_id");

		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		Set<User> set = new HashSet<User>();
		int wid = Integer.valueOf(work_id);
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		JSONArray ja = new JSONArray();
		List<WorkUserRecord> ResultList = WorkUserRecordService.queryWorkUserRecordByPageSizeWithWorkId(row, PageSize, wid);
		for(WorkUserRecord example :ResultList) {
			int uid = example.getUserId();
			user = UserService.queryUser(uid);
			if(user == null) continue;
			JSONObject jo = JSONObject.fromObject(user);
			set.add(user);
		}
		
		for(User u : set) {
			ja.add(u);
		}
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//根据作业,用户查数据总数
	public void CountRecordByWorkUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String user_id = request.getParameter("user_id");

		if(work_id == null || work_id == "" || work_id.equals("")) {
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
		int wid = Integer.valueOf(work_id);
		
		int count = WorkUserRecordService.CountWorkUserRecordWithUserIdWorkId(uid, wid);
		JSONObject jo = new JSONObject();
		jo.put("WorkUserRecordCount", count);
		
		out.println(jo.toString());
        out.flush(); 
        out.close();
	}

	//根据记录编号查记录
	public void QuerySingleRecord() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String record_id = request.getParameter("record_id");

		if(record_id == null || record_id == "" || record_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int rid = Integer.valueOf(record_id);
		
		wur = WorkUserRecordService.queryWorkUserRecord(rid);
		JSONObject jo = JSONObject.fromObject(wur);
	
		out.println(jo.toString());
        out.flush(); 
        out.close();
	}
	
	//根据作业查数据总数
	public void CountRecordByWork() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");

		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		int count = WorkUserRecordService.CountWorkUserRecordWithWorkId(wid);
		JSONObject jo = new JSONObject();
		jo.put("WorkUserRecordCount", count);
		
		out.println(jo.toString());
        out.flush(); 
        out.close();
	}
}
