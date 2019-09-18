package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkUserRecordDAO;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.WorkUserRecord;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class WorkUserRecordDAOImpl extends HibernateDaoSupport implements WorkUserRecordDAO{

	public void addWorkUserRecord(WorkUserRecord wur) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(wur);
	}

	public void deleteWorkUserRecord(WorkUserRecord wur) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(wur);
	}

	public void updateWorkUserRecord(WorkUserRecord wur) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(wur);
	}

	public WorkUserRecord queryWorkUserRecord(int id) {
		// TODO Auto-generated method stub
		WorkUserRecord result = null;
		result = getHibernateTemplate().get(WorkUserRecord.class, id);
		return result;
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemId(final int row, final int PageSize, final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where problem_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithUserAccount(final int row, final int PageSize,
			final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where user_account = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, UserAccount);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithWorkId(final int row, final int PageSize, final int WorkId) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where work_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, WorkId);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemWork(final int row, final int PageSize, final int ProblemId,
			final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where problem_id = ? and work_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				query.setParameter(1, WorkId);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithAccountWork(final int row, final int PageSize, final String UserAccount,
			final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where user_account = ? and work_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, UserAccount);
				query.setParameter(1, WorkId);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithProblemAccount(final int row, final int PageSize, final int ProblemId,
			final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where problem_id = ? and user_account = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				query.setParameter(1, UserAccount);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkUserRecord> queryWorkUserRecordByProblemWorkAccountPageSize(final int row, final int PageSize, final int ProblemId,
			final int WorkId, final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where problem_id = ? and work_id = ? and user_account = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				query.setParameter(1, WorkId);
				query.setParameter(2, UserAccount);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkUserRecord> queryWorkUserRecordByPageSizeWithUserIdWork(final int row, final int PageSize, final int UserId,
			final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkUserRecord>>() {
			

			public List<WorkUserRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkUserRecord where user_id = ? and work_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, UserId);
				query.setParameter(1, WorkId);
				List<WorkUserRecord> list = query.list();
				return list;
			}
		});
	}

	public int CountWorkUserRecordWithUserIdWorkId(int UserId, int WorkId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from WorkUserRecord as WorkUserRecord where work_id = '"+WorkId+"'" +"AND user_id = '"+UserId+"'";  
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}


}
