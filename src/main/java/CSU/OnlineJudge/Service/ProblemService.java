package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Problem;

public interface ProblemService {
	
	public List<Problem> GetProblemByPageSize(int row,int PageSize);
	
	public List<Problem> GetFlagProblemByPageSize(int row,int PageSize,int flag);
	
	public int AddProblem(Problem problem);
	
	public void DeleteProblem(Problem problem);
	
	public void UpdateProblem(Problem problem);
	
	public Problem QueryProblem(int id);
	
	public List<Problem> QueryProblemByPageSize(int row,int PageSize);
	
	public List<Problem> VagueByPageSizeWithFlagByName(int row,int PageSize,int flag,String ProblemName);
	
	public List<Problem> VagueByPageSizeWithFlagByPeople(int row,int PageSize,String PeopleName);
	
	public List<Object[]> GetProblemOutInfo(int row,int PageSize,int flag);
	
	public List<Object[]> GetProblemManagerInfo(int row,int PageSize);
	
	public int CountProblem();
	
}
