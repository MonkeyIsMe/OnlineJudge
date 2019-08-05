package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.KnowledgeDAO;
import CSU.OnlineJudge.DAO.Impl.KnowledgeDAOImpl;
import CSU.OnlineJudge.Model.Knowledge;
import CSU.OnlineJudge.Service.KnowledgeService;

@Transactional
public class KnowledgeServiceImpl implements  KnowledgeService{

	private KnowledgeDAO kd;
	
	public KnowledgeDAO getKd() {
		return kd;
	}

	public void setKd(KnowledgeDAO kd) {
		this.kd = kd;
	}

	public void addKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		kd.addKnowledge(knowledge);
	}

	public void updateKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		kd.updateKnowledge(knowledge);
	}

	public void deleteKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		kd.deleteKnowledge(knowledge);
	}

	public Knowledge queryKnowledge(int id) {
		// TODO Auto-generated method stub
		return kd.queryKnowledge(id);
	}

	public List<Knowledge> QueryKnowledgeByPageSize(int rows, int PageSize) {
		// TODO Auto-generated method stub
		return kd.QueryKnowledgeByPageSize(rows, PageSize);
	}

	public int CountKnowledge() {
		// TODO Auto-generated method stub
		return kd.CountKnowledge();
	}

}
