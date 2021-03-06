package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Comment;
import CSU.OnlineJudge.Service.CommentService;
import CSU.OnlineJudge.Utils.DateUtil;
import CSU.OnlineJudge.Utils.HtmlUtil;
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
	
	//添加评论
	public void AddComment() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		DateUtil du = new DateUtil();

		String comment_info = request.getParameter("comment_info");
		String answer_id = request.getParameter("answer_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		if(answer_id == null || answer_id == "" || answer_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int aid = Integer.valueOf(answer_id);
		
		comment.setAnswerId(aid);
		comment.setCommentInfo(comment_info);
		comment.setCommentTime(du.GetNowDate());
		comment.setUserAccount(user_account);
		
		CommentService.AddComment(comment);
		
	}
	
	//删除评论
	public void DeleteComment() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String comment_id = request.getParameter("comment_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(comment_id == null || comment_id == "" || comment_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
	
	//查询单一评论
	public void QuerySingleComment() throws Exception{
	
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String comment_id = request.getParameter("comment_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(comment_id == null || comment_id == "" || comment_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
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
	
	//通过题解分页查询评论
	public void QueryCommentByAnswerIdPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		String answer_id = request.getParameter("answer_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(answer_id == null || answer_id == "" || answer_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int aid = Integer.valueOf(answer_id);
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		List<Comment> CommentList = CommentService.QueryCommentByAnswerIdPageSize(aid, row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(CommentList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//分页查询所有评论
	public void QueryCommentPageSize() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String page = request.getParameter("page");
		String size = request.getParameter("limit");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		List<Comment> CommentList = CommentService.QueryCommentByPageSize(row, PageSize);
		
		JSONArray ja = JSONArray.fromObject(CommentList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//查询评论总数
	public void CountComment() throws Exception{
		
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
		int count = CommentService.CountComment();
		
		JSONObject jo = new JSONObject();
		jo.put("CommentCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	
	//通过题解编号查询评论总数
	public void CountCommentByAnswerId() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String answer_id = request.getParameter("answer_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(answer_id == null || answer_id == "" || answer_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int aid = Integer.valueOf(answer_id);
		
		int count = CommentService.CountCommentByAnswerId(aid);
		
		JSONObject jo = new JSONObject();
		jo.put("CommentCount", count);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
		
	}

	//通过题解id查询评论
	public void QueryCommentByAnswerId() throws Exception{
		
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
		String answer_id = request.getParameter("answer_id");
		
		if(answer_id == null || answer_id == "" || answer_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int aid = Integer.valueOf(answer_id);
		
		List<Comment> CommentList = CommentService.QueryCommentByAnswerId(aid);
		
		JSONArray ja = JSONArray.fromObject(CommentList);
		
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
		
	}
	
	//清空所有评论
	public void ClearComment() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();

		String answer_id = request.getParameter("answer_id");
		
		HttpSession session = request.getSession();
		String user_account = (String) session.getAttribute("useraccount");
		
		if(user_account == null || user_account == "" || user_account.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		if(answer_id == null || answer_id == "" || answer_id.equals("")) {
			out.println("Fail");
	        out.flush(); 
	        out.close();
	        return ;
		}
		
		int aid = Integer.valueOf(answer_id);
		
		List<Comment> CommentList = CommentService.QueryCommentByAnswerId(aid);
		
		CommentService.deleteMutiplyAnswer(CommentList);
		
	}
}
