package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.KnowledgeProblemDAO;
import CSU.OnlineJudge.Model.KnowledgeProblem;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class KnowledgeProblemDAOImpl extends HibernateDaoSupport implements KnowledgeProblemDAO{

	public void addKnowledgeProblem(KnowledgeProblem kp) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(kp);
	}

	public void deleteKnowledgeProblem(KnowledgeProblem kp) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(kp);
	}

	public void updateKnowledgeProblem(KnowledgeProblem kp) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(kp);
	}

	public KnowledgeProblem queryKnowledgeProblem(int id) {
		// TODO Auto-generated method stub
		KnowledgeProblem result = null;
		result = getHibernateTemplate().get(KnowledgeProblem.class, id);
		return result;
	}

	public List<KnowledgeProblem> queryKnowledgeProblemByProblemId(final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<KnowledgeProblem>>() {

			public List<KnowledgeProblem> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from KnowledgeProblem where problem_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ProblemId);
				List<KnowledgeProblem> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public List<KnowledgeProblem> queryKnowledgeProblemByKnowledge(final int KnowledgeId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<KnowledgeProblem>>() {

			public List<KnowledgeProblem> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from KnowledgeProblem where knowledge_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, KnowledgeId);
				List<KnowledgeProblem> result = null;
				result = query.list();
				return result;
			}
		});
	}

}
