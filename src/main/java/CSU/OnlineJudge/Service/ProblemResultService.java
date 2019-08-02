package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.ProblemResult;

public interface ProblemResultService {
	
	public void AddProblemResult(ProblemResult pr);
	
	public void UpdateProblemResult(ProblemResult pr);
	
	public void DeleteProblemResult(ProblemResult pr);
	
	public ProblemResult QueryProblemResult(int id);
	
	public String GeryProblemResultByProblemResultAccount(int ProblemId,String ProblemResult,String UserAccount);

	public List<ProblemResult> GeryProblemResultByProblemAccount(int ProblemId,String UserAccount);
}
