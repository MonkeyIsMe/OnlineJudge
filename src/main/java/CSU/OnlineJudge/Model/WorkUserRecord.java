package CSU.OnlineJudge.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//考试-作业记录，用户关联表
@Entity
@Table(name="tab_workuser")
public class WorkUserRecord {
	
	@Id
	@Column(name="wu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int WorkUserRecordId;// 主键
	
	@Column(name="problem_id")
	private int ProblemId; // 问题编号
	
	@Column(name="work_id")
	private int WorkId; // 作业/比赛编号
	
	@Column(name="user_id")
	private int UserId; // 学生编号
	
	@Column(name="submission_result")
	private String SubmissionResult;  // 结果 
	
	@Column(name="submission_time")
	private Date SubmissionTime;  // 提交时间
	
	@Column(name="submission_code_length")
	private int CodeLength;  // 代码长度
	
	@Column(name="submission_code_time")
	private int CodeTime;  // 代码运行时间
	
	@Column(name="submission_code_memory")
	private int CodeMemory;  // 代码消耗内存
	
	@Column(name="user_account")
	private String UserAccount;  // 学生账号
	
	@Column(name="submission_type")
	private String CodeType;  // 代码类型
	
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getWorkUserRecordId() {
		return WorkUserRecordId;
	}
	public void setWorkUserRecordId(int workUserRecordId) {
		WorkUserRecordId = workUserRecordId;
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
	public String getSubmissionResult() {
		return SubmissionResult;
	}
	public void setSubmissionResult(String submissionResult) {
		SubmissionResult = submissionResult;
	}
	public Date getSubmissionTime() {
		return SubmissionTime;
	}
	public void setSubmissionTime(Date submissionTime) {
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
	
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkUserRecordId", this.WorkUserRecordId);
		jo.put("ProblemId", this.ProblemId);
		jo.put("SubmissionResult", this.SubmissionResult);
		jo.put("SubmissionTime", this.SubmissionTime);
		jo.put("CodeLength", this.CodeLength);
		jo.put("CodeMemory", this.CodeMemory);
		jo.put("CodeTime", this.CodeTime);
		jo.put("UserAccount", this.UserAccount);
		jo.put("CodeType", this.CodeType);
		
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
