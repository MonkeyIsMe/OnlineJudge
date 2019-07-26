package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.LogDAO;
import CSU.OnlineJudge.DAO.Impl.LogDAOImpl;
import CSU.OnlineJudge.Model.Log;
import CSU.OnlineJudge.Service.LogService;

@Transactional
public class LogServiceImpl implements LogService{

	private LogDAO ld;
	
	public LogDAO getLd() {
		return ld;
	}

	public void setLd(LogDAO ld) {
		this.ld = ld;
	}

	public void addLog(Log log) {
		// TODO Auto-generated method stub
		ld.addLog(log);
	}

	public void deleteLog(Log log) {
		// TODO Auto-generated method stub
		ld.deleteLog(log);
	}

	public void updateLog(Log log) {
		// TODO Auto-generated method stub
		ld.updateLog(log);
	}

	public Log queryLog(int id) {
		// TODO Auto-generated method stub
		return ld.queryLog(id);
	}


}
