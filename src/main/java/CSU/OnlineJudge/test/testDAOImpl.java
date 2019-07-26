package CSU.OnlineJudge.test;

import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import CSU.OnlineJudge.DAO.CaseDAO;
import CSU.OnlineJudge.DAO.ProblemDAO;
import CSU.OnlineJudge.DAO.UserDAO;
import CSU.OnlineJudge.DAO.Impl.CaseDAOImpl;
import CSU.OnlineJudge.DAO.Impl.ProblemDAOImpl;
import CSU.OnlineJudge.DAO.Impl.UserDAOImpl;
import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Utils.HibernateUtil;



public class testDAOImpl {
	
	@Test
	public void AddUser() {
		UserDAO ud = new UserDAOImpl();
		User user = new User();
		user.setUserName("test connect");
		ud.addUser(user);
	}
	
	@Test
	public void TestSingleModel() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "select  UserName from User";
		Query query = session.createQuery(hql);
		List<String> list = query.list();
		session.getTransaction().commit();
		for(String user :list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void TestManyModel() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "select  UserName,UserInfo  from User";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		session.getTransaction().commit();
		System.out.println(list.size());
		for(Object[] user :list) {
			System.out.println(Arrays.toString(user));
		}
	}
	
	@Test
	public void VagueProblem() {
		ProblemDAO pd = new ProblemDAOImpl();
		List<Problem> list = pd.VagueByPageSizeWithFlagByName(1, 5, 1, "2");
		for(Problem problem : list) {
			System.out.println(problem.toString());
		}
	}
	

}
