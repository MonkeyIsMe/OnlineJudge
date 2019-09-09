package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.WorkUserRecord;
import CSU.OnlineJudge.Service.WorkUserRecordService;
import net.sf.json.JSONArray;

public class WorkUserRecordAction extends ActionSupport{

	private WorkUserRecordService WorkUserRecordService;
	private WorkUserRecord wur = new WorkUserRecord();
	
	public WorkUserRecordService getWorkUserRecordService() {
		return WorkUserRecordService;
	}
	public void setWorkUserRecordService(WorkUserRecordService workUserRecordService) {
		WorkUserRecordService = workUserRecordService;
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
	
	//根据用户题目查记录
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
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
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
	
}
