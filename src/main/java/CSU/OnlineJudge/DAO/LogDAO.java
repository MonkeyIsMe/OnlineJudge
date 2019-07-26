package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Log;

public interface LogDAO {
	
	public void addLog(Log log);
	
	public void deleteLog(Log log);
	
	public void updateLog(Log log);
	
	public Log queryLog(int id);
	
	
}
