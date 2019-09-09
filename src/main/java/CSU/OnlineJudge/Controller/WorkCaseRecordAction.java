package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.WorkCaseRecord;
import CSU.OnlineJudge.Service.WorkCaseRecordService;

public class WorkCaseRecordAction extends ActionSupport{

	private WorkCaseRecordService WorkCaseRecordService;
	private WorkCaseRecord wcr = new WorkCaseRecord();
	
	public WorkCaseRecordService getWorkCaseRecordService() {
		return WorkCaseRecordService;
	}
	public void setWorkCaseRecordService(WorkCaseRecordService workCaseRecordService) {
		WorkCaseRecordService = workCaseRecordService;
	}
	
	//添加一个学生针对每一个样例的记录
	public void AddWorkCaseRecord() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String problem_id = request.getParameter("problem_id");
		String case_id = request.getParameter("case_id");
		String test_result = request.getParameter("test_result");		
		String aim_input = request.getParameter("aim_input");
		String aim_output = request.getParameter("aim_output");
		String test_output = request.getParameter("test_output");
		String user_account = request.getParameter("user_account");
		
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
		
		int wid = Integer.valueOf(work_id);
		int pid = Integer.valueOf(problem_id);
		
		wcr.setWorkId(wid);
		wcr.setProblemId(pid);
		wcr.setCaseResult(test_result);
		wcr.setAimInput(aim_input);
		wcr.setAimOutput(aim_output);
		wcr.setTestOutput(test_output);
		wcr.setUserAccount(user_account);
		
		WorkCaseRecordService.addWorkCaseRecord(wcr);
		
	}
	
}
