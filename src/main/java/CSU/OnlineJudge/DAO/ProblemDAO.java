package CSU.OnlineJudge.DAO;

import java.io.Serializable;
import java.util.List;

import CSU.OnlineJudge.Model.Problem;

public interface ProblemDAO {
	
	public int addProblem(Problem problem);
	
	public void updateProblem(Problem problem);
	
	public void deleteProblem(Problem problem);
	
	public Problem queryProblem(int id);
	
	public List<Problem> GetAllProblemByPageSize(int row,int PageSize);
	
	public List<Problem> GetProblemByPageSizeWithFlag(int row,int PageSize,int flag);
	
	public List<Problem> VagueByPageSizeWithFlagByName(int row,int PageSize,int flag,String ProblemName);
	
	public List<Problem> VagueByPageSizeWithFlagByPeople(int row,int PageSize,String PeopleName);
	
	public List<Object[]> GetProblemOutInfo(int row,int PageSize,int flag);
}
