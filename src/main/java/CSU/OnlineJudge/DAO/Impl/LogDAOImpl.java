package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.LogDAO;
import CSU.OnlineJudge.Model.Log;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class LogDAOImpl extends HibernateDaoSupport implements LogDAO{

	public void addLog(Log log) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(log);
	}

	public void deleteLog(Log log) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(log);
	}

	public void updateLog(Log log) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(log);
	}

	public Log queryLog(int id) {
		// TODO Auto-generated method stub
		Log result = null;
		result = getHibernateTemplate().get(Log.class, id);
		return result;
	}

}
