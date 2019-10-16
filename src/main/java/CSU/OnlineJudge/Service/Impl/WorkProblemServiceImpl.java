package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkProblemDAO;
import CSU.OnlineJudge.DAO.Impl.WorkProblemDAOImpl;
import CSU.OnlineJudge.Model.WorkProblem;
import CSU.OnlineJudge.Service.WorkProblemService;

@Transactional
public class WorkProblemServiceImpl implements WorkProblemService{
	
	private  WorkProblemDAO wpd;
	
	public WorkProblemDAO getWpd() {
		return wpd;
	}

	public void setWpd(WorkProblemDAO wpd) {
		this.wpd = wpd;
	}

	public void addWorkProblem(WorkProblem wp) {
		// TODO Auto-generated method stub
		//System.out.println("service = " + wp.toString());
		wpd.addWorkProblem(wp);
	}

	public void deleteWorkProblem(WorkProblem wp) {
		// TODO Auto-generated method stub
		wpd.deleteWorkProblem(wp);
	}

	public void updateWorkProblem(WorkProblem wp) {
		// TODO Auto-generated method stub
		wpd.updateWorkProblem(wp);
	}

	public WorkProblem queryWorkProblem(int id) {
		// TODO Auto-generated method stub
		return wpd.queryWorkProblem(id);
	}

	public List<WorkProblem> QueryWorkProblemByWorkId(int WorkId) {
		// TODO Auto-generated method stub
		return wpd.QueryWorkProblemByWorkId(WorkId);
	}

	public Object AddMutiplyWorkProblem(List<WorkProblem> wp_list) {
		// TODO Auto-generated method stub
		return wpd.AddMutiplyWorkProblem(wp_list);
	}

	public Object DeleteMutiplyWorkProblem(List<WorkProblem> wp_list) {
		// TODO Auto-generated method stub
		return wpd.DeleteMutiplyWorkProblem(wp_list);
	}

	public int CountByWorkId(int WorkId) {
		// TODO Auto-generated method stub
		return wpd.CountByWorkId(WorkId);
	}

	public WorkProblem QueryWorkProblemByWorkProblem(int WorkId, int ProblemId) {
		// TODO Auto-generated method stub
		List<WorkProblem> wp_list = wpd.QueryWorkProblemByWorkProblem(WorkId, ProblemId);
		if(wp_list.size() == 0) return null;
		return wp_list.get(0);
	}

}
