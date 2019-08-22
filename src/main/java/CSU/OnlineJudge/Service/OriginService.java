package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Origin;

public interface OriginService {
	
	public void AddOrigin(Origin origin);
	
	public void UpdateOrigin(Origin origin);
	
	public void DeleteOrigin(Origin origin);
	
	public Origin queryOrigin(int id);
	
	public List<Origin> QueryOriginByPageSize(int row,int PageSize);
	
	public List<Origin> QueryOriginByProblemId(int ProblemId);
}
