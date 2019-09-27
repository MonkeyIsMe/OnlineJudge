package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.SubmissionDAO;
import CSU.OnlineJudge.Model.Submission;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class SubmissionDAOImpl extends HibernateDaoSupport implements SubmissionDAO{

	public int addSubmission(Submission submission) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(submission);
		int id = submission.getSubmissionId();
		return id;
	}

	public void updateSubmission(Submission submission) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(submission);
	}

	public void deleteSubmission(Submission submission) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(submission);
	}

	public Submission querySubmission(int id) {
		// TODO Auto-generated method stub
		Submission result = null;
		result = getHibernateTemplate().get(Submission.class, id);
		return result;
	}

	public List<Submission> QuerySubmissionByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {
			

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccount(final int row, final int PageSize, final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {
			

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where user_account = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, UserAccount);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithProblemId(final int row, final int PageSize, final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where problem_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithProblemIdResult(final int row, final int PageSize, final int ProblemId,
			final String result) {
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {
			

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where problem_id = ? and submission_result = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				query.setParameter(1, result);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccountResult(final int row, final int PageSize, final String UserAccount,
			final String result) {
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {
			

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where user_account = ? and submission_result = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, UserAccount);
				query.setParameter(1, result);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithResult(final int row, final int PageSize, final String Result) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {
			

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where submission_result = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, Result);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccountProblem(final int row, final int PageSize, final String UserAccount,
			final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {
			

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where problem_id = ? and user_account = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				query.setParameter(1, UserAccount);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccountResultProblem(final int row, final int PageSize,
			final String UserAccount, final String result, final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {
			

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where problem_id = ? and user_account = ? and submission_result = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, UserAccount);
				query.setParameter(1, result);
				query.setParameter(2, ProblemId);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public List<Submission> QuerySubmissionByPageSizeWithWorkId(final int row, final int PageSize, final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Submission>>() {

			public List<Submission> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Submission where work_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, WorkId);
				List<Submission> list = query.list();
				return list;
			}
		});
	}

	public int CountSubmission() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Submission as Submission";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}



}
