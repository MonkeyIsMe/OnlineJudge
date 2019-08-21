package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.ProblemResult;
import CSU.OnlineJudge.Service.ProblemResultService;
import net.sf.json.JSONArray;

public class ProblemResultAction extends ActionSupport{
	
	private ProblemResultService ProblemResultService;
	private ProblemResult pr = new ProblemResult();
	
	
	public ProblemResultService getProblemResultService() {
		return ProblemResultService;
	}


	public void setProblemResultService(ProblemResultService problemResultService) {
		ProblemResultService = problemResultService;
	}


	//添加一个题目用户结果的关联
	public void AddProblemResult() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		String problem_result = request.getParameter("problem_result");
		String user_account = request.getParameter("user_account");
		
		int pid = Integer.valueOf(problem_id);
		
		pr.setProblemId(pid);
		pr.setProblemResult(problem_result);
		pr.setUserAccount(user_account);
		
		ProblemResultService.AddProblemResult(pr);
		
	}
	
	
	//根据用户查询所有题目
	public void QueryProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_account = request.getParameter("user_account");
		
		List<ProblemResult> pr_list = ProblemResultService.QueryProblemResultByAccount(user_account);
		
		JSONArray ja = JSONArray.fromObject(pr_list);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户题目的结果更新
	public void UpdateByResult() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		String problem_result = request.getParameter("problem_result");
		String user_account = request.getParameter("user_account");
		
		int pid = Integer.valueOf(problem_id);
		
		List<ProblemResult> pr_list = ProblemResultService.QueryProblemResultByProblemAccount(pid, user_account);
		
		if(pr_list.size() == 0) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		pr = pr_list.get(0);
		pr.setProblemResult(problem_result);
		
		ProblemResultService.UpdateProblemResult(pr);
		
	}
	
	//根据用户查询所有不正确题目
	public void QueryOtherProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_account = request.getParameter("user_account");
		//String user_account = "AC";
		
		List<ProblemResult> pr_list = ProblemResultService.QueryProblemResultByResultAccount("Other", user_account);
		
		JSONArray ja = JSONArray.fromObject(pr_list);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	
	//根据用户查询所有正确题目
	public void QueryAcceptProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_account = request.getParameter("user_account");
		//String user_account = "AC";
		
		List<ProblemResult> pr_list = ProblemResultService.QueryProblemResultByResultAccount("AC", user_account);
		
		JSONArray ja = JSONArray.fromObject(pr_list);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	

}
