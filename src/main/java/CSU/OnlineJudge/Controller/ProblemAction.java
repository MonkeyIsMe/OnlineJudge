package CSU.OnlineJudge.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Model.Knowledge;
import CSU.OnlineJudge.Model.KnowledgeProblem;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Service.CaseService;
import CSU.OnlineJudge.Service.KnowledgeProblemService;
import CSU.OnlineJudge.Service.KnowledgeService;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Service.Impl.ProblemServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ProblemAction extends ActionSupport{
	
	private ProblemService ProblemService;
	private KnowledgeProblemService KnowledgeProblemService;
	private KnowledgeService KnowledgeService;
	private Problem problem = new Problem();
	private CaseService CaseService;
	private Case cas = new Case();
	private Knowledge knowledge = new Knowledge();
	private KnowledgeProblem kp = new KnowledgeProblem();

	
	public KnowledgeProblemService getKnowledgeProblemService() {
		return KnowledgeProblemService;
	}

	public void setKnowledgeProblemService(KnowledgeProblemService knowledgeProblemService) {
		KnowledgeProblemService = knowledgeProblemService;
	}

	public KnowledgeService getKnowledgeService() {
		return KnowledgeService;
	}

	public void setKnowledgeService(KnowledgeService knowledgeService) {
		KnowledgeService = knowledgeService;
	}

	public CaseService getCaseService() {
		return CaseService;
	}

	public void setCaseService(CaseService caseService) {
		CaseService = caseService;
	}

	public ProblemService getProblemService() {
		return ProblemService;
	}

	public void setProblemService(ProblemService problemService) {
		ProblemService = problemService;
	}

	//删除题目
	public void DeleteProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		
		int pid = Integer.valueOf(problem_id);
		Problem problem = ProblemService.QueryProblem(pid);
		
		if(problem == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		ProblemService.DeleteProblem(problem);
		
	}
	
	public void TestAddProblem() throws Exception{
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_name = request.getParameter("problem_name");
		String problem_info = request.getParameter("problem_info");
		String problem_hint = request.getParameter("problem_hint");
		String problem_memory = request.getParameter("problem_memory");
		String problem_time = request.getParameter("problem_time");
		String problem_people = request.getParameter("problem_people");
		String problem_flag = request.getParameter("problem_flag");
		String problem_input = request.getParameter("problem_input");
		String problem_output = request.getParameter("problem_output");
		String case_input = request.getParameter("case_input");
		String case_output = request.getParameter("case_output");
		String case_info = request.getParameter("case_info");
		System.out.println(case_info);
		JSONArray ja = JSONArray.fromObject(case_info);
		
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String stdin = jo.getString("stdin");
			String stdout = jo.getString("stdout");
			System.out.println(stdin + " " + stdout);
		}
	}
	
	
	//添加题目
	public void AddProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_name = request.getParameter("problem_name");
		String problem_info = request.getParameter("problem_info");
		String problem_hint = request.getParameter("problem_hint");
		String problem_memory = request.getParameter("problem_memory");
		String problem_time = request.getParameter("problem_time");
		String problem_people = request.getParameter("problem_people");
		String problem_flag = request.getParameter("problem_flag");
		String problem_input = request.getParameter("problem_input");
		String problem_output = request.getParameter("problem_output");
		String case_input = request.getParameter("case_input");
		String case_output = request.getParameter("case_output");
		String case_info = request.getParameter("case_info");
		
		int memory = Integer.valueOf(problem_memory);
		int time = Integer.valueOf(problem_time);
		int IsPublic = Integer.valueOf(problem_flag);
		
		JSONArray ja = JSONArray.fromObject(case_info);
		
		problem.setProblemName(problem_name);
		problem.setProblemInfo(problem_input);
		problem.setProblemHint(problem_hint);
		problem.setProblemMemory(memory);
		problem.setProblemTimeLimit(time);
		problem.setSubmissionTimes(0);
		problem.setAcceptTimes(0);
		problem.setProblemDegree(0);
		problem.setWrongAnswerTimes(0);
		problem.setTimeLimitTimes(0);
		problem.setRuntimeErrorTimes(0);
		problem.setCompileErrorTimes(0);
		problem.setProblemPeople(problem_people);
		problem.setPublicOrNot(IsPublic);
		problem.setProblemInput(problem_input);
		problem.setProblemOutput(problem_output);
		
		int pid = ProblemService.AddProblem(problem);
		cas.setCaseFlag(0);
		cas.setCaseInput(case_input);
		cas.setProblemId(pid);
		cas.setCaseOutput(case_output);
		CaseService.AddCase(cas);
		
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject jo = ja.getJSONObject(i);
			String stdin = jo.getString("stdin");
			String stdout = jo.getString("stdout");
			//System.out.println(stdin + " " + stdout);
			cas.setCaseFlag(1);
			cas.setCaseInput(stdin);
			cas.setProblemId(pid);
			cas.setCaseOutput(stdout);
			CaseService.AddCase(cas);
		}
		
		JSONObject jo = new JSONObject();
		jo.put("ProblemId", pid);
		
		out.println(jo.toString());
		out.flush(); 
		out.close();
		
	}
	
	//更新题目
	public void UpdateProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		String problem_id = request.getParameter("problem_id");
		String problem_name = request.getParameter("problem_name");
		String problem_info = request.getParameter("problem_info");
		String problem_hint = request.getParameter("problem_hint");
		String problem_memory = request.getParameter("problem_memory");
		String problem_time = request.getParameter("problem_time");
		String problem_submission = request.getParameter("problem_submission");
		String problem_accept = request.getParameter("problem_accept");
		String problem_degree = request.getParameter("problem_degree");
		String problem_wrong = request.getParameter("problem_wrong");
		String problem_tle = request.getParameter("problem_tle");
		String problem_rte = request.getParameter("problem_rte");
		String problem_ce = request.getParameter("problem_ce");
		String problem_people = request.getParameter("problem_people");
		String problem_flag = request.getParameter("problem_flag");
		String problem_input = request.getParameter("problem_input");
		String problem_output = request.getParameter("problem_output");
		
		int memory = Integer.valueOf(problem_memory);
		int time = Integer.valueOf(problem_time);
		int submission = Integer.valueOf(problem_submission);
		int accept = Integer.valueOf(problem_accept);
		int wrong = Integer.valueOf(problem_wrong);
		int tle = Integer.valueOf(problem_tle);
		int rte = Integer.valueOf(problem_rte);
		int ce = Integer.valueOf(problem_ce);
		int IsPublic = Integer.valueOf(problem_flag);
		int degree = Integer.valueOf(problem_degree);
		
		int pid = Integer.valueOf(problem_id);
		problem = ProblemService.QueryProblem(pid);
		
		if(problem == null) {
			out.println("Fail");
			out.flush(); 
			out.close();
			return ;
		}
		
		problem.setProblemName(problem_name);
		problem.setProblemInfo(problem_input);
		problem.setProblemHint(problem_hint);
		problem.setProblemMemory(memory);
		problem.setProblemTimeLimit(time);
		problem.setSubmissionTimes(submission);
		problem.setAcceptTimes(accept);
		problem.setProblemDegree(degree);
		problem.setWrongAnswerTimes(wrong);
		problem.setTimeLimitTimes(tle);
		problem.setRuntimeErrorTimes(rte);
		problem.setCompileErrorTimes(ce);
		problem.setProblemPeople(problem_people);
		problem.setPublicOrNot(IsPublic);
		problem.setProblemInput(problem_input);
		problem.setProblemOutput(problem_output);
		
		ProblemService.UpdateProblem(problem);
		
	}
	
	//查询公开的题目
	public void QueryPublicProblem() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ProblemService.GetFlagProblemByPageSize(row, PageSize, 1);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
        out.flush(); 
        out.close();
	}
		
	//查询不公开的题目
	public void QueryPrivateProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();	
		
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ProblemService.GetFlagProblemByPageSize(row, PageSize, 0);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	}
		
	//查询所有的题目
	public void QueryAllProblem() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ProblemService.QueryProblemByPageSize(row, PageSize);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	    }
		
	//查询单个的题目
	public void QuerySingleProblem() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String problem_id = request.getParameter("problem_id");
			
		int pid = Integer.valueOf(problem_id);
			
		Problem Problem = ProblemService.QueryProblem(pid);
			
		JSONObject jo = JSONObject.fromObject(Problem);
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
	}

	//根据名字模糊查询公开题目
	public void VaguePublicProblemByName() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_name = request.getParameter("problem_name");	
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ProblemService.VagueByPageSizeWithFlagByName(row, PageSize, 1, problem_name);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	   }
	
	//根据名字模糊查询私有题目
	public void VaguePrivateProblemByName() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_name = request.getParameter("problem_name");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ProblemService.VagueByPageSizeWithFlagByName(row, PageSize, 0, problem_name);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	   }
	
	//根据出题人查题目
	public void QueryProblemByWriter() throws Exception{
			
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		String problem_people = request.getParameter("problem_people");
			
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size);
			
		List<Problem> ProblemList = ProblemService.VagueByPageSizeWithFlagByPeople(row, PageSize, problem_people);
			
		JSONArray ja = JSONArray.fromObject(ProblemList);
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	   }
	
	//查询题目列表
	public void QueryProblemList() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		JSONArray ja = new JSONArray();
		List<Object[]> obj_list = ProblemService.GetProblemOutInfo(row, PageSize, 1);
		for(Object[]  obj : obj_list) {
			JSONObject jo = new JSONObject();
			Object id = obj[0];
			Object name = obj[1];
			Object submission = obj[2];
			Object accept = obj[3];
			jo.put("ProblemId", id);
			jo.put("ProblemName", name);
			jo.put("SubmissionTimes",submission);
			jo.put("AcceptTimes", accept);
			ja.add(jo);
		}
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	}
	
	//查询题目总数
	public void CountProblem() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
		
		int cnt = ProblemService.CountProblem();
		
		JSONObject jo = new JSONObject();
		jo.put("ProblemCount", jo);
		
		out.println(jo.toString());
	    out.flush(); 
	    out.close();
	}
	
	//查询管理端题目列表
	public void QueryProblemListManager() throws Exception{
		
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request= ServletActionContext.getRequest();
			
		//返回结果
		PrintWriter out = null;
		out = ServletActionContext.getResponse().getWriter();
			
		String page = request.getParameter("rows");
		String size = request.getParameter("size");
		
		int row = Integer.valueOf(page);
		int PageSize = Integer.valueOf(size); 
		
		JSONArray ja = new JSONArray();
		List<Object[]> obj_list = ProblemService.GetProblemOutInfo(row, PageSize, 1);
		for(Object[]  obj : obj_list) {
			JSONObject jo = new JSONObject();
			String know = "";
			int pid = (Integer) obj[0];
			List<KnowledgeProblem> kp_list =  KnowledgeProblemService.queryKnowledgeProblemByProblemId(pid);
			for(KnowledgeProblem kpl : kp_list) {
				int kid = kpl.getKnowledgeId();
				knowledge = KnowledgeService.queryKnowledge(kid);
				know = know + knowledge.getKnowledgeName();
				know = know +",";
			}
			
			Object id = obj[0];
			Object name = obj[1];
			Object degree = obj[2];
			Object people = obj[3];
			Object publicornot = obj[4];
			jo.put("ProblemId", id);
			jo.put("ProblemName", name);
			jo.put("degree",degree);
			jo.put("people", people);
			jo.put("publicornot", publicornot);
			ja.add(jo);
		}
		out.println(ja.toString());
	    out.flush(); 
	    out.close();
	}
}
