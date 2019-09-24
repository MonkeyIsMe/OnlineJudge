package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkUserDAO;
import CSU.OnlineJudge.Model.WorkUser;
import CSU.OnlineJudge.Service.WorkUserService;

@Transactional
public class WorkUserServiceImpl implements WorkUserService{

	private WorkUserDAO wud;
	
	public WorkUserDAO getWud() {
		return wud;
	}

	public void setWud(WorkUserDAO wud) {
		this.wud = wud;
	}

	public void AddWorkUser(WorkUser wu) {
		// TODO Auto-generated method stub
		wud.addWorkUser(wu);
	}

	public void DeleteWorkUser(WorkUser wu) {
		// TODO Auto-generated method stub
		wud.deleteWorkUser(wu);
	}

	public void UpdateWorkUser(WorkUser wu) {
		// TODO Auto-generated method stub
		wud.updateWorkUser(wu);
	}

	public WorkUser QueryWorkUser(int id) {
		// TODO Auto-generated method stub
		return wud.queryWorkUser(id);
	}

	public List<WorkUser> QueryWorkUserByPageSize(int row, int PageSize,int WorkId) {
		// TODO Auto-generated method stub
		return wud.QueryWorkUserByPageSize(row, PageSize,WorkId);
	}

	public int CountWorkUser(int WorkId) {
		// TODO Auto-generated method stub
		return wud.CountWorkUser(WorkId);
	}

}
