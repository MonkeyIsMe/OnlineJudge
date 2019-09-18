package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkRecordDAO;
import CSU.OnlineJudge.Model.WorkRecord;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class WorkRecordDAOImpl extends HibernateDaoSupport implements WorkRecordDAO{

	public void addWorkRecord(WorkRecord wr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(wr);
	}

	public void deleteWorkRecord(WorkRecord wr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(wr);
	}

	public void updateWorkRecord(WorkRecord wr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(wr);
	}

	public WorkRecord queryWorkRecord(int id) {
		// TODO Auto-generated method stub
		WorkRecord result = null;
		result = getHibernateTemplate().get(WorkRecord.class, id);
		return result;
	}

	public List<WorkRecord> QueryWorkRecordByPageSizeWithProblemId(final int row, final int PageSize, final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkRecord>>() {
			

			public List<WorkRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkRecord where problem_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				List<WorkRecord> list = query.list();
				return list;
			}
		});
	}

	public List<WorkRecord> QueryWorkRecordByPageSizeWithWorkId(final int row, final int PageSize, final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkRecord>>() {
			

			public List<WorkRecord> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkRecord where work_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, WorkId);
				List<WorkRecord> list = query.list();
				return list;
			}
		});
	}

	public int CountWorkRecordByWorkId(int WorkId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from WorkRecord as WorkRecord where work_id = '"+WorkId+"'";  
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}



}
