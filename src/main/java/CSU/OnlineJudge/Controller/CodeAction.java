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
		//String user_account = request.getParameter("user_account");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("user_account");
		
		code.setCodeInfo(code_info);
		code.setCodeName(code_name);
		code.setUserAccount(user_account);
		
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
	
	//更新一个代码
	public void UpdateCode() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String code_id = request.getParameter("code_id");
		String code_name = request.getParameter("code_name");
		String code_info = request.getParameter("code_info");
		
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
	
	//根据用户查询代码
	public void QueryCodeByAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		int uid = Integer.valueOf(user_id);
		user = UserService.queryUser(uid);
		String user_account = user.getUserAccount();
		
		List<Code>  CodeList = CodeService.QueryCodeByUserAccountByPageSize(user_account,row,PageSize);
		
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
		int uid = Integer.valueOf(user_id);
		user = UserService.queryUser(uid);
		String user_account = user.getUserAccount();
		
		List<Code>  CodeList = CodeService.QueryCodeByUserAccount(user_account);

		CodeService.DeleteMutiplyCode(CodeList);
		
	}
	
	//根据用户查询代码总数
	public void CountCodeByAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String user_id = request.getParameter("user_id");
		int uid = Integer.valueOf(user_id);
		user = UserService.queryUser(uid);
		String user_account = user.getUserAccount();
		
		int cnt = CodeService.CountUserCode(user_account);
		
		JSONObject jo = new JSONObject();
		jo.put("CodeCount", cnt);
		
		out.println(jo.toString());
        out.flush(); 
        out.close();
		
	}
	
}
