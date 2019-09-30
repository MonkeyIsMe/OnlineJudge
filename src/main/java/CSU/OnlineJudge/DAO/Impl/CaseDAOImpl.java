package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.CaseDAO;
import CSU.OnlineJudge.Model.Case;

@Transactional
public class CaseDAOImpl extends HibernateDaoSupport implements CaseDAO{

	public void addCase(Case Case) {
		 getHibernateTemplate().save(Case);
	}

	public void deleteCase(Case Case) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(Case);
	}

	public void updateCase(Case Case) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(Case);
	}

	public Case queryCase(int id) {
		// TODO Auto-generated method stub
		Case result = null;
		result = getHibernateTemplate().get(Case.class, id);
		return result;
	}

	public List<Case> QueryCaseByProblemId(final int ProblemId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Case>>() {

			public List<Case> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Case where problem_id = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ProblemId);
				List<Case> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public List<Case> QueryCaseByProblemIdFlag(final int ProblemId, final int Flag) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Case>>() {

			public List<Case> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Case where problem_id = ? and case_flag = ?";
				Query query = session.createQuery(hql);
				query.setParameter(0, ProblemId);
				query.setParameter(1, Flag);
				List<Case> result = null;
				result = query.list();
				return result;
			}
		});
	}

	public Object addMutiplyCase(final List<Case> Case) {
		// TODO Auto-generated method stub
		
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < Case.size(); i ++) {
					session.save(Case.get(i));
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
