package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Code;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Service.CodeService;
import CSU.OnlineJudge.Service.UserService;
import CSU.OnlineJudge.Utils.DateUtil;
import CSU.OnlineJudge.Utils.HtmlUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonBeanProcessor;

public class CodeAction extends ActionSupport{
	
	private CodeService CodeService;
	private Code code = new Code();
	private UserService UserService;
	private User user;

	public UserService getUserService() {
		return UserService;
	}

	public void setUserService(UserService userService) {
		UserService = userService;
	}

	public CodeService getCodeService() {
		return CodeService;
	}

	public void setCodeService(CodeService codeService) {
		CodeService = codeService;
	}

	//增加一个代码
	public void AddCode() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String code_name = request.getParameter("code_name");
		String code_info = request.getParameter("code_info");
		String code_file = request.getParameter("code_file");
		String code_type = request.getParameter("code_type");
		
		DateUtil du = new DateUtil();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		code.setCodeInfo(code_info);
		code.setCodeName(code_name);
		code.setUserAccount(useraccount);
		code.setCodeFile(code_file);
		code.setCodeType(code_type);
		code.setCodeTime(du.GetNowDate());
		
		CodeService.AddCode(code);
		
	}
	
	//删除一个代码
	public void DeleteCode() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String code_id = request.getParameter("code_id");
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		if(code_id == null || code_id == "" || code_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int cid = Integer.valueOf(code_id);
		
		code = CodeService.QueryCode(cid);
		
		if(code == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		CodeService.DeleteCode(code);
		
	}
	
	//查询单一代码
	public void QuerySingleCode() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String code_id = request.getParameter("code_id");
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		if(code_id == null || code_id == "" || code_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int cid = Integer.valueOf(code_id);
		
		code = CodeService.QueryCode(cid);
		
		if(code == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(code);
		
		out.println(code.toString());
        out.flush(); 
        out.close();
		
	}
	
	//更新一个代码
	public void UpdateCode() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		String code_id = request.getParameter("code_id");
		String code_name = request.getParameter("code_name");
		String code_info = request.getParameter("code_info");
		
		if(code_id == null || code_id == "" || code_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int cid = Integer.valueOf(code_id);
		
		code = CodeService.QueryCode(cid);
		
		code.setCodeInfo(code_info);
		code.setCodeName(code_name);
		
		if(code == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		CodeService.UpdateCode(code);
		
	}
	
	//根据用户id查询代码
	public void QueryCodeByUserId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		String user_id = request.getParameter("user_id");
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		int uid = Integer.valueOf(user_id);
		user = UserService.queryUser(uid);
		String u_account = user.getUserAccount();
		
		List<Code>  CodeList = CodeService.QueryCodeByUserAccountByPageSize(u_account,row,PageSize);
		
		JSONArray ja = JSONArray.fromObject(CodeList);
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	
	//清空用户所有代码
	public void ClearCodeByAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String user_id = request.getParameter("user_id");
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int uid = Integer.valueOf(user_id);
		
		user = UserService.queryUser(uid);
		
		String u_account = user.getUserAccount();
		
		List<Code>  CodeList = CodeService.QueryCodeByUserAccount(u_account);

		CodeService.DeleteMutiplyCode(CodeList);
		
	}
	
	//根据用户id查询代码总数
	public void CountCodeByUserId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		String user_id = request.getParameter("user_id");
		
		if(user_id == null || user_id == "" || user_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int uid = Integer.valueOf(user_id);
		
		user = UserService.queryUser(uid);
		
		String u_account = user.getUserAccount();
		
		int cnt = CodeService.CountUserCode(u_account);
		
		JSONObject jo = new JSONObject();
		jo.put("CodeCount", cnt);
		
		out.println(jo.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户账号查询代码
	public void QueryCodeByUserAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		if(useraccount == null || useraccount == "" || useraccount.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		List<Code>  CodeList = CodeService.QueryCodeByUserAccountByPageSize(useraccount,row,PageSize);
		
		JSONArray ja = JSONArray.fromObject(CodeList);
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户账号查询代码总数
	public void CountCodeByUserAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String useraccount = (String) session.getAttribute("useraccount");
		
		int cnt = CodeService.CountUserCode(useraccount);
		
		JSONObject jo = new JSONObject();
		jo.put("CodeCount", cnt);
		
		out.println(jo.toString());
        out.flush(); 
        out.close();
		
	}
}
