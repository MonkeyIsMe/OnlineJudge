package CSU.OnlineJudge.Utils;

import org.apache.commons.lang.StringEscapeUtils;

public class ChangeHtml {
	
	public static String htmlReplace(String str){
		str = StringEscapeUtils.escapeHtml(str);
        return str;
    }
}
