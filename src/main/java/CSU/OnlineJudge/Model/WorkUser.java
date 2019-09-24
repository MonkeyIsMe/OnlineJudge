package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//考试-作业用户关联表
@Entity
@Table(name="tab_workuser")
public class WorkUser {
	
	@Id
	@Column(name="wu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int WorkUserId;  //主键
	
	@Column(name="work_id")
	private int WorkId; //考试作业编号
	
	@Column(name="problem_accept")
	private int AcceptTimes; // 正确次数
	
	@Column(name="problem_wrong")
	private int WrongAnswerTimes; // 错误次数
	
	@Column(name="problem_tle")
	private int TimeLimitTimes; //  超时次数
	
	@Column(name="problem_rte")
	private int RuntimeErrorTimes; // RE次数
	
	@Column(name="problem_submission")
	private int SubmissionTimes; // 提交次数
	
	@Column(name="user_id")
	private int UserId; //用户编号
	
	@Column(name="user_account")
	private String UserAccount; //用户账号
	
	@Column(name="user_name")
	private String UserName; //用户名字
	
	@Column(name="problem_ce")
	private int CompileErrorTimes; // 编译错误次数

	public int getWorkUserId() {
		return WorkUserId;
	}

	public void setWorkUserId(int workUserId) {
		WorkUserId = workUserId;
	}

	public int getWorkId() {
		return WorkId;
	}

	public void setWorkId(int workId) {
		WorkId = workId;
	}

	public int getAcceptTimes() {
		return AcceptTimes;
	}

	public void setAcceptTimes(int acceptTimes) {
		AcceptTimes = acceptTimes;
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

	public int getSubmissionTimes() {
		return SubmissionTimes;
	}

	public void setSubmissionTimes(int submissionTimes) {
		SubmissionTimes = submissionTimes;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public int getWrongAnswerTimes() {
		return WrongAnswerTimes;
	}

	public void setWrongAnswerTimes(int wrongAnswerTimes) {
		WrongAnswerTimes = wrongAnswerTimes;
	}

	public int getCompileErrorTimes() {
		return CompileErrorTimes;
	}

	public void setCompileErrorTimes(int compileErrorTimes) {
		CompileErrorTimes = compileErrorTimes;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkUserId", this.WorkUserId);
		jo.put("UserId", this.UserId);
		jo.put("UserAccount", this.UserAccount);
		jo.put("UserName", this.UserName);
		jo.put("WrongAnswerTimes", this.WrongAnswerTimes);
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
