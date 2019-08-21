package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.ProblemResult;

public interface ProblemResultService {
	
	public void AddProblemResult(ProblemResult pr);
	
	public void UpdateProblemResult(ProblemResult pr);
	
	public void DeleteProblemResult(ProblemResult pr);
	
	public ProblemResult QueryProblemResult(int id);
	
	public String QueryProblemResultByProblemResultAccount(int ProblemId,String ProblemResult,String UserAccount);

	public List<ProblemResult> QueryProblemResultByProblemAccount(int ProblemId,String UserAccount);
	
	public List<ProblemResult> QueryProblemResultByResultAccount(String Result,String UserAccount);
	
	public List<ProblemResult> QueryProblemResultByAccount(String UserAccount);
}
