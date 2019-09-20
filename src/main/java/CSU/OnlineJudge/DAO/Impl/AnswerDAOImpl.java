package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.AnswerDAO;
import CSU.OnlineJudge.Model.Answer;

@Transactional
public class AnswerDAOImpl extends HibernateDaoSupport implements AnswerDAO{

	public void addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(answer);
	}

	public void updateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(answer);
	}

	public void deleteAnswer(Answer answer) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(answer);
	}

	public Answer queryAnswer(int id) {
		// TODO Auto-generated method stub
		Answer result = null;
		result = getHibernateTemplate().get(Answer.class, id);
		return result;
	}

	public List<Answer> QueryAnswerByProblemIdPageSize(final int ProblemId, final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Answer>>() {

			public List<Answer> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Answer where problem_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, ProblemId);
				List<Answer> list = query.list();
				return list;
			}
		});
	}

	public List<Answer> QueryAnswerByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Answer>>() {

			public List<Answer> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Answer";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Answer> list = query.list();
				return list;
			}
		});
	}

	public int CountAnswer() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Answer as answer";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public Object deleteMutiplyAnswer(final List<Answer> AnswerList) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < AnswerList.size(); i ++) {
					session.delete(AnswerList.get(i));
                    if (i % 50 == 0) {  
                        session.flush();  
                        session.clear();  
                    }  
				}
				return null;
			}
			
		});
	}

	public List<Answer> QueryAnswerByProblemId(final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Answer>>() {

			public List<Answer> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Answer where problem_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ProblemId);
				List<Answer> list = query.list();
				return list;
			}
		});
	}

	public int CountAnswerByProblem(int ProblemId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Answer as Answer where problem_id = '"+ProblemId+"'";  
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

}
