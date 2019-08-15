package CSU.OnlineJudge.DAO.Impl;

import java.io.Serializable;
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

	public int addProblem(Problem problem) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(problem);
		System.out.println(problem);
		int id = problem.getProblemId();
		return id;
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
				String hql = "from Problem where problem_flag = ? and problem_name like :ProblemName";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, flag);
				query.setString("ProblemName", "%"+ProblemName+"%");
				List<Problem> list = query.list();
				return list;
			}
		});
	}

	public List<Problem> VagueByPageSizeWithFlagByPeople(final int row, final int PageSize, final String PeopleName) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Problem>>() {
			

			public List<Problem> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Problem where problem_people like :PeopleName";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setString("PeopleName", "%"+PeopleName+"%");
				List<Problem> list = query.list();
				return list;
			}
		});
	}

	public List<Object[]> GetProblemOutInfo(final int row, final int PageSize, final int flag) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {

			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "select ProblemId,ProblemName,SubmissionTimes,AcceptTimes  from Problem where problem_flag = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, flag);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}

	public int CountProblem() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Problem as problem";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Object[]> GetProblemManagerInfo(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {

			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "select ProblemId,ProblemName,ProblemDegree,ProblemPeople,PublicOrNot from Problem";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Object[]> list = query.list();
				return list;
			}
		});
	}

	public Object addMutiplyProblem(final List<Problem> problem) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < problem.size(); i ++) {
					session.save(problem.get(i));
                    if (i % 50 == 0) {  
                        session.flush();  
                        session.clear();  
                    }  
				}
				return null;
			}
			
		});
	}


}
