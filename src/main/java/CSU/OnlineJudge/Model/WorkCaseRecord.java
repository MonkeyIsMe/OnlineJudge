package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//考试样例记录表，记录关联表
@Entity
@Table(name="tab_testrecord")
public class WorkCaseRecord {
	
	@Id
	@Column(name="tr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int WorkCaseRecordId;  // 主键
	
	@Column(name="work_id")
	private int WorkId; // 作业-考试编号
	
	@Column(name="problem_id")
	private int ProblemId; // 题目编号
	
	@Column(name="user_id")
	private int UserId; // 用户编号
	
	@Column(name="case_id")
	private int CaseId; // 样例编号
	
	@Column(name="test_result")
	private String CaseResult; // 样例结果
	
	@Column(name="aim_input")
	private String AimOutput;  // 目标输出
	
	@Column(name="aim_output")
	private String AimInput;  // 目标输入
	
	@Column(name="test_output")
	private String TestOutput; // 测试输出
	
	@Column(name="user_account")
	private String UserAccount; // 用户账号
	
	
	public int getWorkCaseRecordId() {
		return WorkCaseRecordId;
	}
	public void setWorkCaseRecordId(int workCaseRecordId) {
		WorkCaseRecordId = workCaseRecordId;
	}
	public int getWorkId() {
		return WorkId;
	}
	public void setWorkId(int workId) {
		WorkId = workId;
	}
	public int getProblemId() {
		return ProblemId;
	}
	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getCaseId() {
		return CaseId;
	}
	public void setCaseId(int caseId) {
		CaseId = caseId;
	}
	public String getCaseResult() {
		return CaseResult;
	}
	public void setCaseResult(String caseResult) {
		CaseResult = caseResult;
	}
	public String getAimOutput() {
		return AimOutput;
	}
	public void setAimOutput(String aimOutput) {
		AimOutput = aimOutput;
	}
	public String getAimInput() {
		return AimInput;
	}
	public void setAimInput(String aimInput) {
		AimInput = aimInput;
	}
	public String getTestOutput() {
		return TestOutput;
	}
	public void setTestOutput(String testOutput) {
		TestOutput = testOutput;
	}
	public String getUserAccount() {
		return UserAccount;
	}
	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}
	
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkCaseRecordId", this.WorkCaseRecordId);
		jo.put("ProblemId", this.ProblemId);
		jo.put("UserId", this.UserId);
		jo.put("CaseId", this.CaseId);
		jo.put("CaseResult", this.CaseResult);
		jo.put("AimOutput", this.AimOutput);
		jo.put("AimInput", this.AimInput);
		jo.put("TestOutput", this.TestOutput);
		jo.put("UserAccount", this.UserAccount);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
