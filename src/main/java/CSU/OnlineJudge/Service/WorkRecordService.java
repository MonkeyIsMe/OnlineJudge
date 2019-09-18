package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.WorkRecord;

public interface WorkRecordService {
	
	public void addWorkRecord(WorkRecord wr);
	
	public void deleteWorkRecord(WorkRecord wr);
	
	public void updateWorkRecord(WorkRecord wr);
	
	public WorkRecord queryWorkRecord(int id);
	
	public List<WorkRecord> QueryWorkRecordByPageSizeWithProblemId(int row,int PageSize,int ProblemId);
	
	public List<WorkRecord> QueryWorkRecordByPageSizeWithWorkId(int row,int PageSize,int WorkId);
	
	public int CountWorkRecordByWorkId(int WorkId);
}
