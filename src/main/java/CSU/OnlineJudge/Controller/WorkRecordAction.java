package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.WorkRecord;
import CSU.OnlineJudge.Service.WorkRecordService;
import CSU.OnlineJudge.Service.Impl.WorkRecordServiceImpl;

public class WorkRecordAction extends ActionSupport{

	private WorkRecord wr = new WorkRecord();
	private WorkRecordService WorkRecordService;
	

	public WorkRecordService getWorkRecordService() {
		return WorkRecordService;
	}


	public void setWorkRecordService(WorkRecordService workRecordService) {
		WorkRecordService = workRecordService;
	}


	//添加考试，作业的题目记录
	public void AddWorkRecord() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String problem_id = request.getParameter("problem_id");
		
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
		
		int submission = Integer.valueOf(0);
		int accept = Integer.valueOf(0);
		int wrong = Integer.valueOf(0);
		int rte = Integer.valueOf(0);
		int tle = Integer.valueOf(0);
		int ce = Integer.valueOf(0);
		int wid = Integer.valueOf(work_id);
		int pid = Integer.valueOf(problem_id);
		
		wr.setTimeLimitTimes(tle);
		wr.setCompileErrorTimes(ce);
		wr.setRuntimeErrorTimes(rte);
		wr.setAcceptTimes(accept);
		wr.setCompileErrorTimes(ce);
		wr.setSubmitNumber(submission);
		wr.setWorkId(wid);
		wr.setProblemId(pid);

		WorkRecordService.addWorkRecord(wr);
		
	}
	
}
