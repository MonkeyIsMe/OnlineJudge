package CSU.OnlineJudge.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import CSU.OnlineJudge.DAO.CaseDAO;
import CSU.OnlineJudge.DAO.KnowledgeDAO;
import CSU.OnlineJudge.DAO.ProblemDAO;
import CSU.OnlineJudge.DAO.WorkDAO;
import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Service.CaseService;
import net.sf.json.JSONObject;

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

import java.util.Arrays;
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
	
	@Resource(name="ProblemDAO")
	private ProblemDAO pd;
	
	@Test
	public void TestAddProblem() {
		Problem problem = new Problem();
		problem.setProblemName("test add id");
		int pid = pd.addProblem(problem);
		System.out.println(pid);
	}
	
	@Test
	public void QueryOutProblemInfo() {
		List<Object[]> pro_list = pd.GetProblemOutInfo(1, 3, 1);
		for(Object[] pro : pro_list) {
			JSONObject jo = new JSONObject();
			Object id = pro[0];
			Object name = pro[1];
			Object submission = pro[2];
			Object accept = pro[3];
			System.out.println(id.toString());
			jo.put("ProblemId", id);
			jo.put("ProblemName", name);
			jo.put("SubmissionTimes",submission);
			jo.put("AcceptTimes", accept);
			System.out.println(jo.toString());
		}
	}
	
	@Test
	public void TestVagueProblem() {
		String pname = "id";
		List<Problem> list = pd.VagueByPageSizeWithFlagByName(1, 2, 0, pname);
		for(Problem pro : list) {
			System.out.println(pro.toString());
		}
	}
	
	@Resource(name="WorkDAO")
	private WorkDAO wd;
	@Test
	public void QueryWork() {
		List<Work> WorkList = wd.QueryWorkByPageSize(1, 30);
		for(Work work : WorkList) {
			System.out.println(work.toString());
		}
	}
	
	@Test
	public void AddWork() {
		Work work = new Work();
		work.setWorkName("132");
		work.setWorkInfo("4564654");
		wd.addWork(work);
	}
	
	@Resource(name="KnowledgeDAO")
	private KnowledgeDAO kd;
	@Test
	public void CountKnowledge() {
		System.out.println(kd.CountKnowledge());
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
