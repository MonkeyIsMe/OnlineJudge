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
	


	public KnowledgeService getKnowledgeService() {
		return KnowledgeService;
	}

	public void setKnowledgeService(KnowledgeService knowledgeService) {
		KnowledgeService = knowledgeService;
	}

	public ProblemService getProblemService() {
		return ProblemService;
	}

	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}

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
		
		if(knowledge_id == null || knowledge_id == "" || knowledge_id.equals("")) {
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
		
		if(knowledgeproblem_id == null || knowledgeproblem_id == "" || knowledgeproblem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		if(knowledge_id == null || knowledge_id == "" || knowledge_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
	
	
	//查询知识点关联的题目数量
	public void CountProblemByKnowledgeId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_id = request.getParameter("knowledge_id");

		if(knowledge_id == null || knowledge_id == "" || knowledge_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int count = 0;
		
		if(knowledge_id.equals("none")) {
		
			count = ProblemService.CountProblem();
			
		}
		else {
			int kid = Integer.valueOf(knowledge_id);
			count = KnowledgeProblemService.CountProblem(kid);
		}
		
		JSONObject jo = new JSONObject();
		jo.put("count", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//批量添加知识点题目关联
	public void AddMutiplyKnowledgeProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_info = request.getParameter("knowledge_info");
		String problem_id = request.getParameter("problem_id");

		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		List<KnowledgeProblem> de_list = KnowledgeProblemService.queryKnowledgeProblemByProblemId(pid);
		KnowledgeProblemService.DeleteMutiplyKnowledgeProblem(de_list);
		
		JSONArray add_ja = new JSONArray();
		JSONArray ja = JSONArray.fromObject(knowledge_info);
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String KnowledgeId = jo.getString("knowledgeId");
			int kid = Integer.valueOf(KnowledgeId);
			kp.setKnowledgeId(kid);
			kp.setProblemId(pid);
			JSONObject kpjo = JSONObject.fromObject(kp);
			add_ja.add(kpjo);
		}
		
		
		List<KnowledgeProblem> kp_list = JSONArray.toList(add_ja,KnowledgeProblem.class);
		KnowledgeProblemService.AddMutiplyKnowledgeProblem(kp_list);
		
	}
}
