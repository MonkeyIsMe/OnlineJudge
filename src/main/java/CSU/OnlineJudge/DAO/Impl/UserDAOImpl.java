package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.UserDAO;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO{

	public void addUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(user);
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(user);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(user);
	}

	public User queryUser(int id) {
		// TODO Auto-generated method stub
		User result = null;
		result = getHibernateTemplate().get(User.class, id);
		return result;
	}

	public List<User> QueryUserByPageSize(final int row, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
			

			public List<User> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from User";
				Query query = session.createQuery(hql).setFirstResult(
                        (row - 1) * PageSize).setMaxResults(PageSize);
				List<User> list = query.list();
				return list;
			}
		});
	}

	public List<User> QueryUserByName(final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
			

			public List<User> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from User where user_account = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, UserAccount);
				List<User> list = query.list();
				return list;
			}
		});
	}

	public int CountUser() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User as User";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

}
