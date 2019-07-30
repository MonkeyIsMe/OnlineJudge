package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Code;
import CSU.OnlineJudge.Service.CodeService;
import net.sf.json.JSONArray;

public class CodeAction extends ActionSupport{
	
	private CodeService CodeService;
	private Code code = new Code();
	


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
		String user_account = request.getParameter("user_account");
		
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
		
		String user_account = request.getParameter("user_account");
		
		List<Code>  CodeList = CodeService.QueryCodeByUserAccount(user_account);
		
		JSONArray ja = JSONArray.fromObject(CodeList);
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
}
