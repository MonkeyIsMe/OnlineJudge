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
import CSU.OnlineJudge.Service.WorkService;
import CSU.OnlineJudge.Service.Impl.ProblemServiceImpl;
import CSU.OnlineJudge.Service.Impl.WorkProblemServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkProblemAction extends ActionSupport{
	
	private ProblemService ProblemService;
	private WorkProblemService WorkProblemService;
	private WorkProblem wp = new WorkProblem();
	
	

	public ProblemService getProblemService() {
		return ProblemService;
	}

	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}

	public WorkProblemService getWorkProblemService() {
		return WorkProblemService;
	}

	public void setWorkProblemService(WorkProblemService workProblemService) {
		WorkProblemService = workProblemService;
	}

	//根据作业编号查询题目
	public void QueryPublicProblem() throws Exception{
		
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
		
		JSONArray ja = new JSONArray();
		
		List<WorkProblem> WorkProblemList = WorkProblemService.QueryWorkProblemByWorkId(wid);
		for(WorkProblem wpl :WorkProblemList) {
			int pid = wpl.getProblemId();
			Problem problem = ProblemService.QueryProblem(pid);
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
		
		wp.setProblemId(pid);
		wp.setWorkId(wid);
		
		WorkProblemService.addWorkProblem(wp);
		
	}
	
	//更新作业-考试和题目的关联
	public void UpdateWorkProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String problem_info = request.getParameter("problem_info");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		List<WorkProblem> wp_list = WorkProblemService.QueryWorkProblemByWorkId(wid);
		WorkProblemService.DeleteMutiplyWorkProblem(wp_list);
		
		JSONArray ja = JSONArray.fromObject(problem_info);
		JSONArray add_ja = new JSONArray();
		
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String ProblemId = jo.getString("ProblemId");
			int pid = Integer.valueOf(ProblemId);
			wp.setWorkId(wid);
			wp.setProblemId(pid);
			
			JSONObject wpjo = JSONObject.fromObject(wp);
			add_ja.add(wpjo);
		}
		
		List<WorkProblem> add_wp_list = JSONArray.toList(add_ja,WorkProblem.class);
		WorkProblemService.AddMutiplyWorkProblem(add_wp_list);
		
		out.println("Success");
		out.flush(); 
		out.close(); 
	}
	
	//添加作业-考试和多个题目的关联
	public void AddManyWorkProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String problem_info = request.getParameter("problem_info");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		JSONArray ja = JSONArray.fromObject(problem_info);
		JSONArray add_ja = new JSONArray();
		int wid = Integer.valueOf(work_id);
		//System.out.println(problem_info);
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String ProblemId = jo.getString("ProblemId");
			int pid = Integer.valueOf(ProblemId);
			wp.setWorkId(wid);
			wp.setProblemId(pid);
			
			JSONObject wpjo = JSONObject.fromObject(wp);
			add_ja.add(wpjo);
		}
		
		List<WorkProblem> wp_list = JSONArray.toList(add_ja,WorkProblem.class);
		WorkProblemService.AddMutiplyWorkProblem(wp_list);
		
		out.println("Success");
		out.flush(); 
		out.close(); 
		
	}
	
	//删除作业-考试和题目的关联
	public void DeleteWorkProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String workproblem_id = request.getParameter("workproblem_id");
		
		if(workproblem_id == null || workproblem_id == "" || workproblem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wpid = Integer.valueOf(workproblem_id);
		
		wp = WorkProblemService.queryWorkProblem(wpid);
		
		if(wp == null) {
			out.println("Fail");
			out.flush(); 
			out.close(); 
			return ;
		}
		
		WorkProblemService.deleteWorkProblem(wp);
		
	}
}
