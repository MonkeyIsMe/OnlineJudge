package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CommentDAO;
import CSU.OnlineJudge.Model.Comment;
import CSU.OnlineJudge.Service.CommentService;

@Transactional
public class CommentServiceImpl implements CommentService{

	private CommentDAO cd;
	
	public CommentDAO getCd() {
		return cd;
	}

	public void setCd(CommentDAO cd) {
		this.cd = cd;
	}

	public void AddComment(Comment comment) {
		// TODO Auto-generated method stub
		cd.addComment(comment);
	}

	public void DeleteComment(Comment comment) {
		// TODO Auto-generated method stub
		cd.deleteComment(comment);
	}

	public void UpdateComment(Comment comment) {
		// TODO Auto-generated method stub
		cd.updateComment(comment);
	}

	public Comment QueryComment(int id) {
		// TODO Auto-generated method stub
		return cd.queryComment(id);
	}

	public List<Comment> QueryCommentByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return cd.QueryCommentByPageSize(row, PageSize);
	}

	public List<Comment> QueryCommentByAnswerIdPageSize(int AnswerId, int row, int PageSize) {
		// TODO Auto-generated method stub
		return cd.QueryCommentByAnswerIdPageSize(AnswerId, row, PageSize);
	}

	public int CountComment() {
		// TODO Auto-generated method stub
		return cd.CountComment();
	}

}
