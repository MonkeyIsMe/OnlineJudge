package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Service.CaseService;
import CSU.OnlineJudge.Service.Impl.CaseServiceImpl;
import net.sf.json.JSONArray;

public class CaseAction extends ActionSupport{
	
	private CaseService cs = new CaseServiceImpl();
	private Case cas = new Case();
	
	//添加样例
	public void AddCase() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String case_flag = request.getParameter("case_flag");
		String problem_id = request.getParameter("problem_id");
		String case_input = request.getParameter("case_input");
		String case_output = request.getParameter("case_output");
		
		int cFlag = Integer.valueOf(case_flag);
		int pid = Integer.valueOf(problem_id);
		
		cas.setCaseFlag(cFlag);
		cas.setCaseInput(case_input);
		cas.setCaseOutput(case_output);
		cas.setProblemId(pid);
		
		cs.AddCase(cas);
	}
	
	//删除样例
	public void DeleteCase() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String case_id = request.getParameter("case_id");
		
		int cid = Integer.valueOf(case_id);
		cas = cs.QueryCase(cid);
		
		if(cas == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		cs.DeleteCase(cas);
		
	}
	
	//更新样例
	public void UpdateCase() throws Exception{
	
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String case_id = request.getParameter("case_id");
		String case_flag = request.getParameter("case_flag");
		String problem_id = request.getParameter("problem_id");
		String case_input = request.getParameter("case_input");
		String case_output = request.getParameter("case_output");
		
		int cFlag = Integer.valueOf(case_flag);
		int pid = Integer.valueOf(problem_id);
		int cid = Integer.valueOf(case_id);
		cas = cs.QueryCase(cid);
		if(cas == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		cas.setCaseFlag(cFlag);
		cas.setCaseInput(case_input);
		cas.setCaseOutput(case_output);
		cas.setProblemId(pid);
		
		cs.UpdateCase(cas);
		
	}
	
	//查询题目题面样例
	public void QueryInCase() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		
		List<Case> CaseList = cs.GetCaseByFlag(pid, 0);
		
		JSONArray ja = JSONArray.fromObject(CaseList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	
	//查询题目测试样例
	public void QueryTestCase() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		
		List<Case> CaseList = cs.GetCaseByFlag(pid, 1);
		
		JSONArray ja = JSONArray.fromObject(CaseList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
		
	}
	
	//查询题目所有样例
	public void QueryAllCase() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		
		List<Case> CaseList = cs.GetProblemCase(pid);
		
		JSONArray ja = JSONArray.fromObject(CaseList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
		
	}
}
