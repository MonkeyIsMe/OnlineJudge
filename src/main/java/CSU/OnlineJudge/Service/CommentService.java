package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Comment;

public interface CommentService {

	public void AddComment(Comment comment);
	
	public void DeleteComment(Comment comment);
	
	public void UpdateComment(Comment comment);
	
	public Comment QueryComment(int id);
	
	public List<Comment> QueryCommentByPageSize(int row,int PageSize);
	
	public List<Comment> QueryCommentByAnswerIdPageSize(int AnswerId,int row,int PageSize);
	
	public int CountComment();
	
	public Object deleteMutiplyAnswer(List<Comment> CommentList);
	
	public List<Comment> QueryCommentByAnswerId(int answer_id);
	
	public int CountCommentByAnswerId(int AnswerId);
	
}
