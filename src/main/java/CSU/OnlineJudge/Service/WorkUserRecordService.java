package CSU.OnlineJudge.Service;

import CSU.OnlineJudge.Model.WorkUserRecord;

public interface WorkUserRecordService {

	public void addWorkUserRecord(WorkUserRecord wur);
	
	public void deleteWorkUserRecord(WorkUserRecord wur);
	
	public void updateWorkUserRecord(WorkUserRecord wur);
	
	public WorkUserRecord queryWorkUserRecord(int id);
	
}
