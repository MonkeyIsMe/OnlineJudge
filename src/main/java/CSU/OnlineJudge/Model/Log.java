package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//日志表
@Entity
@Table(name="tab_log")
public class Log {
	
	@Id
	@Column(name="log_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int LogId; //主键
	
	@Column(name="problem_submisson")
	private int SubmissionNumber; // 提交数
	
	@Column(name="problem_wrong")
	private int WrongAnswerTimes;  // 错误次数
	
	@Column(name="problem_tle")
	private int TimeLimitTimes;  //  超时次数
	
	@Column(name="problem_rte")
	private int RuntimeErrorTimes;  // RE次数
	
	@Column(name="problem_ce")
	private int CompileErrorTimes;  // 编译错误次数
	
	@Column(name="problem_accept")
	private int AcceptTimes;   // 正确次数
	
	@Column(name="log_time")
	private String LogTime;   // 当天时间
	
	
	public int getLogId() {
		return LogId;
	}
	public void setLogId(int logId) {
		LogId = logId;
	}
	public int getSubmissionNumber() {
		return SubmissionNumber;
	}
	public void setSubmissionNumber(int submissionNumber) {
		SubmissionNumber = submissionNumber;
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
	public String getLogTime() {
		return LogTime;
	}
	public void setLogTime(String logTime) {
		LogTime = logTime;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("LogId", this.LogId);
		jo.put("SubmissionNumber", this.SubmissionNumber);
		jo.put("WrongAnswerTimes", this.WrongAnswerTimes);
		jo.put("TimeLimitTimes", this.TimeLimitTimes);
		jo.put("RuntimeErrorTimes", this.RuntimeErrorTimes);
		jo.put("CompileErrorTimes", this.CompileErrorTimes);
		jo.put("AcceptTimes", this.AcceptTimes);
		jo.put("LogTime", this.LogTime);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
