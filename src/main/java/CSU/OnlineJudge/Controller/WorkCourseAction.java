package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.CourseUser;
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
	private WorkCourseService WorkCourseService;
	private WorkService WorkService;
	


	public WorkCourseService getWorkCourseService() {
		return WorkCourseService;
	}

	public void setWorkCourseService(WorkCourseService workCourseService) {
		WorkCourseService = workCourseService;
	}

	public WorkService getWorkService() {
		return WorkService;
	}

	public void setWorkService(WorkService workService) {
		WorkService = workService;
	}

	//根据课程查询作业-考试
	public void QueryWorkByCourseId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String course_id = request.getParameter("course_id");
		
		if(course_id == null || course_id == "" || course_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int cid = Integer.valueOf(course_id);
		
		JSONArray ja = new JSONArray();
		
		List<WorkCourse> WorkList = WorkCourseService.QueryWorkCourseByClassId(cid);
		for(WorkCourse workcourse : WorkList) {
			int wid = workcourse.getWorkId();
			Work work = WorkService.queryWork(wid);
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
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(course_id == null || course_id == "" || course_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int cid = Integer.valueOf(course_id);
		int wid = Integer.valueOf(work_id);
		
		wc.setCourseId(cid);
		wc.setWorkId(wid);
		
		WorkCourseService.addWorkCourse(wc);
		
	}
	
	//删除课程，考试-作业关联
	public void DeleteWorkCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String workcourse_id = request.getParameter("workcourse_id");
		
		if(workcourse_id == null || workcourse_id == "" || workcourse_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wcid = Integer.valueOf(workcourse_id);
		
		wc = WorkCourseService.queryWorkCourse(wcid);
		
		if(wc == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		WorkCourseService.deleteWorkCourse(wc);
		
	}
	
	
	//课程布置作业
	public void AddWorkForCourse() throws Exception{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String work_id = request.getParameter("work_id");
		String course_info = request.getParameter("course_info");
		
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		List<WorkCourse> delete_list = WorkCourseService.QueryWorkCourseByWorkId(wid);
		WorkCourseService.DeleteMutiplyWorkCourse(delete_list);
		
		
		JSONArray ja = JSONArray.fromObject(course_info);
		JSONArray add_ja = new JSONArray();
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String course_id = jo.getString("courseId");
			int cid = Integer.valueOf(course_id);
			//System.out.println(cu.toString());
			wc.setCourseId(cid);
			wc.setWorkId(wid);
			add_ja.add(wc);
		}
		
		//System.out.println(add_ja.size());
		List<WorkCourse> wc_list = JSONArray.toList(add_ja, WorkCourse.class);
		WorkCourseService.AddMutiplyWorkCourse(wc_list);
	}


	//根据考试编号查询课程
	public void QueryCourseByWorkId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		
		
		List<WorkCourse> WorkList = WorkCourseService.QueryWorkCourseByWorkId(wid);
		JSONArray ja = JSONArray.fromObject(WorkList);
		
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}

}
