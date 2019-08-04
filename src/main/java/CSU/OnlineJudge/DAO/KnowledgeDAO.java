package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Knowledge;

public interface KnowledgeDAO {
	
	public void addKnowledge(Knowledge knowledge);
	
	public void updateKnowledge(Knowledge knowledge);
	
	public void deleteKnowledge(Knowledge knowledge);
	
	public Knowledge queryKnowledge(int id);
	
	public List<Knowledge> QueryKnowledgeByPageSize(int rows,int PageSize);
}
