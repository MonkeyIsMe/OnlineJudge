package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Submission;
import CSU.OnlineJudge.Service.SubmissionService;
import CSU.OnlineJudge.Service.Impl.SubmissionServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubmissionAction extends ActionSupport{

	private Submission submission =  null;
	private SubmissionService ss = new SubmissionServiceImpl();
	
	public void AddSubmission() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		String submission_result = request.getParameter("submission_result");
		String submit_time = request.getParameter("submit_time");
		String submission_length = request.getParameter("submission_length");
		String submission_time = request.getParameter("submission_time");
		String submission_memory = request.getParameter("submission_memory");
		String user_account = request.getParameter("user_account");
		String submission_type = request.getParameter("submission_type");
		String problem_id = request.getParameter("problem_id");
		
		int uid = Integer.valueOf(user_id);
		int pid = Integer.valueOf(problem_id);
		int length = Integer.valueOf(submission_length);
		int memory = Integer.valueOf(submission_memory);
		int time = Integer.valueOf(submission_time);
		
		submission.setStudentId(uid);
		submission.setSubmissionResult(submission_result);
		submission.setSubmissionTime(submit_time);
		submission.setCodeLength(length);
		submission.setCodeMemory(memory);
		submission.setCodeTime(time);
		submission.setUserAccount(user_account);
		submission.setCodeType(submission_type);
		submission.setProblemId(pid);
		
		ss.addSubmission(submission);
		
	}
	
	
	public void DeleteSubmission() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String submission_id = request.getParameter("submission_id");
		
		int sid = Integer.valueOf(submission_id);
		
		submission = ss.querySubmission(sid);
		if(submission == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		ss.deleteSubmission(submission);
		
	}
	
	
	//查询指定提交
	public void QuerySingleSubmission() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String submission_id = request.getParameter("submission_id");
		
		int sid = Integer.valueOf(submission_id);
		
		submission = ss.querySubmission(sid);
		if(submission == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(submission);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	
	//分页查询所有提交
	public void QuerySubmissionByPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		
		List<Submission> SubList = ss.QuerySubmissionByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//根据用户账号分页查询所有提交
	public void QuerySubmissionByAccount() throws Exception{
		
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
		
		List<Submission> SubList = ss.QuerySubmissionByPageSizeWithUserAccount(row, PageSize, user_account);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据结果分页查询所有提交
	public void QuerySubmissionByResult() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String submission_result = request.getParameter("submission_result");	
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Submission> SubList = ss.QuerySubmissionByPageSizeWithResult(row, PageSize, submission_result);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据题题目分页查询所有提交
	public void QuerySubmissionByProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_id = request.getParameter("problem_id");	
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<Submission> SubList = ss.QuerySubmissionByPageSizeWithProblemId(row, PageSize, pid);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户账号和结果分页查询所有提交
	public void QuerySubmissionByAccountResult() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String submission_result = request.getParameter("submission_result");
		String user_account = request.getParameter("user_account");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Submission> SubList = ss.QuerySubmissionByPageSizeWithUserAccountResult(row, PageSize, user_account, submission_result);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户账号和题目分页查询所有提交
	public void QuerySubmissionByAccountProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_id = request.getParameter("problem_id");
		String user_account = request.getParameter("user_account");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<Submission> SubList = ss.QuerySubmissionByPageSizeWithUserAccountProblem(row, PageSize, user_account, pid);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户结果和题目分页查询所有提交
	public void QuerySubmissionByResultProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_id = request.getParameter("problem_id");
		String submission_result = request.getParameter("submission_result");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<Submission> SubList = ss.QuerySubmissionByPageSizeWithProblemIdResult(row, PageSize, pid, submission_result);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户账号，题目和结果分页查询所有提交
	public void QuerySubmissionByAccountProblemResult() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String submission_result = request.getParameter("submission_result");
		String user_account = request.getParameter("user_account");
		String problem_id = request.getParameter("problem_id");	
		
		int pid = Integer.valueOf(problem_id);
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Submission> SubList = ss.QuerySubmissionByPageSizeWithUserAccountResultProblem(row, PageSize, user_account, submission_result, pid);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
}
