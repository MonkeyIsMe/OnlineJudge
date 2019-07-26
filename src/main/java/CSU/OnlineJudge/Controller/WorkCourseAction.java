package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Model.WorkCourse;
import CSU.OnlineJudge.Service.WorkCourseService;
import CSU.OnlineJudge.Service.WorkService;
import CSU.OnlineJudge.Service.Impl.WorkCourseServiceImpl;
import CSU.OnlineJudge.Service.Impl.WorkServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkCourseAction extends ActionSupport{
	
	private WorkCourse wc = new WorkCourse();
	private WorkCourseService wcs = new WorkCourseServiceImpl();
	private WorkService ws = new WorkServiceImpl();
	
	//根据课程查询作业-考试
	public void QueryWorkByCourseId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String course_id = request.getParameter("course_id");
		
		int cid = Integer.valueOf(course_id);
		
		JSONArray ja = new JSONArray();
		
		List<WorkCourse> WorkList = wcs.QueryWorkCourseByClassId(cid);
		for(WorkCourse workcourse : WorkList) {
			int wid = workcourse.getWorkId();
			Work work = ws.queryWork(wid);
			JSONObject jo = JSONObject.fromObject(work);
			ja.add(jo);
		}
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//添加课程，考试-作业关联
	public void AddWorkCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String course_id = request.getParameter("course_id");
		String work_id = request.getParameter("work_id");
		
		int cid = Integer.valueOf(course_id);
		int wid = Integer.valueOf(work_id);
		
		wc.setCourseId(cid);
		wc.setWorkId(wid);
		
		wcs.addWorkCourse(wc);
		
	}
	
	//删除课程，考试-作业关联
	public void DeleteWorkCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String workcourse_id = request.getParameter("workcourse_id");
		
		int wcid = Integer.valueOf(workcourse_id);
		
		wc = wcs.queryWorkCourse(wcid);
		
		if(wc == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		wcs.deleteWorkCourse(wc);
		
	}
	
	
}
