package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.ProblemResultDAO;
import CSU.OnlineJudge.Model.ProblemResult;
import CSU.OnlineJudge.Service.ProblemResultService;

@Transactional
public class ProblemResultServiceImpl implements ProblemResultService{
	
	private ProblemResultDAO prd;
	
	public ProblemResultDAO getPrd() {
		return prd;
	}

	public void setPrd(ProblemResultDAO prd) {
		this.prd = prd;
	}

	public void AddProblemResult(ProblemResult pr) {
		// TODO Auto-generated method stub
		prd.addProblemResult(pr);
	}

	public void UpdateProblemResult(ProblemResult pr) {
		// TODO Auto-generated method stub
		prd.updateProblemResult(pr);
	}

	public void DeleteProblemResult(ProblemResult pr) {
		// TODO Auto-generated method stub
		prd.deleteProblemResult(pr);
	}

	public ProblemResult QueryProblemResult(int id) {
		// TODO Auto-generated method stub
		return prd.queryProblemResult(id);
	}

	public String QueryProblemResultByProblemResultAccount(int ProblemId, String ProblemResult,
			String UserAccount) {
		// TODO Auto-generated method stub
		
		List<ProblemResult> result_list = prd.QueryProblemResultByProblemAccount(ProblemId, UserAccount);
		if(result_list.size() == 0) return "";
		else{
			List<ProblemResult> ac_list = prd.QueryProblemResultByProblemResultAccount(ProblemId, "AC", UserAccount);
			if(ac_list.size() != 0) return "AC";
			List<ProblemResult> wa_list = prd.QueryProblemResultByProblemResultAccount(ProblemId, "WA", UserAccount);
			if(wa_list.size() != 0) return "WA";
			List<ProblemResult> tle_list = prd.QueryProblemResultByProblemResultAccount(ProblemId, "TLE", UserAccount);
			if(tle_list.size() != 0) return "TLE";
			List<ProblemResult> rte_list = prd.QueryProblemResultByProblemResultAccount(ProblemId, "RTE", UserAccount);
			if(rte_list.size() != 0) return "RTE";
			List<ProblemResult> ce_list = prd.QueryProblemResultByProblemResultAccount(ProblemId, "CE", UserAccount);
			if(ce_list.size() != 0) return "CE";
		}
		return "";
		
	}

	public List<ProblemResult> QueryProblemResultByProblemAccount(int ProblemId, String UserAccount) {
		// TODO Auto-generated method stub
		return prd.QueryProblemResultByProblemAccount(ProblemId, UserAccount);
	}

	public List<ProblemResult> QueryProblemResultByResultAccount(String Result, String UserAccount) {
		// TODO Auto-generated method stub
		return prd.QueryProblemResultByResultAccount(Result, UserAccount);
	}

	public List<ProblemResult> QueryProblemResultByAccount(String UserAccount) {
		// TODO Auto-generated method stub
		return prd.QueryProblemResultByAccount(UserAccount);
	}

	public Object DeleteMutiplyResult(List<ProblemResult> prlist) {
		// TODO Auto-generated method stub
		return prd.DeleteMutiplyResult(prlist);
	}

}
