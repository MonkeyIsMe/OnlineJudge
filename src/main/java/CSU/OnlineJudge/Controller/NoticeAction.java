package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Notice;
import CSU.OnlineJudge.Service.NoticeService;
import CSU.OnlineJudge.Utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NoticeAction extends ActionSupport{

	private NoticeService NoticeService;
	private Notice notice = new Notice();
	
	public NoticeService getNoticeService() {
		return NoticeService;
	}
	public void setNoticeService(NoticeService noticeService) {
		NoticeService = noticeService;
	}
	
	//增加一个公告
	public void AddNotice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String notice_name = request.getParameter("notice_name");
		String notice_info = request.getParameter("notice_info");
		//System.out.println(notice_name + " " + notice_info);
		DateUtil du = new DateUtil();
		
		notice.setNoticeName(notice_name);
		notice.setNoticeInfo(notice_info);
		notice.setNoticeTime(du.GetNowDate());
		
		NoticeService.AddNotice(notice);
		
	}
	
	//删除一个公告
	public void DeleteNotice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String notice_id = request.getParameter("notice_id");
		
		if(notice_id == null || notice_id == "" || notice_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int nid = Integer.valueOf(notice_id);
		
		notice = NoticeService.QueryNotice(nid);
		NoticeService.DeleteNotice(notice);
	}
	
	//更新一个公告
	public void UpdateNotice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String notice_id = request.getParameter("notice_id");
		String notice_name = request.getParameter("notice_name");
		String notice_info = request.getParameter("notice_info");
		
		if(notice_id == null || notice_id == "" || notice_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int nid = Integer.valueOf(notice_id);
		
		notice = NoticeService.QueryNotice(nid);
		
		if(notice == null) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		notice.setNoticeName(notice_name);
		notice.setNoticeInfo(notice_info);
		NoticeService.UpdateNotice(notice);
	}
	
	//查询单一公告
	public void QuerySingleNotice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String notice_id = request.getParameter("notice_id");
		
		if(notice_id == null || notice_id == "" || notice_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int nid = Integer.valueOf(notice_id);
		
		notice = NoticeService.QueryNotice(nid);
		
		JSONObject jo = JSONObject.fromObject(notice);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
	}
	
	//分页查询公告
	public void QueryNoticeByPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
		
		List<Notice> NoticeList = NoticeService.QueryNoticeByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(NoticeList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//计算公告总数
	public void CountNotice() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int count = NoticeService.CountNotice();
		
		JSONObject jo = new JSONObject();
		
		jo.put("NoticeCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
	}
	
}
