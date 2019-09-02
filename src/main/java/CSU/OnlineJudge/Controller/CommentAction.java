package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Comment;
import CSU.OnlineJudge.Service.CommentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommentAction extends ActionSupport{
	
	private CommentService CommentService;
	private Comment comment = new Comment();
	
	public CommentService getCommentService() {
		return CommentService;
	}
	public void setCommentService(CommentService commentService) {
		CommentService = commentService;
	}
	
	public void AddComment() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String comment_info = request.getParameter("comment_info");
		String answer_id = request.getParameter("answer_id");
		String comment_time = request.getParameter("comment_time");
		String user_account = request.getParameter("user_account");
		
		int aid = Integer.valueOf(answer_id);
		
		comment.setAnswerId(aid);
		comment.setCommentInfo(comment_info);
		comment.setCommentTime(comment_time);
		comment.setUserAccount(user_account);
		
		CommentService.AddComment(comment);
		
	}
	
	public void DeleteComment() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String comment_id = request.getParameter("comment_id");
		
		int cid = Integer.valueOf(comment_id);
		
		comment = CommentService.QueryComment(cid);
		
		if(comment == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		CommentService.DeleteComment(comment);
		
	}
	

	
	public void QuerySingleComment() throws Exception{
	
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String comment_id = request.getParameter("comment_id");
		
		int cid = Integer.valueOf(comment_id);
		
		comment = CommentService.QueryComment(cid);
		
		if(comment == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		JSONObject jo = JSONObject.fromObject(comment);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	public void QueryCommentByAnswerIdPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String answer_id = request.getParameter("answer_id");
		
		int aid = Integer.valueOf(answer_id);
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		List<Comment> CommentList = CommentService.QueryCommentByAnswerIdPageSize(aid, row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(CommentList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	public void QueryCommentPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		List<Comment> CommentList = CommentService.QueryCommentByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(CommentList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	public void CountComment() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int count = CommentService.CountComment();
		
		JSONObject jo = new JSONObject();
		jo.put("CommentCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}

}
