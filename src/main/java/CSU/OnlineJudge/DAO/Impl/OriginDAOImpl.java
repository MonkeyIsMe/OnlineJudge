package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import CSU.OnlineJudge.DAO.OriginDAO;
import CSU.OnlineJudge.Model.Origin;
import CSU.OnlineJudge.Model.Problem;

public class OriginDAOImpl extends HibernateDaoSupport implements OriginDAO{

	public void addOrigin(Origin origin) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(origin);
	}

	public void updateOrigin(Origin origin) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(origin);
	}

	public void deleteOrigin(Origin origin) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(origin);
	}

	public Origin queryOrigin(int id) {
		// TODO Auto-generated method stub
		Origin origin = null;
		origin = getHibernateTemplate().get(Origin.class, id);
		return origin;
	}

	public List<Origin> QueryOriginByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Origin>>() {
			

			public List<Origin> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Origin";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Origin> list = query.list();
				return list;
			}
		});
	}

	public List<Origin> QueryOriginByProblemId(final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Origin>>() {
			

			public List<Origin> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Origin where problem_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ProblemId);
				List<Origin> list = query.list();
				return list;
			}
		});
	}

}
