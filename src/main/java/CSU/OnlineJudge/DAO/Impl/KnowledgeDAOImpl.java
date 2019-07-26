package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.KnowledgeDAO;
import CSU.OnlineJudge.Model.Knowledge;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class KnowledgeDAOImpl extends HibernateDaoSupport implements KnowledgeDAO{

	public void addKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(knowledge);
	}

	public void updateKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(knowledge);
	}

	public void deleteKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(knowledge);
	}

	public Knowledge queryKnowledge(int id) {
		// TODO Auto-generated method stub
		Knowledge result = null;
		result =  getHibernateTemplate().get(Knowledge.class, id);
		return result;
	}


}
