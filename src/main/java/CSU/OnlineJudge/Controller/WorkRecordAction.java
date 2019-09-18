package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.WorkRecord;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Service.WorkRecordService;
import CSU.OnlineJudge.Service.Impl.WorkRecordServiceImpl;
import net.sf.json.JSONObject;

public class WorkRecordAction extends ActionSupport{

	private WorkRecord wr = new WorkRecord();
	private Problem problem = new Problem();
	private WorkRecordService WorkRecordService;
	private ProblemService ProblemService;
	

	public WorkRecordService getWorkRecordService() {
		return WorkRecordService;
	}


	public void setWorkRecordService(WorkRecordService workRecordService) {
		WorkRecordService = workRecordService;
	}
	public ProblemService getProblemService() {
		return ProblemService;
	}


	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}

	
	//根据考试作业编号分页查询总数
	public void CountByWorkId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		int count = WorkRecordService.CountWorkRecordByWorkId(wid);
		
		JSONObject jo = new JSONObject();
		jo.put("WorkRecordCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//根据考试作业编号分页查询数据
	public void QueryByWorkIdPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		List<WorkRecord> wr_list = WorkRecordService.QueryWorkRecordByPageSizeWithWorkId(row, PageSize, wid);
		
		out.println(wr_list.toString());
	    out.flush(); 
	    out.close();
	}
}
