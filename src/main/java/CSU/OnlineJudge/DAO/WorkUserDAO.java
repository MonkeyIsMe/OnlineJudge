package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.WorkUser;

public interface WorkUserDAO {
	
	public void addWorkUser(WorkUser wu);
	
	public void deleteWorkUser(WorkUser wu);
	
	public void updateWorkUser(WorkUser wu);
	
	public WorkUser queryWorkUser(int id);
	
	public List<WorkUser> QueryWorkUserByPageSize(int row,int PageSize,int WorkId);
	
	public int CountWorkUser(int WorkId);
}
