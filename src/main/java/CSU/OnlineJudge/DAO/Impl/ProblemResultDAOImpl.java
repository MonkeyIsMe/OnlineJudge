package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import CSU.OnlineJudge.DAO.ProblemResultDAO;
import CSU.OnlineJudge.Model.CourseUser;
import CSU.OnlineJudge.Model.ProblemResult;

public class ProblemResultDAOImpl extends HibernateDaoSupport implements ProblemResultDAO{

	public void addProblemResult(ProblemResult pr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(pr);
	}

	public void updateProblemResult(ProblemResult pr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(pr);
	}

	public void deleteProblemResult(ProblemResult pr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(pr);
	}

	public ProblemResult queryProblemResult(int id) {
		// TODO Auto-generated method stub
		ProblemResult result = null;
		getHibernateTemplate().get(ProblemResult.class, id);
		return result;
	}

	public List<ProblemResult> GeryProblemResultByProblemResultAccount(final int ProblemId, final String ProblemResult,
			final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<ProblemResult>>() {

			public List<ProblemResult> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from ProblemResult where problem_id = ? and problem_result = ? and user_account = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ProblemId);
				query.setParameter(1, ProblemResult);
				query.setParameter(2, UserAccount);
				List<ProblemResult> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public List<ProblemResult> GeryProblemResultByProblemAccount(final int ProblemId, final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<ProblemResult>>() {

			public List<ProblemResult> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from ProblemResult where problem_id = ?  and user_account = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ProblemId);
				query.setParameter(1, UserAccount);
				List<ProblemResult> result = null;
				result = query.list();
				return result;
			}
		});
	}

}
