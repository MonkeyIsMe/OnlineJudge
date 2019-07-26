package CSU.OnlineJudge.Service.Impl;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkCaseRecordDAO;
import CSU.OnlineJudge.DAO.Impl.WorkCaseRecordDAOImpl;
import CSU.OnlineJudge.Model.WorkCaseRecord;
import CSU.OnlineJudge.Service.WorkCaseRecordService;

@Transactional
public class WorkCaseRecordServiceImpl implements WorkCaseRecordService{
	
	private WorkCaseRecordDAO wcrd;
	
	public WorkCaseRecordDAO getWcrd() {
		return wcrd;
	}

	public void setWcrd(WorkCaseRecordDAO wcrd) {
		this.wcrd = wcrd;
	}

	public void addWorkCaseRecord(WorkCaseRecord wcr) {
		// TODO Auto-generated method stub
		wcrd.addWorkCaseRecord(wcr);
	}

	public void updateWorkCaseRecord(WorkCaseRecord wcr) {
		// TODO Auto-generated method stub
		wcrd.updateWorkCaseRecord(wcr);
	}

	public void deleteWorkCaseRecord(WorkCaseRecord wcr) {
		// TODO Auto-generated method stub
		wcrd.deleteWorkCaseRecord(wcr);
	}

	public WorkCaseRecord queryWorkCaseRecord(int id) {
		// TODO Auto-generated method stub
		return wcrd.queryWorkCaseRecord(id);
	}

}
