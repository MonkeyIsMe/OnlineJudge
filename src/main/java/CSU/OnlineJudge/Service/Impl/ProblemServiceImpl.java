package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.ProblemDAO;
import CSU.OnlineJudge.DAO.Impl.ProblemDAOImpl;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Service.ProblemService;

@Transactional
public class ProblemServiceImpl implements ProblemService{

	private ProblemDAO pd;
	
	
	public ProblemDAO getPd() {
		return pd;
	}

	public void setPd(ProblemDAO pd) {
		this.pd = pd;
	}

	public List<Problem> GetProblemByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return pd.GetAllProblemByPageSize(row, PageSize);
	}

	public int AddProblem(Problem problem) {
		// TODO Auto-generated method stub
		return pd.addProblem(problem);
	}

	public void DeleteProblem(Problem problem) {
		// TODO Auto-generated method stub
		pd.deleteProblem(problem);
	}

	public void UpdateProblem(Problem problem) {
		// TODO Auto-generated method stub
		pd.updateProblem(problem);
	}

	public Problem QueryProblem(int id) {
		// TODO Auto-generated method stub
		return pd.queryProblem(id);
	}

	public List<Problem> QueryProblemByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		List<Problem> result = pd.GetAllProblemByPageSize(row, PageSize);
		return result;
	}


	public List<Problem> GetFlagProblemByPageSize(int row, int PageSize, int flag) {
		// TODO Auto-generated method stub
		return pd.GetProblemByPageSizeWithFlag(row, PageSize, flag);
	}


	public List<Problem> VagueByPageSizeWithFlagByName(int row, int PageSize, int flag, String ProblemName) {
		// TODO Auto-generated method stub
		return pd.VagueByPageSizeWithFlagByName(row, PageSize, flag, ProblemName);
	}


	public List<Problem> VagueByPageSizeWithFlagByPeople(int row, int PageSize, String PeopleName) {
		// TODO Auto-generated method stub
		return pd.VagueByPageSizeWithFlagByPeople(row, PageSize, PeopleName);
	}

	public List<Object[]> GetProblemOutInfo(int row, int PageSize, int flag) {
		// TODO Auto-generated method stub
		return pd.GetProblemOutInfo(row, PageSize, 1);
	}

	public int CountProblem() {
		// TODO Auto-generated method stub
		return pd.CountProblem();
	}

	public List<Object[]> GetProblemManagerInfo(int row, int PageSize) {
		// TODO Auto-generated method stub
		return pd.GetProblemManagerInfo(row, PageSize);
	}

	public Object addMutiplyProblem(List<Problem> problem) {
		// TODO Auto-generated method stub
		return pd.addMutiplyProblem(problem);
	}

}
