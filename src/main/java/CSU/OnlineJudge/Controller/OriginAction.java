package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Origin;
import CSU.OnlineJudge.Service.OriginService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OriginAction extends ActionSupport{
	
	private OriginService OriginService;
	private Origin origin = new Origin();
	
	public OriginService getOriginService() {
		return OriginService;
	}


	public void setOriginService(OriginService originService) {
		OriginService = originService;
	}

	//添加一个题目的标程
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
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
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
		
		int pid = Integer.valueOf(problem_id);
		
		origin.setOrginType(origin_type);
		origin.setProblemCode(origin_code);
		origin.setProblemName(problem_name);
		origin.setProblemId(pid);
		
		OriginService.AddOrigin(origin);
		
	}
	
	//添加一个题目的标程
	public void DeleteOrigin() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String origin_id = request.getParameter("origin_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(origin_id == null || origin_id == "" || origin_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
	
	//查询一个题目的标程
	public void QuerySingleOrigin() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		String origin_id = request.getParameter("origin_id");
		
		if(origin_id == null || origin_id == "" || origin_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int oid = Integer.valueOf(origin_id);
		
		origin = OriginService.queryOrigin(oid);
		
		if(origin == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(origin);
		out.println(jo.toString());
        out.flush(); 
        out.close();
		
	}
	
	
	//添加一个题目的标程
	public void UpdateOrigin() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		String origin_code = request.getParameter("origin_code");
		String origin_type = request.getParameter("origin_type");
		String origin_id = request.getParameter("origin_id");
		
		if(origin_id == null || origin_id == "" || origin_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
	
	//分页查询所有标程
	public void QueryOriginPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
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
	
	//根据题目id分页查询所有标程
	public void QueryOriginByProblemId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		String problem_id = request.getParameter("problem_id");
		
		if(problem_id == null || problem_id == "" || problem_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int pid = Integer.valueOf(problem_id);
		
		List<Origin> origin_list = OriginService.QueryOriginByProblemId(pid);
		
		JSONArray ja = JSONArray.fromObject(origin_list);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
}
