package CSU.OnlineJudge.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import CSU.OnlineJudge.DAO.CaseDAO;
import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Service.CaseService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.util.List;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {
	
	@Resource(name="sessionFactory")
	private  SessionFactory sf;
	
	
	@Test
	public void TestHibernate() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setUserName("hibernate");
		session.save(user);
		tx.commit();
		session.close();
	}
	
	@Resource(name="CaseDAO")
	private CaseDAO cd;
	
	@Test
	public void TestDaoAdd() {
		Case cas = new Case();
		cas.setProblemId(1027);
		cas.setCaseInput("");
		cas.setCaseOutput("Hello World!");
		cas.setCaseFlag(0);
		cd.addCase(cas);
	}
	
	@Test
	public void TestDaoQuery() {
		System.out.println(cd.queryCase(1));
	}
	
	@Test
	public void TestDaoQueryList() {
		List<Case> list = cd.QueryCaseByProblemIdFlag(1027, 0);
		System.out.println(list.size());
		for(Case cas : list) {
			System.out.println(cas.toString());
		}
	}
	
	@Resource(name="CaseService")
	private CaseService cs;
	
	@Test
	public void TestAddService() {
		Case cas = new Case();
		cas.setProblemId(1024);
		cas.setCaseInput("");
		cas.setCaseOutput("Hello World!");
		cas.setCaseFlag(0);
		cs.AddCase(cas);
	}
	
	@Test
	public void TestQueryService() {
	}
	
	
	@Test
	public void TestConfiguration() {
		//1 创建容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2 向容器"要"user对象
		User u = (User) ac.getBean("user");
		//3 打印user对象
		System.out.println(u);
	}
}
