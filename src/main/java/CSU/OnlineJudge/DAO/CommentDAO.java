package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Comment;

public interface CommentDAO {
	
	public void addComment(Comment comment);
	
	public void deleteComment(Comment comment);
	
	public void updateComment(Comment comment);
	
	public Comment queryComment(int id);
	
	public List<Comment> QueryCommentByPageSize(int row,int PageSize);
	
	public List<Comment> QueryCommentByAnswerIdPageSize(int AnswerId,int row,int PageSize);
	
	public int CountComment();
}
