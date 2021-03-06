package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.CourseUser;
import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Model.WorkCourse;
import CSU.OnlineJudge.Model.WorkProblem;
import CSU.OnlineJudge.Service.CourseService;
import CSU.OnlineJudge.Service.CourseUserService;
import CSU.OnlineJudge.Service.WorkCourseService;
import CSU.OnlineJudge.Service.WorkProblemService;
import CSU.OnlineJudge.Service.WorkService;
import CSU.OnlineJudge.Service.Impl.WorkServiceImpl;
import CSU.OnlineJudge.Utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkAction extends ActionSupport{
	
	private WorkService WorkService;
	private Work work = new Work();
	private WorkProblem wp = new WorkProblem();
	private WorkProblemService WorkProblemService;
	private CourseUserService CourseUserService;
	private WorkCourseService WorkCourseService;

	
	
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

	public WorkProblemService getWorkProblemService() {
		return WorkProblemService;
	}

	public void setWorkProblemService(WorkProblemService workProblemService) {
		WorkProblemService = workProblemService;
	}
	public CourseUserService getCourseUserService() {
		return CourseUserService;
	}

	public void setCourseUserService(CourseUserService courseUserService) {
		CourseUserService = courseUserService;
	}

	//增加作业-考试
	public void AddWork() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_name = request.getParameter("work_name");
		String work_starttime = request.getParameter("work_starttime");
		String work_endtime = request.getParameter("work_endtime");
		String work_info = request.getParameter("work_info");
		String work_flag = request.getParameter("work_flag");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		DateUtil du = new DateUtil();
		String ct = du.GetNowDate();
		work.setWorkName(work_name);
		work.setWorkInfo(work_info);
		work.setWorkFlag(work_flag);
		work.setWorkOwner(user_account);
		work.setWorkCreatTime(du.GetNowDate());
		work.setWorkBeginTime(work_starttime);
		work.setWorkEndTime(work_endtime);
		
		int wid = WorkService.addWork(work);

		
		JSONObject jo = new JSONObject();
		jo.put("WorkId", wid);
		out.println(jo.toString());
		out.flush(); 
		out.close();
		
	}
	
	//删除作业-考试
	public void DeleteWork() throws Exception{
		
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
		String work_id = request.getParameter("work_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		work = WorkService.queryWork(wid);
		if(work == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		WorkService.deleteWork(work);
		
	}
	
	//更新作业-考试
	public void UpdateWork() throws Exception{
		
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
		String work_id = request.getParameter("work_id");
		String work_name = request.getParameter("work_name");
		String work_starttime = request.getParameter("work_starttime");
		String work_endtime = request.getParameter("work_endtime");
		String work_info = request.getParameter("work_info");
		String work_flag = request.getParameter("work_flag");

		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		work = WorkService.queryWork(wid);
		
		if(work == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		work.setWorkName(work_name);
		work.setWorkInfo(work_info);
		work.setWorkFlag(work_flag);
		work.setWorkBeginTime(work_starttime);
		work.setWorkEndTime(work_endtime);
		
		WorkService.updateWork(work);
		
		out.println("Success");
		out.flush(); 
		out.close();
		return ;
		
	}
	
	//得到指定作业-考试
	public void QuerySingleWork() throws Exception{
		
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
		String work_id = request.getParameter("work_id");
		
		if(work_id == null || work_id == "" || work_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int wid = Integer.valueOf(work_id);
		
		work = WorkService.queryWork(wid);
		if(work == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(work);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//得到所有作业-考试
	public void QueryAllWork() throws Exception{
		
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
		
		List<Work> WorkList = WorkService.QueryWorkByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//得到所有作业
	public void QueryAllHomeWork() throws Exception{
		
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
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Work> WorkList = WorkService.QueryHomeworkByPageSize(row, PageSize, 0);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	
	//得到所有考试
	public void QueryAllExam() throws Exception{
		
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
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Work> WorkList = WorkService.QueryExamByPageSize(row, PageSize, 1);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}

	//根据课程查询作业
	public void QueryHomeworkByCourse() throws Exception{
		
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
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String class_id = request.getParameter("class_id");
		
		if(class_id == null || class_id == "" || class_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int cid = Integer.valueOf(class_id);
		
		List<Work> WorkList = WorkService.QueryHomeWorkByPageSizeWithClassId(row, PageSize, cid, 0);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据课程查询考试
	public void QueryExamByCourse() throws Exception{
		
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
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String class_id = request.getParameter("class_id");
		
		if(class_id == null || class_id == "" || class_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int cid = Integer.valueOf(class_id);
		
		List<Work> WorkList = WorkService.QueryHomeWorkByPageSizeWithClassId(row, PageSize, cid, 1);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据课程查询考试-作业
	public void QueryWorkByCourse() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String class_id = request.getParameter("class_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(class_id == null || class_id == "" || class_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int cid = Integer.valueOf(class_id);
		
		List<Work> WorkList = WorkService.QueryWorkByPageSizeWithClassId(row, PageSize, cid);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据作者查询考试-作业
	public void QueryAllWorkByOwner() throws Exception{
		
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
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String work_onwer = request.getParameter("work_onwer");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Work> WorkList = WorkService.QueryWorkByPageSizeWithOwner(row, PageSize, work_onwer);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
	
	//根据用户账号查作业-考试
	public void QueryWorkByUserAccount() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		//System.out.println(user_account);
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		JSONArray ja = new JSONArray();
		
		List<CourseUser> cu_list = CourseUserService.QueryCourseUserByUserAccount(user_account);
		//System.out.println(user_account);
		for(CourseUser cu : cu_list) {
			int cuid = cu.getCourseId();
			List<WorkCourse> wc_list = WorkCourseService.QueryWorkCourseByClassId(cuid);
			for(WorkCourse wc : wc_list) {
				JSONObject jo = JSONObject.fromObject(wc);
				work = WorkService.queryWork(wc.getWorkId());
				ja.add(work);
			}
		}
		//System.out.println(ja.size());
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
	
	//查询考试-作业总数
	public void CountWork() throws Exception{
		
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
		int cnt = WorkService.CountWork();
		JSONObject jo = new JSONObject();
		jo.put("WorkCount", cnt);
		
		out.println(jo.toString());
        out.flush(); 
        out.close();
	}
	
}
