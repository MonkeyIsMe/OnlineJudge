package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.ProblemResult;

public interface ProblemResultDAO {
	
	public void addProblemResult(ProblemResult pr);
	
	public void updateProblemResult(ProblemResult pr);
	
	public void deleteProblemResult(ProblemResult pr);
	
	public ProblemResult queryProblemResult(int id);
	
	public List<ProblemResult> GeryProblemResultByProblemResultAccount(int ProblemId,String ProblemResult,String UserAccount);
	
	public List<ProblemResult> GeryProblemResultByProblemAccount(int ProblemId,String UserAccount);
}
