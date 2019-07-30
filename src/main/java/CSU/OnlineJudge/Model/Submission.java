package CSU.OnlineJudge.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//提交表
@Entity
@Table(name="tab_submission")
public class Submission {

	@Id
	@Column(name="log_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SubmissionId;  // 主键
	
	@Column(name="user_id")
	private int StudentId;  //学生编号
	
	@Column(name="submission_result")
	private String SubmissionResult;  // 结果 
	
	@Column(name="submit_time")
	private String SubmissionTime;  // 提交时间
	
	@Column(name="submission_length")
	private int CodeLength;  // 代码长度
	
	@Column(name="submission_time")
	private int CodeTime;  // 代码运行时间
	
	@Column(name="submission_memory")
	private int CodeMemory;  // 代码消耗内存

	@Column(name="user_account")
	private String UserAccount;  // 学生账号
	
	@Column(name="submission_type")
	private String CodeType;  // 代码类型
	
	@Column(name="problem_id")
	private int ProblemId;  // 题目编号
	
	public int getSubmissionId() {
		return SubmissionId;
	}
	public void setSubmissionId(int submissionId) {
		SubmissionId = submissionId;
	}
	public String getSubmissionResult() {
		return SubmissionResult;
	}
	public void setSubmissionResult(String submissionResult) {
		SubmissionResult = submissionResult;
	}
	
	public String getSubmissionTime() {
		return SubmissionTime;
	}
	public void setSubmissionTime(String submissionTime) {
		SubmissionTime = submissionTime;
	}
	public int getCodeLength() {
		return CodeLength;
	}
	public void setCodeLength(int codeLength) {
		CodeLength = codeLength;
	}
	public int getCodeTime() {
		return CodeTime;
	}
	public void setCodeTime(int codeTime) {
		CodeTime = codeTime;
	}
	public int getCodeMemory() {
		return CodeMemory;
	}
	public void setCodeMemory(int codeMemory) {
		CodeMemory = codeMemory;
	}
	public String getUserAccount() {
		return UserAccount;
	}
	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}
	public String getCodeType() {
		return CodeType;
	}
	public void setCodeType(String codeType) {
		CodeType = codeType;
	}
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public int getProblemId() {
		return ProblemId;
	}
	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}
	
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("SubmissionId", this.SubmissionId);
		jo.put("SubmissionResult", this.SubmissionResult);
		jo.put("SubmissionTime", this.SubmissionTime);
		jo.put("CodeLength", this.CodeLength);
		jo.put("CodeTime", this.CodeTime);
		jo.put("CodeMemory", this.CodeMemory);
		jo.put("UserAccount", this.UserAccount);
		jo.put("CodeType", this.CodeType);
		jo.put("StudentId", this.StudentId);
		jo.put("ProblemId", this.ProblemId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
}
