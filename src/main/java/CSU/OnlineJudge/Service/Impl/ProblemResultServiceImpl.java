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

	public String GeryProblemResultByProblemResultAccount(int ProblemId, String ProblemResult,
			String UserAccount) {
		// TODO Auto-generated method stub
		
		List<ProblemResult> result_list = prd.GeryProblemResultByProblemAccount(ProblemId, UserAccount);
		if(result_list.size() == 0) return "";
		else{
			List<ProblemResult> ac_list = prd.GeryProblemResultByProblemResultAccount(ProblemId, "AC", UserAccount);
			if(ac_list.size() != 0) return "AC";
			List<ProblemResult> wa_list = prd.GeryProblemResultByProblemResultAccount(ProblemId, "WA", UserAccount);
			if(wa_list.size() != 0) return "WA";
			List<ProblemResult> tle_list = prd.GeryProblemResultByProblemResultAccount(ProblemId, "TLE", UserAccount);
			if(tle_list.size() != 0) return "TLE";
			List<ProblemResult> rte_list = prd.GeryProblemResultByProblemResultAccount(ProblemId, "RTE", UserAccount);
			if(rte_list.size() != 0) return "RTE";
			List<ProblemResult> ce_list = prd.GeryProblemResultByProblemResultAccount(ProblemId, "CE", UserAccount);
			if(ce_list.size() != 0) return "CE";
		}
		return "";
		
	}

	public List<ProblemResult> GeryProblemResultByProblemAccount(int ProblemId, String UserAccount) {
		// TODO Auto-generated method stub
		return prd.GeryProblemResultByProblemAccount(ProblemId, UserAccount);
	}

}
