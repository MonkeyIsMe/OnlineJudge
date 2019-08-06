package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkDAO;
import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class WorkDAOImpl extends HibernateDaoSupport implements WorkDAO{

	public int addWork(Work work) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(work);
		int id = work.getWorkId();
		return id;
	}

	public void deleteWork(Work work) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(work);
	}

	public void updateWork(Work work) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(work);
	}

	public Work queryWork(int id) {
		// TODO Auto-generated method stub
		
		Work result = null;
		result = getHibernateTemplate().get(Work.class, id);
		return result;
	}

	public List<Work> QueryWorkByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Work>>() {
			

			public List<Work> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Work";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Work> list = query.list();
				return list;
			}
		});
	}

	public List<Work> QueryWorkByPageSizeWithClassId(final int row, final int PageSize, final int ClassId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Work>>() {
			

			public List<Work> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Work where class_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ClassId);
				List<Work> list = query.list();
				return list;
			}
		});
	}


	public List<Work> QueryWorkByPageSizeWithFlag(final int row, final int PageSize, final int Flag) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Work>>() {
			

			public List<Work> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Work where work_flag = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, Flag);
				List<Work> list = query.list();
				return list;
			}
		});
	}

	public List<Work> QueryWorkByPageSizeWithOwner(final int row, final int PageSize, final String WorkOwner) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Work>>() {
			

			public List<Work> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Work where work_owner = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, WorkOwner);
				List<Work> list = query.list();
				return list;
			}
		});
	}

	public List<Work> QueryWorkByPageSizeWithClassIdFlag(final int row, final int PageSize, final int ClassId, final int Flag) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Work>>() {
			

			public List<Work> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Work where class_id = ? and work_flag = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ClassId);
				query.setParameter(1, Flag);
				List<Work> list = query.list();
				return list;
			}
		});
	}

	public int CountWork() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Work as work";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

}
