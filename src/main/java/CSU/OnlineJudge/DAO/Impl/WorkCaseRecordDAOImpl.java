package CSU.OnlineJudge.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkCaseRecordDAO;
import CSU.OnlineJudge.Model.WorkCaseRecord;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class WorkCaseRecordDAOImpl extends HibernateDaoSupport implements WorkCaseRecordDAO{

	public void addWorkCaseRecord(WorkCaseRecord wcr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(wcr);
	}

	public void updateWorkCaseRecord(WorkCaseRecord wcr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(wcr);
	}

	public void deleteWorkCaseRecord(WorkCaseRecord wcr) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(wcr);
	}

	public WorkCaseRecord queryWorkCaseRecord(int id) {
		// TODO Auto-generated method stub
		WorkCaseRecord result = null;
		getHibernateTemplate().get(WorkCaseRecord.class, id);
		return result;
	}

}
