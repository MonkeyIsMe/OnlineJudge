package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkUserDAO;
import CSU.OnlineJudge.Model.WorkUser;

@Transactional
public class WorkUserDAOImpl extends HibernateDaoSupport implements WorkUserDAO{

	public void addWorkUser(WorkUser wu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(wu);
	}

	public void deleteWorkUser(WorkUser wu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(wu);
	}

	public void updateWorkUser(WorkUser wu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(wu);
	}

	public WorkUser queryWorkUser(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(WorkUser.class, id);
	}

	public List<WorkUser> QueryWorkUserByPageSize(final int row, final int PageSize,final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUser>>() {
			
			public List<WorkUser> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUser where work_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, WorkId);
				List<WorkUser> list = query.list();
				return list;
			}
		});
	}

	public int CountWorkUser(int WorkId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from WorkUser as WorkUser where work_id = '"+WorkId+"'";  
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

}
