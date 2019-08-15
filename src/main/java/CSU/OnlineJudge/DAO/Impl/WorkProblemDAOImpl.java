package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkProblemDAO;
import CSU.OnlineJudge.Model.WorkProblem;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class WorkProblemDAOImpl extends HibernateDaoSupport implements WorkProblemDAO{

	public void addWorkProblem(WorkProblem wp) {
		// TODO Auto-generated method stub
		System.out.println("dao = " + wp.toString());
		getHibernateTemplate().save(wp);
	}

	public void deleteWorkProblem(WorkProblem wp) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(wp);
	}

	public void updateWorkProblem(WorkProblem wp) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(wp);
	}

	public WorkProblem queryWorkProblem(int id) {
		// TODO Auto-generated method stub
		WorkProblem result = null;
		result = getHibernateTemplate().get(WorkProblem.class, id);
		return result;
	}

	public List<WorkProblem> QueryWorkProblemByWorkId(final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkProblem>>() {

			public List<WorkProblem> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkProblem where work_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, WorkId);
				List<WorkProblem> result = null;
				result = query.list();
				return result;
			}
		});
	}

}
