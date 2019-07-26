package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.KnowledgeProblem;

public interface KnowledgeProblemService {

	public void addKnowledgeProblem(KnowledgeProblem kp);
	
	public void deleteKnowledgeProblem(KnowledgeProblem kp);
	
	public void updateKnowledgeProblem(KnowledgeProblem kp);
	
	public KnowledgeProblem queryKnowledgeProblem(int id);
	
	public List<KnowledgeProblem> queryKnowledgeProblemByProblemId(int ProblemId);
	
	public List<KnowledgeProblem> queryKnowledgeProblemByKnowledge(int KnowledgeId);
}
