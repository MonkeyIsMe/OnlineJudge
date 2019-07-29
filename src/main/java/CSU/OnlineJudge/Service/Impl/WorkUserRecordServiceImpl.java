package CSU.OnlineJudge.Service.Impl;

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

}
