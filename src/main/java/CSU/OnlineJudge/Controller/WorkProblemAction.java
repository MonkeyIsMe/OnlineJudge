package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.WorkProblem;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Service.WorkProblemService;
import CSU.OnlineJudge.Service.Impl.ProblemServicempl;
import CSU.OnlineJudge.Service.Impl.WorkProblemServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkProblemAction extends ActionSupport{
	
	private ProblemService ps;
	private WorkProblemService wps;
	private WorkProblem wp = new WorkProblem();
	
	public ProblemService getPs() {
		return ps;
	}

	public void setPs(ProblemService ps) {
		this.ps = ps;
	}

	public WorkProblemService getWps() {
		return wps;
	}

	public void setWps(WorkProblemService wps) {
		this.wps = wps;
	}

	//根据作业编号查询题目
	public void QueryPublicProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		
		int wid = Integer.valueOf(work_id);
		
		JSONArray ja = new JSONArray();
		
		List<WorkProblem> WorkProblemList = wps.QueryWorkProblemByWorkId(wid);
		for(WorkProblem wpl :WorkProblemList) {
			int pid = wpl.getProblemId();
			Problem problem = ps.QueryProblem(pid);
			JSONObject jo = JSONObject.fromObject(problem);
			ja.add(jo);
		}
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//添加作业-考试和题目的关联
	public void AddWorkProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String problem_id = request.getParameter("problem_id");
		
		int wid = Integer.valueOf(work_id);
		int pid = Integer.valueOf(problem_id);
		
		wp.setProblemId(pid);
		wp.setWorkId(wid);
		
		wps.addWorkProblem(wp);
		
	}
	
	//删除作业-考试和题目的关联
	public void DeleteWorkProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String workproblem_id = request.getParameter("workproblem_id");
		
		int wpid = Integer.valueOf(workproblem_id);
		
		wp = wps.queryWorkProblem(wpid);
		
		if(wp == null) {
			out.println("Fail");
			out.flush(); 
			out.close(); 
			return ;
		}
		
		wps.deleteWorkProblem(wp);
		
	}
}
