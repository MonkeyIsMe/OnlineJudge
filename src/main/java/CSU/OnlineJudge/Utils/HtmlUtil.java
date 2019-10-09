package CSU.OnlineJudge.Utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

public class HtmlUtil {
	
	public static String htmlReplace(String str){
		str = StringEscapeUtils.escapeHtml(str);
        return str;
    }
	
}
