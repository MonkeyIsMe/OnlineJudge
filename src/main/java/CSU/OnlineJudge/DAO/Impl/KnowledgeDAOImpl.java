package CSU.OnlineJudge.DAO.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.KnowledgeDAO;
import CSU.OnlineJudge.Model.Knowledge;
import CSU.OnlineJudge.Utils.HibernateUtil;

@Transactional
public class KnowledgeDAOImpl extends HibernateDaoSupport implements KnowledgeDAO{

	public void addKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(knowledge);
	}

	public void updateKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(knowledge);
	}

	public void deleteKnowledge(Knowledge knowledge) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(knowledge);
	}

	public Knowledge queryKnowledge(int id) {
		// TODO Auto-generated method stub
		Knowledge result = null;
		result =  getHibernateTemplate().get(Knowledge.class, id);
		return result;
	}

	public List<Knowledge> QueryKnowledgeByPageSize(final int rows, final int PageSize) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Knowledge>>() {
			
			public List<Knowledge> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Knowledge";
				Query query = session.createQuery(hql).setFirstResult(
                        (rows - 1) * PageSize).setMaxResults(PageSize);
				List<Knowledge> list = query.list();
				return list;
			}
		});
	}

	public int CountKnowledge() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Knowledge as Knowledge";
		return ((Long)getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Knowledge> QueryAllKnowledge() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<List<Knowledge>>() {
			

			public List<Knowledge> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String hql = "from Knowledge";
				Query query = session.createQuery(hql);
				List<Knowledge> list = query.list();
				return list;
			}
		});
	}

	public Object addMutiplyKnowledge(final List<Knowledge> knowledge) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				for(int i = 0; i < knowledge.size(); i ++) {
					session.save(knowledge.get(i));
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
