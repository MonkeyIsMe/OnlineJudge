package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

// 考试-作业，记录关联表
@Entity
@Table(name="tab_workrecord")
public class WorkRecord {
	
	@Id
	@Column(name="wr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int WorkRecordId; // 主键
	
	@Column(name="problem_id")
	private int ProblemId; // 问题编号
	
	@Column(name="work_id")
	private int WorkId; // 作业/作业编号
	
	@Column(name="wr_submission")
	private int SubmitNumber; // 提交数
	
	@Column(name="submission_wrong")
	private int WrongAnswerTimes;  // 错误次数
	
	@Column(name="submission_tle")
	private int TimeLimitTimes;  //  超时次数
	
	@Column(name="submission_rte")
	private int RuntimeErrorTimes;  // RE次数
	
	@Column(name="submission_ce")
	private int CompileErrorTimes;  // 编译错误次数
	
	@Column(name="submission_accept")
	private int AcceptTimes;   // 正确次数
	
	
	public int getWorkRecordId() {
		return WorkRecordId;
	}
	public void setWorkRecordId(int workRecordId) {
		WorkRecordId = workRecordId;
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
	public int getSubmitNumber() {
		return SubmitNumber;
	}
	public void setSubmitNumber(int submitNumber) {
		SubmitNumber = submitNumber;
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
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkRecordId", this.WorkRecordId);
		jo.put("ProblemId", this.ProblemId);
		jo.put("SubmitNumber", this.SubmitNumber);
		jo.put("WrongAnswerTimes", this.WrongAnswerTimes);
		jo.put("TimeLimitTimes", this.TimeLimitTimes);
		jo.put("RuntimeErrorTimes", this.RuntimeErrorTimes);
		jo.put("CompileErrorTimes", this.CompileErrorTimes);
		jo.put("AcceptTimes", this.AcceptTimes);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
