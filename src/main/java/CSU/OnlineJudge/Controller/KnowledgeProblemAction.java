package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Knowledge;
import CSU.OnlineJudge.Model.KnowledgeProblem;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Service.KnowledgeProblemService;
import CSU.OnlineJudge.Service.KnowledgeService;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Service.Impl.KnowledgeProblemServiceImpl;
import CSU.OnlineJudge.Service.Impl.KnowledgeServiceImpl;
import CSU.OnlineJudge.Service.Impl.ProblemServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KnowledgeProblemAction extends ActionSupport{
	
	private KnowledgeProblemService KnowledgeProblemService;
	private KnowledgeService KnowledgeService;
	private KnowledgeProblem kp = new KnowledgeProblem();
	private ProblemService ProblemService;
	private Problem problem = new Problem();
	


	public KnowledgeProblemService getKnowledgeProblemService() {
		return KnowledgeProblemService;
	}

	public void setKnowledgeProblemService(KnowledgeProblemService knowledgeProblemService) {
		KnowledgeProblemService = knowledgeProblemService;
	}

	//添加知识点题目关联
	public void AddKnowledgeProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_id = request.getParameter("knowledge_id");
		String problem_id = request.getParameter("problem_id");
		
		int kid = Integer.valueOf(knowledge_id);
		int pid = Integer.valueOf(problem_id);
		
		kp.setKnowledgeId(kid);
		kp.setProblemId(pid);
		
		KnowledgeProblemService.addKnowledgeProblem(kp);
		
	}
	
	//删除知识点题目关联
	public void DeleteKnowledgeProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledgeproblem_id = request.getParameter("knowledgeproblem_id");
		
		int kpid = Integer.valueOf(knowledgeproblem_id);
		
		kp = KnowledgeProblemService.queryKnowledgeProblem(kpid);
		if(kp == null) {
			out.println("Fail");
			out.flush(); 
			out.close(); 
			return ;
		}
		
		KnowledgeProblemService.deleteKnowledgeProblem(kp);
	}
	
	//查询题目关联的知识点
	public void QueryKnowledgeByProblemId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		
		JSONArray ja = new JSONArray();
		
		List<KnowledgeProblem> KnowledgeProblemList = KnowledgeProblemService.queryKnowledgeProblemByProblemId(pid);
		for(KnowledgeProblem know : KnowledgeProblemList) {
			int kid = know.getKnowledgeId();
			Knowledge knowledge = KnowledgeService.queryKnowledge(kid);
			JSONObject jo = JSONObject.fromObject(knowledge);
			ja.add(jo);
		}
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//查询知识点关联的题目
	public void QueryProblemByKnowledgeId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_id = request.getParameter("knowledge_id");
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int rows = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		JSONArray ja = new JSONArray();
		
		if(knowledge_id.equals("none")) {
		
			List<Problem> ProblemList = ProblemService.GetProblemByPageSize(rows, PageSize);
			ja = JSONArray.fromObject(ProblemList);
			
		}
		else {
			int kid = Integer.valueOf(knowledge_id);
			
			List<KnowledgeProblem> KnowledgeProblemList = KnowledgeProblemService.queryKnowledgeProblemByKnowledgePageSize(kid, rows, PageSize);
			for(KnowledgeProblem pro : KnowledgeProblemList) {
				int pid = pro.getKnowledgeId();
				Problem problem = ProblemService.QueryProblem(pid);
				JSONObject jo = JSONObject.fromObject(problem);
				ja.add(jo);
			}
		}

		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
}
