package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Answer;

public interface AnswerDAO {
	
	public void addAnswer(Answer answer);
	
	public void updateAnswer(Answer answer);
	
	public void deleteAnswer(Answer answer);
	
	public Answer queryAnswer(int id);
	
	public List<Answer> QueryAnswerByProblemIdPageSize(int ProblemId,int row,int PageSize);
	
	public List<Answer> QueryAnswerByPageSize(int row,int PageSize);
	
	public int CountAnswer();
	
}
