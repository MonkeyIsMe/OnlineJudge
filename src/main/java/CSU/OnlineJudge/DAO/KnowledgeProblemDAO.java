package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.KnowledgeProblem;

public interface KnowledgeProblemDAO {
	
	public void addKnowledgeProblem(KnowledgeProblem kp);
	
	public void deleteKnowledgeProblem(KnowledgeProblem kp);
	
	public void updateKnowledgeProblem(KnowledgeProblem kp);
	
	public KnowledgeProblem queryKnowledgeProblem(int id);
	
	public List<KnowledgeProblem> queryKnowledgeProblemByProblemId(int ProblemId);
	
	public List<KnowledgeProblem> queryKnowledgeProblemByKnowledge(int KnowledgeId);
	
	public List<KnowledgeProblem> queryKnowledgeProblemByKnowledgePageSize(int KnowledgeId,int rows,int PageSize);
}
