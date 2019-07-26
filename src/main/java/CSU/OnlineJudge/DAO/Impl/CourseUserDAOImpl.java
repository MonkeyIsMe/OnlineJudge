package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CourseUserDAO;
import CSU.OnlineJudge.Model.CourseUser;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class CourseUserDAOImpl extends HibernateDaoSupport implements CourseUserDAO{


	public void addCourseUser(CourseUser cu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(cu);
	}

	public void updateCourseUser(CourseUser cu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(cu);
	}

	public void deleteCourseUser(CourseUser cu) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(cu);
	}

	public CourseUser queryCourseUser(int id) {
		// TODO Auto-generated method stub
		CourseUser result = null;
		result =  getHibernateTemplate().get(CourseUser.class, id);
		return result;
	}

	public List<CourseUser> QueryCourseUserByUserID(final int UserId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<CourseUser>>() {

			public List<CourseUser> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from CourseUser where user_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, UserId);
				List<CourseUser> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public List<CourseUser> QueryCourseUserByUserAccount(final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<CourseUser>>() {

			public List<CourseUser> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from CourseUser where user_account = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, UserAccount);
				List<CourseUser> result = null;
				result = query.list();
				return result;
			}
		});
	}

}
