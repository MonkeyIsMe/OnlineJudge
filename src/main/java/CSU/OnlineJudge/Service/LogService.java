package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Log;

public interface LogService {

	
	public void addLog(Log log);
	
	public void deleteLog(Log log);
	
	public void updateLog(Log log);
	
	public Log queryLog(int id);
	
	
}
