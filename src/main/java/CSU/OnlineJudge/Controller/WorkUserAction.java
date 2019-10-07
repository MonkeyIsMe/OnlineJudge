package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.WorkUser;
import CSU.OnlineJudge.Service.WorkUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkUserAction extends ActionSupport{

	private WorkUser wu = new WorkUser();
	private WorkUserService WorkUserService;
	
	public WorkUserService getWorkUserService() {
		return WorkUserService;
	}
	public void setWorkUserService(WorkUserService workUserService) {
		WorkUserService = workUserService;
	}
	
	// 添加一个记录
	public void AddWorkUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		String work_id = request.getParameter("work_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		wu.setWorkId(wid);
		
		WorkUserService.AddWorkUser(wu);
	}
	
	// 更新一个记录
	public void UpdateWorkUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		String wu_id = request.getParameter("wu_id");
		String result = request.getParameter("result");
		
		if(wu_id == null || wu_id == "" || wu_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wuid = Integer.valueOf(wu_id);
		
		wu = WorkUserService.QueryWorkUser(wuid);
		
		if(wu == null) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		if(result.equals("AC")) {
			int num = wu.getAcceptTimes();
			wu.setAcceptTimes(num++);
		}
		else if(result.equals("WA")) {
			int num = wu.getWrongAnswerTimes();
			wu.setWrongAnswerTimes(num++);
		}
		else if(result.equals("CE")) {
			int num = wu.getCompileErrorTimes();
			wu.setCompileErrorTimes(num++);
		}
		else if(result.equals("RTE")) {
			int num = wu.getRuntimeErrorTimes();
			wu.setRuntimeErrorTimes(num++);
		}
		else if(result.equals("TLE")) {
			int num = wu.getTimeLimitTimes();
			wu.setTimeLimitTimes(num++);
		}
		int submission = wu.getSubmissionTimes();
		submission ++;
		wu.setSubmissionTimes(submission);
		WorkUserService.UpdateWorkUser(wu);
	}
	
	// 根据作业考试编号查询总数
	public void CountWorkUser() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		String work_id = request.getParameter("work_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		int count = WorkUserService.CountWorkUser(wid);
		
		JSONObject jo = new JSONObject();
		jo.put("WorkUserCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
	}
	
	// 根据作业考试编号分页查询记录
	public void QueryWorkUserByPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
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
		
		List<WorkUser> Result = WorkUserService.QueryWorkUserByPageSize(row, PageSize,wid);
		
		JSONArray ja = JSONArray.fromObject(Result);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	}
	
}
