package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Origin;
import CSU.OnlineJudge.Service.OriginService;
import net.sf.json.JSONArray;

public class OriginAction extends ActionSupport{
	
	private OriginService OriginService;
	private Origin origin = new Origin();
	
	public OriginService getOriginService() {
		return OriginService;
	}


	public void setOriginService(OriginService originService) {
		OriginService = originService;
	}

	//添加一个题目的源码
	public void AddOrigin() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		String problem_name = request.getParameter("problem_name");
		String origin_code = request.getParameter("origin_code");
		String origin_type = request.getParameter("origin_type");
		
		int pid = Integer.valueOf(problem_id);
		
		origin.setOrginType(origin_type);
		origin.setProblemCode(origin_code);
		origin.setProblemName(problem_name);
		origin.setProblemId(pid);
		
		OriginService.AddOrigin(origin);
		
	}
	
	//添加一个题目的源码
	public void DeleteOrigin() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String origin_id = request.getParameter("origin_id");
		
		int oid = Integer.valueOf(origin_id);
		
		origin = OriginService.queryOrigin(oid);
		
		if(origin == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		OriginService.DeleteOrigin(origin);
		
	}
	
	
	//添加一个题目的源码
	public void UpdateOrigin() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String origin_code = request.getParameter("origin_code");
		String origin_type = request.getParameter("origin_type");
		String origin_id = request.getParameter("origin_id");
		
		int oid = Integer.valueOf(origin_id);
		
		origin = OriginService.queryOrigin(oid);
		
		if(origin == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		origin.setOrginType(origin_type);
		origin.setProblemCode(origin_code);
		
		OriginService.UpdateOrigin(origin);
		
	}
	
	//分页查询所有源码
	public void QueryOriginPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		
		List<Origin> origin_list = OriginService.QueryOriginByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(origin_list);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//根据题目id分页查询所有源码
	public void QueryOriginByProblemId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		
		List<Origin> origin_list = OriginService.QueryOriginByProblemId(pid);
		
		JSONArray ja = JSONArray.fromObject(origin_list);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
}
