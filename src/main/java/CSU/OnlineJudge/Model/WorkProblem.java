package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//考试-作业，题目关联表
@Entity
@Table(name="tab_workproblem")
public class WorkProblem {
	
	@Id
	@Column(name="wp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int WorkProblemId; // 主键
	
	@Column(name="problem_id")
	private int ProblemId; // 题目编号
	
	@Column(name="work_id")
	private int WorkId; // 考试/作业编号
	
	@Column(name="problem_name")
	private String ProblemName;  // 题目名字
	
	@Column(name="problem_memory")
	private int ProblemMemory;  // 题目内存限制
	
	@Column(name="problem_time")
	private int ProblemTimeLimit;  // 题目时间
	
	@Column(name="problem_degree")
	private int ProblemDegree;  // 题目难度
	
	@Column(name="problem_wrong")
	private int WrongAnswerTimes;  // 错误次数
	
	@Column(name="problem_tle")
	private int TimeLimitTimes;  //  超时次数
	
	@Column(name="problem_rte")
	private int RuntimeErrorTimes;  // RE次数
	
	@Column(name="problem_ce")
	private int CompileErrorTimes;  // 编译错误次数
	
	@Column(name="problem_submission")
	private int SubmissionTimes; // 提交次数
	
	@Column(name="problem_accept")
	private int AcceptTimes;   // 正确次数

	public int getWorkProblemId() {
		return WorkProblemId;
	}
	public void setWorkProblemId(int workProblemId) {
		WorkProblemId = workProblemId;
	}
	public int getProblemId() {
		return ProblemId;
	}
	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}
	public int getWorkId() {
		return WorkId;
	}
	public void setWorkId(int workId) {
		WorkId = workId;
	}
	public String getProblemName() {
		return ProblemName;
	}
	public void setProblemName(String problemName) {
		ProblemName = problemName;
	}
	public int getProblemMemory() {
		return ProblemMemory;
	}
	public void setProblemMemory(int problemMemory) {
		ProblemMemory = problemMemory;
	}
	public int getProblemTimeLimit() {
		return ProblemTimeLimit;
	}
	public void setProblemTimeLimit(int problemTimeLimit) {
		ProblemTimeLimit = problemTimeLimit;
	}
	public int getProblemDegree() {
		return ProblemDegree;
	}
	public void setProblemDegree(int problemDegree) {
		ProblemDegree = problemDegree;
	}
	public int getWrongAnswerTimes() {
		return WrongAnswerTimes;
	}
	public void setWrongAnswerTimes(int wrongAnswerTimes) {
		WrongAnswerTimes = wrongAnswerTimes;
	}
	public int getTimeLimitTimes() {
		return TimeLimitTimes;
	}
	public void setTimeLimitTimes(int timeLimitTimes) {
		TimeLimitTimes = timeLimitTimes;
	}
	public int getRuntimeErrorTimes() {
		return RuntimeErrorTimes;
	}
	public void setRuntimeErrorTimes(int runtimeErrorTimes) {
		RuntimeErrorTimes = runtimeErrorTimes;
	}
	public int getCompileErrorTimes() {
		return CompileErrorTimes;
	}
	public void setCompileErrorTimes(int compileErrorTimes) {
		CompileErrorTimes = compileErrorTimes;
	}
	public int getSubmissionTimes() {
		return SubmissionTimes;
	}
	public void setSubmissionTimes(int submissionTimes) {
		SubmissionTimes = submissionTimes;
	}
	public int getAcceptTimes() {
		return AcceptTimes;
	}
	public void setAcceptTimes(int acceptTimes) {
		AcceptTimes = acceptTimes;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkProblemId", this.WorkProblemId);
		jo.put("ProblemId", this.ProblemId);
		jo.put("ProblemMemory", this.ProblemMemory);
		jo.put("ProblemName", this.ProblemName);
		jo.put("ProblemTimeLimit", this.ProblemTimeLimit);
		jo.put("WrongAnswerTimes", this.WrongAnswerTimes);
		jo.put("ProblemDegree", this.ProblemDegree);
		jo.put("TimeLimitTimes", this.TimeLimitTimes);
		jo.put("RuntimeErrorTimes", this.RuntimeErrorTimes);
		jo.put("CompileErrorTimes", this.CompileErrorTimes);
		jo.put("SubmissionTimes", this.SubmissionTimes);
		jo.put("AcceptTimes", this.AcceptTimes);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
