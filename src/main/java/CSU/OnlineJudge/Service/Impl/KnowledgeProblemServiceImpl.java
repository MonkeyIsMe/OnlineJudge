package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.KnowledgeProblemDAO;
import CSU.OnlineJudge.DAO.Impl.KnowledgeProblemDAOImpl;
import CSU.OnlineJudge.Model.KnowledgeProblem;
import CSU.OnlineJudge.Service.KnowledgeProblemService;

@Transactional
public class KnowledgeProblemServiceImpl implements KnowledgeProblemService{

	private KnowledgeProblemDAO kpd;
	
	
	public KnowledgeProblemDAO getKpd() {
		return kpd;
	}

	public void setKpd(KnowledgeProblemDAO kpd) {
		this.kpd = kpd;
	}
	
	public void addKnowledgeProblem(KnowledgeProblem kp) {
		// TODO Auto-generated method stub
		kpd.addKnowledgeProblem(kp);
	}

	public void deleteKnowledgeProblem(KnowledgeProblem kp) {
		// TODO Auto-generated method stub
		kpd.deleteKnowledgeProblem(kp);
	}

	public void updateKnowledgeProblem(KnowledgeProblem kp) {
		// TODO Auto-generated method stub
		kpd.updateKnowledgeProblem(kp);
	}

	public KnowledgeProblem queryKnowledgeProblem(int id) {
		// TODO Auto-generated method stub
		return kpd.queryKnowledgeProblem(id);
	}

	public List<KnowledgeProblem> queryKnowledgeProblemByProblemId(int ProblemId) {
		// TODO Auto-generated method stub
		return kpd.queryKnowledgeProblemByProblemId(ProblemId);
	}

	public List<KnowledgeProblem> queryKnowledgeProblemByKnowledge(int KnowledgeId) {
		// TODO Auto-generated method stub
		return kpd.queryKnowledgeProblemByKnowledge(KnowledgeId);
	}

	public List<KnowledgeProblem> queryKnowledgeProblemByKnowledgePageSize(int KnowledgeId, int rows, int PageSize) {
		// TODO Auto-generated method stub
		return kpd.queryKnowledgeProblemByKnowledgePageSize(KnowledgeId, rows, PageSize);
	}

	public int CountProblem(int KnowledgeId) {
		// TODO Auto-generated method stub
		return kpd.CountProblem(KnowledgeId);
	}

}
