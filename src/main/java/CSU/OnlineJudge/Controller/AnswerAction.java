package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Answer;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Service.AnswerService;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Utils.DateUtil;
import CSU.OnlineJudge.Utils.HtmlUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AnswerAction extends ActionSupport{

	private AnswerService AnswerService;
	private ProblemService ProblemService;
	private Answer answer = new Answer();
	private Problem problem;
	
	public AnswerService getAnswerService() {
		return AnswerService;
	}
	public void setAnswerService(AnswerService answerService) {
		AnswerService = answerService;
	}
	public ProblemService getProblemService() {
		return ProblemService;
	}
	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}
	
	//添加一个题解
	public void AddAnswer() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		DateUtil du = new DateUtil();
		
		String answer_info = request.getParameter("answer_info");
		String answer_name = request.getParameter("answer_name");
		String problem_id = request.getParameter("problem_id");
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
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

		
		int pid = Integer.valueOf(problem_id);
		problem = ProblemService.QueryProblem(pid);
		
		answer.setAnswerInfo(answer_info);
		answer.setUserAccount(useraccount);
		answer.setProblemId(pid);
		answer.setAnswerName(answer_name);
		answer.setAnswerTime(du.GetNowDate());
		answer.setProblemName(problem.getProblemName());
		
		AnswerService.AddAnswer(answer);
		
	}
	
	//删除一个题解
	public void DeleteAnswer() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String answer_id = request.getParameter("answer_id");
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(answer_id == null || answer_id == "" || answer_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int aid = Integer.valueOf(answer_id);
		
		answer = AnswerService.QueryAnswer(aid);
		
		if(answer == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		AnswerService.DeleteAnswer(answer);
		
	}
	
	
	//查询一个题解
	public void QuerySingleAnswer() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String answer_id = request.getParameter("answer_id");
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(answer_id == null || answer_id == "" || answer_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int aid = Integer.valueOf(answer_id);
		
		answer = AnswerService.QueryAnswer(aid);
		
		if(answer == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(answer);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//通过题目id查询题解
	public void QueryAnswerByProblemId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
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
		
		List<Answer> AnswerList = AnswerService.QueryAnswerByProblemIdPageSize(pid, row, PageSize);
		//System.out.println(AnswerList.size());
		JSONArray ja = JSONArray.fromObject(AnswerList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//分页查询所有题解
	public void QueryAnswerPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		List<Answer> AnswerList = AnswerService.QueryAnswerByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(AnswerList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//查询题解数目
	public void CountAnswer() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int count = AnswerService.CountAnswer();
		
		JSONObject jo = new JSONObject();
		jo.put("AnswerCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//根据题目查询题解数目
	public void CountAnswerByProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		String problem_id = request.getParameter("problem_id");
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		int count = AnswerService.CountAnswerByProblem(pid);
		
		JSONObject jo = new JSONObject();
		jo.put("AnswerCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//清空某道题目的全部题解
	public void ClearAnswerByProblemId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		String problem_id = request.getParameter("problem_id");
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		List<Answer> AnswerList = AnswerService.QueryAnswerByProblemId(pid);
		
		AnswerService.DeleteMutiplyAnswer(AnswerList);
		
	}
	
}
