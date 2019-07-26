package CSU.OnlineJudge.DAO;

import CSU.OnlineJudge.Model.WorkCaseRecord;

public interface WorkCaseRecordDAO {
	
	public void addWorkCaseRecord(WorkCaseRecord wcr);
	
	public void updateWorkCaseRecord(WorkCaseRecord wcr);
	
	public void deleteWorkCaseRecord(WorkCaseRecord wcr);
	
	public WorkCaseRecord queryWorkCaseRecord(int id);
	
}
