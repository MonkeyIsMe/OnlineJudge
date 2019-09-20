package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Answer;

public interface AnswerService {

	public void AddAnswer(Answer answer);
	
	public void UpdateAnswer(Answer answer);
	
	public void DeleteAnswer(Answer answer);
	
	public Answer QueryAnswer(int id);
	
	public List<Answer> QueryAnswerByProblemIdPageSize(int ProblemId,int row,int PageSize);
	
	public List<Answer> QueryAnswerByPageSize(int row,int PageSize);
	
	public int CountAnswer();
	
	public Object DeleteMutiplyAnswer(List<Answer> AnswerList);
	
	public List<Answer> QueryAnswerByProblemId(int ProblemId);
	
	public int CountAnswerByProblem(int ProblemId);
}
