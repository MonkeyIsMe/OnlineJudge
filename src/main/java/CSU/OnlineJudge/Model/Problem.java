package CSU.OnlineJudge.Model;

import java.sql.Blob;

import javax.persistence.*;

import org.json.JSONObject;

//题目表
@Entity
@Table(name="tab_problem")
public class Problem {
	
	@Id
	@Column(name="problem_id")
	private int ProblemId;  // 主键
	
	@Column(name="problem_name")
	private String ProblemName;  // 题目名字

	@Column(name="problem_info")
	private String ProblemInfo;  // 题面
	
	@Column(name="problem_hint")
	private String ProblemHint;  // 题目提示
	
	@Column(name="problem_memory")
	private int ProblemMemory;  // 题目内存限制
	
	@Column(name="problem_time")
	private int ProblemTimeLimit;  // 题目时间
	
	@Column(name="problem_degree")
	private String ProblemDegree;  // 题目难度
	
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
	
	@Column(name="problem_people")
	private String ProblemPeople; // 出题人
	
	@Column(name="problem_flag")
	private int PublicOrNot; // 题目是否公开,0代表不公开,1代表公开
	
	@Column(name="problem_input")
	private String ProblemInput; // 题面输入
	
	@Column(name="problem_output")
	private String ProblemOutput; // 题目输出
	
	public int getProblemId() {
		return ProblemId;
	}
	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}
	public String getProblemName() {
		return ProblemName;
	}
	public void setProblemName(String problemName) {
		ProblemName = problemName;
	}
/*	public Blob getProblemInfo() {
		return ProblemInfo;
	}
	public void setProblemInfo(Blob problemInfo) {
		ProblemInfo = problemInfo;
	}*/
	
	public String getProblemHint() {
		return ProblemHint;
	}
	public String getProblemInfo() {
		return ProblemInfo;
	}
	public void setProblemInfo(String problemInfo) {
		ProblemInfo = problemInfo;
	}
	public void setProblemHint(String problemHint) {
		ProblemHint = problemHint;
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
	public String getProblemDegree() {
		return ProblemDegree;
	}
	public void setProblemDegree(String problemDegree) {
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
	public int getAcceptTimes() {
		return AcceptTimes;
	}
	public void setAcceptTimes(int acceptTimes) {
		AcceptTimes = acceptTimes;
	}
	public String getProblemPeople() {
		return ProblemPeople;
	}
	public void setProblemPeople(String problemPeople) {
		ProblemPeople = problemPeople;
	}
	public int getPublicOrNot() {
		return PublicOrNot;
	}
	public void setPublicOrNot(int publicOrNot) {
		PublicOrNot = publicOrNot;
	}
	public int getSubmissionTimes() {
		return SubmissionTimes;
	}
	public void setSubmissionTimes(int submissionTimes) {
		SubmissionTimes = submissionTimes;
	}
	public String getProblemInput() {
		return ProblemInput;
	}
	public void setProblemInput(String problemInput) {
		ProblemInput = problemInput;
	}
	public String getProblemOutput() {
		return ProblemOutput;
	}
	public void setProblemOutput(String problemOutput) {
		ProblemOutput = problemOutput;
	}
	
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("ProblemId", this.ProblemId);
		jo.put("ProblemName", this.ProblemName);
		jo.put("ProblemInfo", this.ProblemInfo);
		jo.put("ProblemHint", this.ProblemHint);
		jo.put("ProblemMemory", this.ProblemMemory);
		jo.put("ProblemTimeLimit", this.ProblemTimeLimit);
		jo.put("ProblemDegree", this.ProblemDegree);
		jo.put("WrongAnswerTimes", this.WrongAnswerTimes);
		jo.put("TimeLimitTimes", this.TimeLimitTimes);
		jo.put("RuntimeErrorTimes", this.RuntimeErrorTimes);
		jo.put("CompileErrorTimes", this.CompileErrorTimes);
		jo.put("SubmissionTimes", this.SubmissionTimes);
		jo.put("AcceptTimes", this.AcceptTimes);
		jo.put("ProblemPeople", this.ProblemPeople);
		jo.put("PublicOrNot", this.PublicOrNot);
		jo.put("ProblemInput", this.ProblemInput);
		jo.put("ProblemOutput", this.ProblemOutput);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
