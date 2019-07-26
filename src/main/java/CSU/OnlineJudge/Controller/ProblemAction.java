package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Service.Impl.ProblemServicempl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProblemAction extends ActionSupport{
	
	private ProblemService ps = new ProblemServicempl();
	private Problem problem = new Problem();
	
	//删除题目
	public void DeleteProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		Problem problem = ps.QueryProblem(pid);
		
		if(problem == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		ps.DeleteProblem(problem);
		
	}
	
	//添加题目
	public void AddProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_name = request.getParameter("problem_name");
		String problem_info = request.getParameter("problem_info");
		String problem_hint = request.getParameter("problem_hint");
		String problem_memory = request.getParameter("problem_memory");
		String problem_time = request.getParameter("problem_time");
		String problem_submission = request.getParameter("problem_submission");
		String problem_accept = request.getParameter("problem_accept");
		String problem_degree = request.getParameter("problem_degree");
		String problem_wrong = request.getParameter("problem_wrong");
		String problem_tle = request.getParameter("problem_tle");
		String problem_rte = request.getParameter("problem_rte");
		String problem_ce = request.getParameter("problem_ce");
		String problem_people = request.getParameter("problem_people");
		String problem_flag = request.getParameter("problem_flag");
		String problem_input = request.getParameter("problem_input");
		String problem_output = request.getParameter("problem_output");
		
		int memory = Integer.valueOf(problem_memory);
		int time = Integer.valueOf(problem_time);
		int submission = Integer.valueOf(problem_submission);
		int accept = Integer.valueOf(problem_accept);
		int wrong = Integer.valueOf(problem_wrong);
		int tle = Integer.valueOf(problem_tle);
		int rte = Integer.valueOf(problem_rte);
		int ce = Integer.valueOf(problem_ce);
		int IsPublic = Integer.valueOf(problem_flag);
		
		problem.setProblemName(problem_name);
		problem.setProblemInfo(problem_input);
		problem.setProblemHint(problem_hint);
		problem.setProblemMemory(memory);
		problem.setProblemTimeLimit(time);
		problem.setSubmissionTimes(submission);
		problem.setAcceptTimes(accept);
		problem.setProblemDegree(problem_degree);
		problem.setWrongAnswerTimes(wrong);
		problem.setTimeLimitTimes(tle);
		problem.setRuntimeErrorTimes(rte);
		problem.setCompileErrorTimes(ce);
		problem.setProblemPeople(problem_people);
		problem.setPublicOrNot(IsPublic);
		problem.setProblemInput(problem_input);
		problem.setProblemOutput(problem_output);
		
		ps.AddProblem(problem);
		
	}
	
	//更新题目
	public void UpdateProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		String problem_name = request.getParameter("problem_name");
		String problem_info = request.getParameter("problem_info");
		String problem_hint = request.getParameter("problem_hint");
		String problem_memory = request.getParameter("problem_memory");
		String problem_time = request.getParameter("problem_time");
		String problem_submission = request.getParameter("problem_submission");
		String problem_accept = request.getParameter("problem_accept");
		String problem_degree = request.getParameter("problem_degree");
		String problem_wrong = request.getParameter("problem_wrong");
		String problem_tle = request.getParameter("problem_tle");
		String problem_rte = request.getParameter("problem_rte");
		String problem_ce = request.getParameter("problem_ce");
		String problem_people = request.getParameter("problem_people");
		String problem_flag = request.getParameter("problem_flag");
		String problem_input = request.getParameter("problem_input");
		String problem_output = request.getParameter("problem_output");
		
		int memory = Integer.valueOf(problem_memory);
		int time = Integer.valueOf(problem_time);
		int submission = Integer.valueOf(problem_submission);
		int accept = Integer.valueOf(problem_accept);
		int wrong = Integer.valueOf(problem_wrong);
		int tle = Integer.valueOf(problem_tle);
		int rte = Integer.valueOf(problem_rte);
		int ce = Integer.valueOf(problem_ce);
		int IsPublic = Integer.valueOf(problem_flag);
		
		
		int pid = Integer.valueOf(problem_id);
		problem = ps.QueryProblem(pid);
		
		if(problem == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		problem.setProblemName(problem_name);
		problem.setProblemInfo(problem_input);
		problem.setProblemHint(problem_hint);
		problem.setProblemMemory(memory);
		problem.setProblemTimeLimit(time);
		problem.setSubmissionTimes(submission);
		problem.setAcceptTimes(accept);
		problem.setProblemDegree(problem_degree);
		problem.setWrongAnswerTimes(wrong);
		problem.setTimeLimitTimes(tle);
		problem.setRuntimeErrorTimes(rte);
		problem.setCompileErrorTimes(ce);
		problem.setProblemPeople(problem_people);
		problem.setPublicOrNot(IsPublic);
		problem.setProblemInput(problem_input);
		problem.setProblemOutput(problem_output);
		
		ps.UpdateProblem(problem);
		
	}
	
	//查询公开的题目
	public void QueryPublicProblem() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ps.GetFlagProblemByPageSize(row, PageSize, 1);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
		
	//查询不公开的题目
	public void QueryPrivateProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();	
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ps.GetFlagProblemByPageSize(row, PageSize, 0);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	}
		
	//查询所有的题目
	public void QueryAllProblem() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ps.QueryProblemByPageSize(row, PageSize);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	    }
		
	//查询单个的题目
	public void QuerySingleProblem() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String problem_id = request.getParameter("problem_id");
			
		int pid = Integer.valueOf(problem_id);
			
		Problem Problem = ps.QueryProblem(pid);
			
		JSONObject jo = JSONObject.fromObject(Problem);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
	}

	//根据名字模糊查询公开题目
	public void VaguePublicProblemByName() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_name = request.getParameter("problem_name");	
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ps.VagueByPageSizeWithFlagByName(row, PageSize, 1, problem_name);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	   }
	
	//根据名字模糊查询私有题目
	public void VaguePrivateProblemByName() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_name = request.getParameter("problem_name");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ps.VagueByPageSizeWithFlagByName(row, PageSize, 0, problem_name);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	   }
	
	//根据出题人查题目
	public void QueryProblemByWriter() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_people = request.getParameter("problem_people");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ps.VagueByPageSizeWithFlagByPeople(row, PageSize, problem_people);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	   }
}
