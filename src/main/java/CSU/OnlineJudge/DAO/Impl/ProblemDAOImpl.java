package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.ProblemDAO;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class ProblemDAOImpl extends HibernateDaoSupport implements ProblemDAO{

	public void addProblem(Problem problem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(problem);
	}

	public void updateProblem(Problem problem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(problem);
	}

	public void deleteProblem(Problem problem) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(problem);
	}

	public Problem queryProblem(int id) {
		// TODO Auto-generated method stub
		Problem result = null;
		result = getHibernateTemplate().get(Problem.class, id);
		return result;
	}

	public List<Problem> GetAllProblemByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Problem>>() {
			

			public List<Problem> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Problem";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Problem> list = query.list();
				return list;
			}
		});
	}

	public List<Problem> GetProblemByPageSizeWithFlag(final int row, final int PageSize, final int flag) {
		// TODO Auto-generated method stub
		
		return getHibernateTemplate().execute(new HibernateCallback<List<Problem>>() {
			

			public List<Problem> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Problem where problem_flag = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, flag);
				List<Problem> list = query.list();
				return list;
			}
		});
		
	}

	public List<Problem> VagueByPageSizeWithFlagByName(final int row, final int PageSize, final int flag, final String ProblemName) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Problem>>() {
			

			public List<Problem> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Problem where problem_flag = ? and problem_name = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, flag);
				query.setParameter(1, ProblemName);
				List<Problem> list = query.list();
				return list;
			}
		});
	}

	public List<Problem> VagueByPageSizeWithFlagByPeople(int row, int PageSize, String PeopleName) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Problem> list = null;
		try {
			session.beginTransaction();
			String hql = "from Problem where problem_people like :PeopleName";
			Query query = session.createQuery(hql);
			query.setFirstResult(PageSize*(row-1));
			query.setMaxResults(PageSize);
			query.setString("PeopleName", "%"+PeopleName+"%");
			list = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
			return list;
		}
		// TODO Auto-generated method stub
		session.close();
		return list;
	}


}
