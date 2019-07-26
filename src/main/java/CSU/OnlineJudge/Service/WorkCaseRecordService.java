package CSU.OnlineJudge.Service;

import CSU.OnlineJudge.Model.WorkCaseRecord;

public interface WorkCaseRecordService {

	
	public void addWorkCaseRecord(WorkCaseRecord wcr);
	
	public void updateWorkCaseRecord(WorkCaseRecord wcr);
	
	public void deleteWorkCaseRecord(WorkCaseRecord wcr);
	
	public WorkCaseRecord queryWorkCaseRecord(int id);
	
}
