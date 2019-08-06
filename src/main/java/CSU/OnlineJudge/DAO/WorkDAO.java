package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Work;

public interface WorkDAO {
	
	public int addWork(Work work);
	
	public void deleteWork(Work work);
	
	public void updateWork(Work work);
	
	public Work queryWork(int id);
	
	public List<Work> QueryWorkByPageSize(int row,int PageSize);
	
	public List<Work> QueryWorkByPageSizeWithClassId(int row,int PageSize,int ClassId);
	
	public List<Work> QueryWorkByPageSizeWithClassIdFlag(int row,int PageSize,int ClassId,int Flag);
	
	public List<Work> QueryWorkByPageSizeWithFlag(int row,int PageSize,int Flag);
	
	public List<Work> QueryWorkByPageSizeWithOwner(int row,int PageSize,String WorkOwner);
	
	public int CountWork();
}
