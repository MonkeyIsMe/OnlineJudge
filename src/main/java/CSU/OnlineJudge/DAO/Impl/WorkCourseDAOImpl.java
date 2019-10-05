package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkCourseDAO;
import CSU.OnlineJudge.Model.WorkCourse;
import CSU.OnlineJudge.Model.WorkCourse;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class WorkCourseDAOImpl extends HibernateDaoSupport implements WorkCourseDAO{

	public void addWorkCourse(WorkCourse wc) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(wc);
	}

	public void updateWorkCourse(WorkCourse wc) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(wc);
	}

	public void deleteWorkCourse(WorkCourse wc) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(wc);
	}

	public WorkCourse queryWorkCourse(int id) {
		// TODO Auto-generated method stub
		WorkCourse result = null;
		result = getHibernateTemplate().get(WorkCourse.class, id);
		return result;
	}

	public List<WorkCourse> QueryWorkCourseByClassId(final int ClassId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkCourse>>() {
			

			public List<WorkCourse> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkCourse where class_id = ?";
				Query query = session.createQuery(hql);
				List<WorkCourse> list = query.list();
				query.setParameter(0, ClassId);
				return list;
			}
		});
	}

	public List<WorkCourse> QueryWorkCourseByWorkId(final int WorkId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<WorkCourse>>() {
			

			public List<WorkCourse> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from WorkCourse where work_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, WorkId);
				List<WorkCourse> list = query.list();
				return list;
			}
		});
	}

	public Object addMutiplyWorkCourse(final List<WorkCourse> wc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < wc.size(); i ++) {
					//System.out.println(wc.toString());
					session.save(wc.get(i));
					//System.out.println(cu.toString());
                    if (i % 50 == 0) {  
                        session.flush();  
                        session.clear();  
                    }  
				}
				return null;
			}
			
		});
	}

	public Object deleteMutiplyWorkCourse(final List<WorkCourse> wc) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < wc.size(); i ++) {
					session.delete(wc.get(i));
					//System.out.println(cu.toString());
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
