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
import CSU.OnlineJudge.Service.Impl.ProblemServicempl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KnowledgeProblemAction extends ActionSupport{
	
	private KnowledgeProblemService kps;
	private KnowledgeService ks;
	private KnowledgeProblem kp = new KnowledgeProblem();
	private ProblemService ps;
	
	
	public KnowledgeProblemService getKps() {
		return kps;
	}

	public void setKps(KnowledgeProblemService kps) {
		this.kps = kps;
	}

	public KnowledgeService getKs() {
		return ks;
	}

	public void setKs(KnowledgeService ks) {
		this.ks = ks;
	}

	public ProblemService getPs() {
		return ps;
	}

	public void setPs(ProblemService ps) {
		this.ps = ps;
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
		
		kps.addKnowledgeProblem(kp);
		
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
		
		kp = kps.queryKnowledgeProblem(kpid);
		if(kp == null) {
			out.println("Fail");
			out.flush(); 
			out.close(); 
			return ;
		}
		
		kps.deleteKnowledgeProblem(kp);
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
		
		List<KnowledgeProblem> KnowledgeProblemList = kps.queryKnowledgeProblemByProblemId(pid);
		for(KnowledgeProblem know : KnowledgeProblemList) {
			int kid = know.getKnowledgeId();
			Knowledge knowledge = ks.queryKnowledge(kid);
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
		
		int kid = Integer.valueOf(knowledge_id);
		
		JSONArray ja = new JSONArray();
		
		List<KnowledgeProblem> KnowledgeProblemList = kps.queryKnowledgeProblemByKnowledge(kid);
		for(KnowledgeProblem pro : KnowledgeProblemList) {
			int pid = pro.getKnowledgeId();
			Problem problem = ps.QueryProblem(pid);
			JSONObject jo = JSONObject.fromObject(problem);
			ja.add(jo);
		}
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
}
