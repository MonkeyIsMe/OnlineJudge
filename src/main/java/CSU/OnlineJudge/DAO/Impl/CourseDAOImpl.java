package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CourseDAO;
import CSU.OnlineJudge.Model.Course;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class CourseDAOImpl extends HibernateDaoSupport implements CourseDAO{

	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(course);
	}

	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(course);
	}

	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(course);
	}

	public Course queryCourse(int id) {
		// TODO Auto-generated method stub
		Course result = null;
		result = getHibernateTemplate().get(Course.class, id);
		return result;
	}


	public List<Course> QueryCourseByUserAccount(final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Course>>() {

			public List<Course> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Course where user_account = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, UserAccount);
				List<Course> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public int CountCourse() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Course as course";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Course> QueryCourseByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Course>>() {
			

			public List<Course> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Course";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<Course> list = query.list();
				return list;
			}
		});
	}

	public List<Course> QueryAllCourse() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Course>>() {

			public List<Course> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Course";
				Query query = session.createQuery(hql);
				List<Course> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public Object addMutiplyCourse(final List<Course> course) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < course.size(); i ++) {
					session.save(course.get(i));
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
