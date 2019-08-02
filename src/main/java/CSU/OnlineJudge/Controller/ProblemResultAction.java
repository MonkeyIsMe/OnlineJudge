package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.ProblemResult;
import CSU.OnlineJudge.Service.ProblemResultService;

public class ProblemResultAction extends ActionSupport{
	
	private ProblemResultService ProblemResultService;
	private ProblemResult pr;
	
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
	

}
