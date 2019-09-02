package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CommentDAO;
import CSU.OnlineJudge.Model.Comment;

@Transactional
public class CommentDAOImpl extends HibernateDaoSupport implements CommentDAO{

	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(comment);
	}

	public void deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(comment);
	}

	public void updateComment(Comment comment) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(comment);
	}

	public Comment queryComment(int id) {
		// TODO Auto-generated method stub
		Comment result = null;
		result = getHibernateTemplate().get(Comment.class, id);
		return result;
	}

	public List<Comment> QueryCommentByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Comment>>() {

			public List<Comment> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Comment";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Comment> list = query.list();
				return list;
			}
		});
	}

	public List<Comment> QueryCommentByAnswerIdPageSize(final int AnswerId, final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Comment>>() {

			public List<Comment> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Comment where answer_id = ?";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				query.setParameter(0, AnswerId);
				List<Comment> list = query.list();
				return list;
			}
		});
	}

	public int CountComment() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Comment as comment";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public Object deleteMutiplyAnswer(final List<Comment> CommentList) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < CommentList.size(); i ++) {
					session.delete(CommentList.get(i));
                    if (i % 50 == 0) {  
                        session.flush();  
                        session.clear();  
                    }  
				}
				return null;
			}
			
		});
	}

	public List<Comment> QueryCommentByAnswerId(final int answer_id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Comment>>() {

			public List<Comment> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Comment where answer_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, answer_id);
				List<Comment> list = query.list();
				return list;
			}
		});
	}

}
