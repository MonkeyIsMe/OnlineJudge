package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkRecordDAO;
import CSU.OnlineJudge.DAO.Impl.WorkRecordDAOImpl;
import CSU.OnlineJudge.Model.WorkRecord;
import CSU.OnlineJudge.Service.WorkRecordService;

@Transactional
public class WorkRecordServiceImpl implements WorkRecordService{
	
	private WorkRecordDAO wrd;
	
	public WorkRecordDAO getWrd() {
		return wrd;
	}

	public void setWrd(WorkRecordDAO wrd) {
		this.wrd = wrd;
	}

	public void addWorkRecord(WorkRecord wr) {
		// TODO Auto-generated method stub
		wrd.addWorkRecord(wr);
	}

	public void deleteWorkRecord(WorkRecord wr) {
		// TODO Auto-generated method stub
		wrd.deleteWorkRecord(wr);
	}

	public void updateWorkRecord(WorkRecord wr) {
		// TODO Auto-generated method stub
		wrd.updateWorkRecord(wr);
	}

	public WorkRecord queryWorkRecord(int id) {
		// TODO Auto-generated method stub
		return wrd.queryWorkRecord(id);
	}

	public List<WorkRecord> QueryWorkRecordByPageSizeWithProblemId(int row, int PageSize, int ProblemId) {
		// TODO Auto-generated method stub
		return wrd.QueryWorkRecordByPageSizeWithProblemId(row, PageSize, ProblemId);
	}

	public List<WorkRecord> QueryWorkRecordByPageSizeWithWorkId(int row, int PageSize, int WorkId) {
		// TODO Auto-generated method stub
		return wrd.QueryWorkRecordByPageSizeWithWorkId(row, PageSize, WorkId);
	}

	public int CountWorkRecordByWorkId(int WorkId) {
		// TODO Auto-generated method stub
		return wrd.CountWorkRecordByWorkId(WorkId);
	}

	public WorkRecord QueryWorkRecordByWorkId(int WorkId) {
		// TODO Auto-generated method stub
		List<WorkRecord> result = wrd.QueryWorkRecordByWorkId(WorkId);
		if(result.size() == 0) return null;
		return result.get(0);
	}


}
