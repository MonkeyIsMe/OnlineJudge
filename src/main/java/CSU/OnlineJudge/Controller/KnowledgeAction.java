package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Knowledge;
import CSU.OnlineJudge.Service.KnowledgeService;
import CSU.OnlineJudge.Service.Impl.KnowledgeServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KnowledgeAction extends ActionSupport{
	
	private Knowledge knowledge = new Knowledge();
	private KnowledgeService KnowledgeService;
	

	public KnowledgeService getKnowledgeService() {
		return KnowledgeService;
	}

	public void setKnowledgeService(KnowledgeService knowledgeService) {
		KnowledgeService = knowledgeService;
	}

	//添加知识点
	public void AddKnowledge() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_info = request.getParameter("knowledge_info");
		String knowledge_name = request.getParameter("knowledge_name");
		
		knowledge.setKnowledgeInfo(knowledge_info);
		knowledge.setKnowledgeName(knowledge_name);
		
		KnowledgeService.addKnowledge(knowledge);
		
	}
	
	//删除知识点
	public void DeleteKnowledge() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_id = request.getParameter("knowledge_id");
		
		int kid = Integer.valueOf(knowledge_id);
		Knowledge knowledge = KnowledgeService.queryKnowledge(kid);
		
		if(knowledge == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		KnowledgeService.deleteKnowledge(knowledge);
		
	}
	
	//查询指定知识点
	public void QuerySingleKnowledge() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_id = request.getParameter("knowledge_id");
		
		int kid = Integer.valueOf(knowledge_id);
		Knowledge knowledge = KnowledgeService.queryKnowledge(kid);
		
		if(knowledge == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(knowledge);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//更新知识点
	public void UpdateKnowledge() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String knowledge_id = request.getParameter("knowledge_id");
		String knowledge_info = request.getParameter("knowledge_info");
		String knowledge_name = request.getParameter("knowledge_name");
		
		int kid = Integer.valueOf(knowledge_id);
		knowledge = KnowledgeService.queryKnowledge(kid);
		
		if(knowledge == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		knowledge.setKnowledgeInfo(knowledge_info);
		knowledge.setKnowledgeName(knowledge_name);
		
		KnowledgeService.updateKnowledge(knowledge);
		
	}
	
	//分页查询知识点
	public void QueryKnowledgeByPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Knowledge> know_list = KnowledgeService.QueryKnowledgeByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(know_list);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}

}
