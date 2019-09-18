package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkUserRecordDAO;
import CSU.OnlineJudge.DAO.Impl.WorkUserRecordDAOImpl;
import CSU.OnlineJudge.Model.WorkUserRecord;
import CSU.OnlineJudge.Service.WorkUserRecordService;

@Transactional
public class WorkUserRecordServiceImpl implements WorkUserRecordService{

	private  WorkUserRecordDAO wurd;
	
	public WorkUserRecordDAO getWurd() {
		return wurd;
	}

	public void setWurd(WorkUserRecordDAO wurd) {
		this.wurd = wurd;
	}

	public void addWorkUserRecord(WorkUserRecord wur) {
		// TODO Auto-generated method stub
		wurd.addWorkUserRecord(wur);
	}

	public void deleteWorkUserRecord(WorkUserRecord wur) {
		// TODO Auto-generated method stub
		wurd.deleteWorkUserRecord(wur);
	}

	public void updateWorkUserRecord(WorkUserRecord wur) {
		// TODO Auto-generated method stub
		wurd.updateWorkUserRecord(wur);
	}

	public WorkUserRecord queryWorkUserRecord(int id) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecord(id);
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemId(int row, int PageSize, int ProblemId) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByPageSizeWithProblemId(row, PageSize, ProblemId);
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithUserAccount(int row, int PageSize,
			String UserAccount) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByPageSizeWithUserAccount(row, PageSize, UserAccount);
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithWorkId(int row, int PageSize, int WorkId) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByPageSizeWithWorkId(row, PageSize, WorkId);
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemWork(int row, int PageSize, int ProblemId,
			int WorkId) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByPageSizeWithProblemWork(row, PageSize, ProblemId, WorkId);
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithAccountWork(int row, int PageSize, String UserAccount,
			int WorkId) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByPageSizeWithAccountWork(row, PageSize, UserAccount, WorkId);
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemAccount(int row, int PageSize, int ProblemId,
			String UserAccount) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByPageSizeWithProblemAccount(row, PageSize, ProblemId, UserAccount);
	}

	public List<WorkUserRecord> queryWorkUserRecordByProblemWorkAccountPageSize(int row, int PageSize, int ProblemId,
			int WorkId, String UserAccount) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByProblemWorkAccountPageSize(row, PageSize, ProblemId, WorkId, UserAccount);
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithUserIdWork(int row, int PageSize, int UserId,
			int WorkId) {
		// TODO Auto-generated method stub
		return wurd.queryWorkUserRecordByPageSizeWithUserIdWork(row, PageSize, UserId, WorkId);
	}

}
