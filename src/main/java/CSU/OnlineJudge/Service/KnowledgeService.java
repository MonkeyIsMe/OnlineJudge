package CSU.OnlineJudge.Service;

import CSU.OnlineJudge.Model.Knowledge;

public interface KnowledgeService {

	
	public void addKnowledge(Knowledge knowledge);
	
	public void updateKnowledge(Knowledge knowledge);
	
	public void deleteKnowledge(Knowledge knowledge);
	
	public Knowledge queryKnowledge(int id);
}
