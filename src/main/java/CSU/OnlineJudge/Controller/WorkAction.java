package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Service.WorkService;
import CSU.OnlineJudge.Service.Impl.WorkServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WorkAction extends ActionSupport{
	
	private WorkService ws;
	private Work work = new Work();
	
	public WorkService getWs() {
		return ws;
	}

	public void setWs(WorkService ws) {
		this.ws = ws;
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
		String work_createtime = request.getParameter("work_createtime");
		String work_info = request.getParameter("work_info");
		String work_flag = request.getParameter("work_flag");
		String calss_id = request.getParameter("calss_id");
		String work_onwer = request.getParameter("work_onwer");
		
		int cid = Integer.valueOf(calss_id);
		
		
		work.setWorkName(work_name);
		work.setWorkInfo(work_info);
		work.setWorkFlag(work_flag);
		work.setClassId(cid);
		work.setWorkOwner(work_onwer);
		work.setWorkBeginTime(work_starttime);
		work.setWorkEndTime(work_endtime);
		work.setWorkCreatTime(work_createtime);
		
		ws.addWork(work);
		
	}
	
	//删除作业-考试
	public void DeleteWork() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		int wid = Integer.valueOf(work_id);
		
		work = ws.queryWork(wid);
		if(work == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		ws.updateWork(work);
		
	}
	
	//更新作业-考试
	public void UpdateWork() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		String work_name = request.getParameter("work_name");
		String work_starttime = request.getParameter("work_starttime");
		String work_endtime = request.getParameter("work_endtime");
		String work_createtime = request.getParameter("work_createtime");
		String work_info = request.getParameter("work_info");
		String work_flag = request.getParameter("work_flag");
		String calss_id = request.getParameter("calss_id");
		String work_onwer = request.getParameter("work_onwer");
		
		int cid = Integer.valueOf(calss_id);
		int wid = Integer.valueOf(work_id);
		
		boolean flag = false;
		work = ws.queryWork(wid);
		
		if(work == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		work.setWorkName(work_name);
		work.setWorkInfo(work_info);
		work.setWorkFlag(work_flag);
		work.setClassId(cid);
		work.setWorkOwner(work_onwer);
		work.setWorkBeginTime(work_starttime);
		work.setWorkEndTime(work_endtime);
		work.setWorkCreatTime(work_createtime);
		
		ws.updateWork(work);
		
	}
	
	//得到指定作业-考试
	public void QuerySingleWork() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String work_id = request.getParameter("work_id");
		
		int wid = Integer.valueOf(work_id);
		
		work = ws.queryWork(wid);
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
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Work> WorkList = ws.QueryWorkByPageSize(row, PageSize);
		
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
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Work> WorkList = ws.QueryHomeworkByPageSize(row, PageSize, 0);
		
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
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Work> WorkList = ws.QueryExamByPageSize(row, PageSize, 1);
		
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
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String class_id = request.getParameter("class_id");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int cid = Integer.valueOf(class_id);
		
		List<Work> WorkList = ws.QueryHomeWorkByPageSizeWithClassId(row, PageSize, cid, 0);
		
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
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String class_id = request.getParameter("class_id");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int cid = Integer.valueOf(class_id);
		
		List<Work> WorkList = ws.QueryHomeWorkByPageSizeWithClassId(row, PageSize, cid, 1);
		
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
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		int cid = Integer.valueOf(class_id);
		
		List<Work> WorkList = ws.QueryWorkByPageSizeWithClassId(row, PageSize, cid);
		
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
		
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String work_onwer = request.getParameter("work_onwer");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Work> WorkList = ws.QueryWorkByPageSizeWithOwner(row, PageSize, work_onwer);
		
		JSONArray ja = JSONArray.fromObject(WorkList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
		
	}
}
