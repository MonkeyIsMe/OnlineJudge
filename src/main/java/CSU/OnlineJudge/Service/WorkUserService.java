package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.WorkUser;

public interface WorkUserService {

	public void AddWorkUser(WorkUser wu);
	
	public void DeleteWorkUser(WorkUser wu);
	
	public void UpdateWorkUser(WorkUser wu);
	
	public WorkUser QueryWorkUser(int id);
	
	public List<WorkUser> QueryWorkUserByPageSize(int row,int PageSize,int WorkId);
	
	public int CountWorkUser(int WorkId);
	
}
