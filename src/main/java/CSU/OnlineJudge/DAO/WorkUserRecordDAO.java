package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.WorkUserRecord;

public interface WorkUserRecordDAO {
	
	public void addWorkUserRecord(WorkUserRecord wur);
	
	public void deleteWorkUserRecord(WorkUserRecord wur);
	
	public void updateWorkUserRecord(WorkUserRecord wur);
	
	public WorkUserRecord queryWorkUserRecord(int id);
	
	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemId(int row,int PageSize,int ProblemId);
	
	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithUserAccount(int row,int PageSize,String UserAccount);
	
	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithWorkId(int row,int PageSize,int WorkId);
	
	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemWork(int row,int PageSize,int ProblemId,int WorkId);
	
	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithAccountWork(int row,int PageSize,String UserAccount,int WorkId);
	
	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithUserIdWork(int row,int PageSize,int UserId,int WorkId);
	
	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemAccount(int row,int PageSize,int ProblemId,String UserAccount);
	
	public List<WorkUserRecord> queryWorkUserRecordByProblemWorkAccountPageSize(int row,int PageSize,int ProblemId,int WorkId,String UserAccount);
	
	
}
