package CSU.OnlineJudge.Utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

public class HtmlUtil {
	
	public static String htmlReplace(String str){
		str = StringEscapeUtils.escapeHtml(str);
        return str;
    }
	
	//判断是否登录
	public boolean IsLogin() {
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		String useraccount = (String)request.getSession().getAttribute("useraccount");
		
		if(useraccount == null || useraccount =="") return true;
		else return false;
		
	}
}
