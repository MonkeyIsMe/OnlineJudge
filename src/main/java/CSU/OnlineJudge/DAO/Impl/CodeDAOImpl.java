package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CodeDAO;
import CSU.OnlineJudge.Model.Code;

@Transactional
public class CodeDAOImpl extends HibernateDaoSupport implements CodeDAO{

	public void addCode(Code code) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(code);
		
	}

	public void deleteCode(Code code) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(code);
	}

	public void updateCode(Code code) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(code);
	}

	public Code queryCode(int id) {
		// TODO Auto-generated method stub
		Code result = null;
		result = getHibernateTemplate().get(Code.class, id);
		return result;
	}

	public List<Code> QueryCodeByUserAccount(final String UserAccount) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Code>>() {

			public List<Code> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Code where user_account = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, UserAccount);
				List<Code> result = null;
				result = query.list();
				return result;
			}
		});
	}

}
