package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Knowledge;

public interface KnowledgeService {

	
	public void addKnowledge(Knowledge knowledge);
	
	public void updateKnowledge(Knowledge knowledge);
	
	public void deleteKnowledge(Knowledge knowledge);
	
	public Knowledge queryKnowledge(int id);
	
	public List<Knowledge> QueryKnowledgeByPageSize(int rows,int PageSize);
	
	public int CountKnowledge();
	
	public List<Knowledge> QueryAllKnowledge();
}
