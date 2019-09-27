package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.Submission;
import CSU.OnlineJudge.Service.CaseService;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Service.SubmissionService;
import CSU.OnlineJudge.Service.Impl.SubmissionServiceImpl;
import CSU.OnlineJudge.Utils.DateUtil;
import CSU.OnlineJudge.Utils.JudgeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SubmissionAction extends ActionSupport{

	private Submission submission = new Submission();
	private SubmissionService SubmissionService;
	private Problem problem = new Problem();
	private ProblemService ProblemService;
	private CaseService CaseService;
	private Case Case = new Case();

	public SubmissionService getSubmissionService() {
		return SubmissionService;
	}


	public void setSubmissionService(SubmissionService submissionService) {
		SubmissionService = submissionService;
	}

	
	public ProblemService getProblemService() {
		return ProblemService;
	}


	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}

	public CaseService getCaseService() {
		return CaseService;
	}


	public void setCaseService(CaseService caseService) {
		CaseService = caseService;
	}


	//增加一个提交的记录
	public void AddSubmission() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		DateUtil du = new DateUtil();
		
		String user_account = request.getParameter("user_account");
		String submission_type = request.getParameter("submission_type");
		String problem_id = request.getParameter("problem_id");
		String submission_code = request.getParameter("submission_code");
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		System.out.println(submission_type);
		int pid = Integer.valueOf(problem_id);
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		problem = ProblemService.QueryProblem(pid);
		
		submission.setSubmissionTime(du.GetNowDate());
		submission.setCodeLength(submission_code.length());
		submission.setCodeMemory(problem.getProblemMemory());
		submission.setCodeTime(problem.getProblemTimeLimit());
		submission.setUserAccount(user_account);
		submission.setCodeType(submission_type);
		submission.setProblemId(pid);
		submission.setSubmissionCode(submission_code);
		int sid = SubmissionService.addSubmission(submission);
		
		JSONObject jo = new JSONObject();
		jo.put("SubmissionId", sid);
		out.println(jo.toString());
		out.flush(); 
		out.close();
	}
	
	//获得提交的结果
	public void GetSubmissionResult() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String submission_id = request.getParameter("submission_id");
		
		if(submission_id == null || submission_id == "" || submission_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		int sid = Integer.valueOf(submission_id);
		
		submission = SubmissionService.querySubmission(sid);
		
		int pid = submission.getProblemId();
		problem = ProblemService.QueryProblem(pid);
		int time = problem.getTimeLimitTimes();
		int memory = problem.getProblemMemory();
		String lang = submission.getCodeType();
		String code = submission.getSubmissionCode();
		
		if(lang.equals("cpp")) {
			lang = "CPP";
		}
		else if(lang.equals("class")) {
			lang = "JAVA8";
		}
		
    	JSONArray caseja = new JSONArray();
    	JSONObject casejo = new JSONObject();
		List<Case> clist = CaseService.GetCaseByFlag(pid, 1);
		for(int i = 0; i < clist.size(); i ++) {
			Case c = clist.get(i);
    		casejo.put("stdin",c.getCaseInput());
    		casejo.put("stdout",c.getCaseOutput());
    		//System.out.println(casejo.toString());
    		caseja.add(casejo);
		}
		
		JudgeUtil judger = new JudgeUtil();
		String json = judger.DealCase(caseja.toString(), lang, time, memory, code);
		String result_str = judger.Judger(json);
		
		JSONObject result_ja = JSONObject.fromObject(result_str);
		String result = result_ja.getString("result");
		String result_time = result_ja.getString("time");
		String result_memory = result_ja.getString("memory");
		
		submission.setCodeMemory(Integer.valueOf(result_memory));
		submission.setCodeTime(Integer.valueOf(result_time));
		submission.setSubmissionResult(result);
		SubmissionService.updateSubmission(submission);
		
		
	}
	
	//rejudge
	public void UpdateSubmission() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String submission_id = request.getParameter("submission_id");
		
		if(submission_id == null || submission_id == "" || submission_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		DateUtil du = new DateUtil();
		int sid = Integer.valueOf(submission_id);
		
		submission = SubmissionService.querySubmission(sid);
		
		submission.setSubmissionTime(du.GetNowDate());
		SubmissionService.updateSubmission(submission);

	}
	
	//查询指定提交
	public void QuerySingleSubmission() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String submission_id = request.getParameter("submission_id");
		
		if(submission_id == null || submission_id == "" || submission_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int sid = Integer.valueOf(submission_id);
		
		submission = SubmissionService.querySubmission(sid);
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
		
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSize(row, PageSize);
		
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
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithUserAccount(row, PageSize, user_account);
		
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
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithResult(row, PageSize, submission_result);
		
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
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithProblemId(row, PageSize, pid);
		
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
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithUserAccountResult(row, PageSize, user_account, submission_result);
		
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
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithUserAccountProblem(row, PageSize, user_account, pid);
		
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
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int pid = Integer.valueOf(problem_id);
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithProblemIdResult(row, PageSize, pid, submission_result);
		
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
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithUserAccountResultProblem(row, PageSize, user_account, submission_result, pid);
		
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//判题
	public void JudgerCode() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String submission_id = request.getParameter("submission_id");
		
		if(submission_id == null || submission_id == "" || submission_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int sid = Integer.valueOf(submission_id);
		
	}
	
	
	//根据作业考试分页查询所有提交
	public void QuerySubmissionByWork() throws Exception{
			
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
			
		List<Submission> SubList = SubmissionService.QuerySubmissionByPageSizeWithWorkId(row, PageSize, wid);
			
		JSONArray ja = JSONArray.fromObject(SubList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
			
		}
}
