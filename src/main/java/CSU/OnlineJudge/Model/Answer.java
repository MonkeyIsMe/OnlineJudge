package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//题解表
@Entity
@Table(name="tab_answer")
public class Answer {
	
	@Id
	@Column(name="answer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int AnswerId;  //主键
	
	@Column(name="answer_info")
	private String AnswerInfo;  //题解信息
	
	@Column(name="problem_id")
	private int ProblemId;  //题目编号
	
	@Column(name="user_id")
	private int UserId;  // 用户编号
	
	@Column(name="user_account")
	private String UserAccount;  //用户账号
	
	@Column(name="answer_time")
	private String AnswerTime;  //题解发布的时间
	
	@Column(name="problem_name")
	private String ProblemName;  //题目名称
	
	@Column(name="answer_name")
	private String AnswerName;  //题目名称

	public int getAnswerId() {
		return AnswerId;
	}

	public void setAnswerId(int answerId) {
		AnswerId = answerId;
	}

	public String getAnswerInfo() {
		return AnswerInfo;
	}

	public void setAnswerInfo(String answerInfo) {
		AnswerInfo = answerInfo;
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

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

	public String getAnswerTime() {
		return AnswerTime;
	}

	public void setAnswerTime(String answerTime) {
		AnswerTime = answerTime;
	}
	public String getProblemName() {
		return ProblemName;
	}

	public void setProblemName(String problemName) {
		ProblemName = problemName;
	}
	public String getAnswerName() {
		return AnswerName;
	}

	public void setAnswerName(String answerName) {
		AnswerName = answerName;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("AnswerId", this.AnswerId);
		jo.put("UserId", this.UserId);
		jo.put("ProblemId", this.ProblemId);
		jo.put("UserAccount", this.UserAccount);
		jo.put("AnswerTime", this.AnswerTime);
		jo.put("AnswerInfo", this.AnswerInfo);
		jo.put("AnswerName", this.AnswerName);
		jo.put("ProblemName", this.ProblemName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
