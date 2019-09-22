package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.NoticeDAO;
import CSU.OnlineJudge.Model.Notice;

@Transactional
public class NoticeDAOImpl extends HibernateDaoSupport implements NoticeDAO{

	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(notice);
	}

	public void deleteNotice(Notice notice) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(notice);
	}

	public void updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(notice);
	}

	public Notice queryNotice(int id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Notice.class, id);
	}

	public List<Notice> QueryNoticeByPageSize(final int rows, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {

			public List<Notice> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Notice";
				Query query = session.createQuery(hql).setFirstResult(
                        (rows - 1) * PageSize).setMaxResults(PageSize);;
				List<Notice> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public int CountNotice() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Notice as Notice";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

}
