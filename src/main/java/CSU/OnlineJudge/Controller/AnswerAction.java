package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Answer;
import CSU.OnlineJudge.Service.AnswerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AnswerAction extends ActionSupport{

	private AnswerService AnswerService;
	private Answer answer = new Answer();
	
	public AnswerService getAnswerService() {
		return AnswerService;
	}
	public void setAnswerService(AnswerService answerService) {
		AnswerService = answerService;
	}
	
	//添加一个题解
	public void AddAnswer() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String answer_info = request.getParameter("answer_info");
		String user_account = request.getParameter("user_account");
		String problem_id = request.getParameter("problem_id");
		String answer_time = request.getParameter("answer_time");
		
		int pid = Integer.valueOf(problem_id);
		
		answer.setAnswerInfo(answer_info);
		answer.setUserAccount(user_account);
		answer.setProblemId(pid);
		answer.setAnswerTime(answer_time);
		
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
	
	//更新一个题解
	public void UpdateAnswer() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String answer_info = request.getParameter("answer_info");
		String user_account = request.getParameter("user_account");
		String problem_id = request.getParameter("problem_id");
		String answer_time = request.getParameter("answer_time");
		String answer_id = request.getParameter("answer_id");
		
		int aid = Integer.valueOf(answer_id);
		
		answer = AnswerService.QueryAnswer(aid);
		
		if(answer == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		answer.setAnswerInfo(answer_info);
		answer.setUserAccount(user_account);
		answer.setProblemId(pid);
		answer.setAnswerTime(answer_time);
		
		AnswerService.UpdateAnswer(answer);
		
	}
	
	//查询一个题解
	public void QuerySingleAnswer() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String answer_id = request.getParameter("answer_id");
		
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
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		List<Answer> AnswerList = AnswerService.QueryAnswerByProblemIdPageSize(pid, row, PageSize);
		
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
		
		int count = AnswerService.CountAnswer();
		
		JSONObject jo = new JSONObject();
		jo.put("AnswerCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
}
