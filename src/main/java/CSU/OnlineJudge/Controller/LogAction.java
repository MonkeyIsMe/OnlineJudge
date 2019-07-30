package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Log;
import CSU.OnlineJudge.Service.LogService;
import CSU.OnlineJudge.Service.Impl.LogServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LogAction extends ActionSupport{

	private Log log = new Log();
	private LogService LogService;
	

	public LogService getLogService() {
		return LogService;
	}


	public void setLogService(LogService logService) {
		LogService = logService;
	}


	//添加当天的日志
	public void AddLog() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String log_time = request.getParameter("log_time");
		
		int submission = Integer.valueOf(0);
		int accept = Integer.valueOf(0);
		int lsong = Integer.valueOf(0);
		int rte = Integer.valueOf(0);
		int tle = Integer.valueOf(0);
		int ce = Integer.valueOf(0);
		
		log.setTimeLimitTimes(tle);
		log.setCompileErrorTimes(ce);
		log.setRuntimeErrorTimes(rte);
		log.setAcceptTimes(accept);
		log.setCompileErrorTimes(ce);
		log.setSubmissionNumber(submission);
		log.setLogTime(log_time);
		
		LogService.addLog(log);
		
	}
	
	
}
