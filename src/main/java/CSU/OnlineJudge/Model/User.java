package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//用户表
@Entity
@Table(name="tab_user")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserId;  // 主键
	
	@Column(name="user_account")
	private String UserAccount;  // 账号
	
	@Column(name="user_classroom")
	private String StudentClassroom;  //班级 
	
	@Column(name="user_info")
	private String UserInfo;  // 备注信息
	
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
	
	@Column(name="problem_submission")
	private int SubmissionTimes;  // 提交次数
	
	@Column(name="user_name")
	private String UserName; //名字
	
	
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
	public String getStudentClassroom() {
		return StudentClassroom;
	}
	public void setStudentClassroom(String studentClassroom) {
		StudentClassroom = studentClassroom;
	}
	public String getUserInfo() {
		return UserInfo;
	}
	public void setUserInfo(String userInfo) {
		UserInfo = userInfo;
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
	public int getSubmissionTimes() {
		return SubmissionTimes;
	}
	public void setSubmissionTimes(int submissionTimes) {
		SubmissionTimes = submissionTimes;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("UserId", this.UserId);
		jo.put("UserAccount", this.UserAccount);
		jo.put("StudentClassroom", this.StudentClassroom);
		jo.put("UserInfo", this.UserInfo);
		jo.put("WrongAnswerTimes", this.WrongAnswerTimes);
		jo.put("TimeLimitTimes", this.TimeLimitTimes);
		jo.put("RuntimeErrorTimes", this.RuntimeErrorTimes);
		jo.put("CompileErrorTimes", this.CompileErrorTimes);
		jo.put("AcceptTimes", this.AcceptTimes);
		jo.put("SubmissionTimes", this.SubmissionTimes);
		jo.put("UserName", this.UserName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
