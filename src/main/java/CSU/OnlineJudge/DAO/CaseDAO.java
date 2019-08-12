package CSU.OnlineJudge.DAO;

import java.util.List;
import CSU.OnlineJudge.Model.Case;

public interface CaseDAO {
	
	public void addCase(Case Case);
	
	public void deleteCase(Case Case);
	
	public void updateCase(Case Case);
	
	public Case queryCase(int id);
	
	public List<Case> QueryCaseByProblemId(int ProblemId);
	
	public List<Case> QueryCaseByProblemIdFlag(int ProblemId,int Flag);
	
	public Object addMutiplyCase(List<Case> Case);
}
