package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Origin;

public interface OriginDAO {
	
	public void addOrigin(Origin origin);
	
	public void updateOrigin(Origin origin);
	
	public void deleteOrigin(Origin origin);
	
	public Origin queryOrigin(int id);
	
	public List<Origin> QueryOriginByPageSize(int row,int PageSize);
	
	public List<Origin> QueryOriginByProblemId(int ProblemId);
	
}
