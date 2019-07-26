package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import CSU.OnlineJudge.DAO.CaseDAO;
import CSU.OnlineJudge.DAO.Impl.CaseDAOImpl;
import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Service.CaseService;

@Transactional
public class CaseServiceImpl implements CaseService{

	private CaseDAO cd;
	
	public CaseDAO getCd() {
		return cd;
	}

	public void setCd(CaseDAO cd) {
		this.cd = cd;
	}
	
	
	public void AddCase(Case Case) {
		// TODO Auto-generated method stub
		cd.addCase(Case);
	}

	public void DeleteCase(Case Case) {
		// TODO Auto-generated method stub
		cd.deleteCase(Case);
	}

	public void UpdateCase(Case Case) {
		// TODO Auto-generated method stub
		cd.updateCase(Case);
	}

	public Case QueryCase(int id) {
		// TODO Auto-generated method stub
		return cd.queryCase(id);
	}

	public List<Case> GetAllCase(int ProblemId) {
		// TODO Auto-generated method stub
		
		List<Case> result = cd.QueryCaseByProblemId(ProblemId);
		
		return result;
	}

	public List<Case> GetProblemCase(int ProblemId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Case> GetCaseByFlag(int ProblemId, int Flag) {
		// TODO Auto-generated method stub
		return cd.QueryCaseByProblemIdFlag(ProblemId, Flag);
	}

}
