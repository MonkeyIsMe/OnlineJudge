package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Case;

public interface CaseService {
	
	public void AddCase(Case Case);
	
	public void DeleteCase(Case Case);
	
	public void UpdateCase(Case Case);
	
	public Case QueryCase(int id);
	
	public List<Case> GetProblemCase(int ProblemId);
	
	public List<Case> GetCaseByFlag(int ProblemId,int Flag);
	
	
}
