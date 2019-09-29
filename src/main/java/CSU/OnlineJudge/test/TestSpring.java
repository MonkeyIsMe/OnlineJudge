package CSU.OnlineJudge.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import CSU.OnlineJudge.DAO.CaseDAO;
import CSU.OnlineJudge.DAO.CodeDAO;
import CSU.OnlineJudge.DAO.KnowledgeDAO;
import CSU.OnlineJudge.DAO.ProblemDAO;
import CSU.OnlineJudge.DAO.SubmissionDAO;
import CSU.OnlineJudge.DAO.UserDAO;
import CSU.OnlineJudge.DAO.WorkDAO;
import CSU.OnlineJudge.DAO.WorkUserRecordDAO;
import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.User;
import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Model.WorkProblem;
import CSU.OnlineJudge.Service.CaseService;
import CSU.OnlineJudge.Service.NoticeService;
import CSU.OnlineJudge.Service.SubmissionService;
import CSU.OnlineJudge.Service.UserService;
import CSU.OnlineJudge.Service.WorkProblemService;
import CSU.OnlineJudge.Utils.JudgeUtil;
import net.sf.json.JSONArray;
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
	
	@Resource(name="UserDAO")
	private UserDAO ud;
	
	@Resource(name="CodeDAO")
	private CodeDAO cod;
	
	@Resource(name="UserService")
	private UserService us;
	
	@Resource(name="NoticeService")
	private NoticeService ns;
	
	@Resource(name="WorkProblemService")
	private WorkProblemService wps;
	
	@Test
	public void AddWorkProblem() {
		WorkProblem wp = new WorkProblem();
		wp.setProblemId(123);
		wp.setWorkId(132);
		wps.addWorkProblem(wp);
	}
	
	@Test
	public void QueryUserRank() {
		List<User> UserList = us.QueryAcceptNumber(1, 15);
		for(User u : UserList) {
			System.out.println(u.toString());
		}
	}
	
	@Test
	public void TestCodeCount() {
		System.out.println(cod.CountUserCode("1"));
		System.out.println(cod.CountUserCode("2"));
	}
	
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
	public void TestCaseQuery() {
		List<Case> CaseList = cd.QueryCaseByProblemId(1);
		System.out.println(CaseList.size());
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
	
	private Case cas = new Case();
	
	@Test
	public void TestDaoAddMany() {
		String case_info = "[{\"stdin\":\"1\",\"stdout\":\"1\"},{\"stdin\":\"2\",\"stdout\":\"2\"}]";
		JSONArray case_ja = new JSONArray();
		JSONArray ja = JSONArray.fromObject(case_info);
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String stdin = jo.getString("stdin");
			String stdout = jo.getString("stdout");
			//System.out.println(stdin + " " + stdout);
			cas.setCaseFlag(1);
			cas.setCaseInput(stdin);
			cas.setProblemId(123);
			cas.setCaseOutput(stdout);
			JSONObject cjo = JSONObject.fromObject(cas);
			case_ja.add(cjo);
		}
		
		List<Case> CaseList = JSONArray.toList(case_ja,Case.class);
		cs.addMutiplyCase(CaseList);

	}
	
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
	public void TestQueryCase() {
		List<Case> CaseList = cs.GetProblemCase(1);
		System.out.println(CaseList.size());
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
	public void QueryManagerProblemInfo() {
		List<Object[]> pro_list = pd.GetProblemManagerInfo(1, 5);
		for(Object[] pro : pro_list) {
			System.out.println(pro[4]);
		}
	}
	
	@Resource(name="WorkUserRecordDAO")
	private WorkUserRecordDAO wurd;
	@Test
	public void CountWorkUserRecord() {
		System.out.println(wurd.CountWorkUserRecordWithUserIdWorkId(1, 1));
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
	
	@Test
	public void TestUtil() {
		String str = "";
		System.out.println(str == "");
		System.out.println(str == null);
		System.out.println(str.equals(""));
	}
	
	@Resource(name="KnowledgeDAO")
	private KnowledgeDAO kd;
	
	@Test
	public void CountKnowledge() {
		System.out.println(kd.CountKnowledge());
	}
	
	@Resource(name="SubmissionDAO")
	private SubmissionDAO sd;
	
	@Resource(name="SubmissionService")
	private SubmissionService ss;
	
	@Test
	public void querySubmission() {
		System.out.println(ss.querySubmission(6));
	}
	
	@Test
	public void Judger() {
		JudgeUtil ju = new JudgeUtil();
    	JSONObject fianljo = new JSONObject();
    	JSONArray caseja = new JSONArray();
    	JSONObject casejo = new JSONObject();
		List<Case> clist = cs.GetCaseByFlag(1, 1);
		for(int i = 0; i < clist.size(); i ++) {
			Case c = clist.get(i);
    		casejo.put("stdin",c.getCaseInput());
    		casejo.put("stdout",c.getCaseOutput());
    		caseja.add(casejo);
		}
		System.out.println(casejo.toString());
		String str = ju.Judger(ju.DealCase(caseja.toString(), "CPP", 3, 228,"#include<cstdio>\r\n" + 
				"#include<cstring>\r\n" + 
				"#include<algorithm>\r\n" + 
				"#include<iostream>\r\n" + 
				"using namespace std;\r\n" + 
				"\r\n" + 
				"int main()\r\n" + 
				"{\r\n" + 
				"    int a,b;\r\n" + 
				"    cin>>a>>b;\r\n" + 
				"    cout<<a+b<<endl;\r\n" + 
				"    return 0;\r\n" + 
				"}\r\n" + 
				""));
    	System.out.println(str);
    	String result = ju.ResultUtil(str);
    	System.out.println(result);
    	String total = ju.TotalResultUtil(str);
    	System.out.println(total);
	}
}
