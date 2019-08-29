package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.ProblemResult;

public interface ProblemResultDAO {
	
	public void addProblemResult(ProblemResult pr);
	
	public void updateProblemResult(ProblemResult pr);
	
	public void deleteProblemResult(ProblemResult pr);
	
	public ProblemResult queryProblemResult(int id);
	
	public List<ProblemResult> QueryProblemResultByProblemResultAccount(int ProblemId,String ProblemResult,String UserAccount);
	
	public List<ProblemResult> QueryProblemResultByProblemAccount(int ProblemId,String UserAccount);
	
	public List<ProblemResult> QueryProblemResultByResultAccount(String Result,String UserAccount);
	
	public List<ProblemResult> QueryProblemResultByAccount(String UserAccount);
	
	public Object DeleteMutiplyResult(List<ProblemResult> prlist);
}
