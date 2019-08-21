package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_problemresult")
public class ProblemResult {

	@Id
	@Column(name="pr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProblemResultId;   //主键
	
	@Column(name="problem_id")
	private int ProblemId;  //题目的编号
	
	@Column(name="problem_result")
	private String ProblemResult;  //题目的结果
	
	@Column(name="user_id")
	private int UserId; //用户编号
	
	@Column(name="user_account")
	private String UserAccount; //用户账号

	public int getProblemResultId() {
		return ProblemResultId;
	}

	public void setProblemResultId(int problemResultId) {
		ProblemResultId = problemResultId;
	}

	public int getProblemId() {
		return ProblemId;
	}

	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}
	

	public String getProblemResult() {
		return ProblemResult;
	}

	public void setProblemResult(String problemResult) {
		ProblemResult = problemResult;
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
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("UserAccount", this.UserAccount);
		jo.put("UserId", this.UserId);
		jo.put("ProblemResult", this.ProblemResult);
		jo.put("ProblemId", this.ProblemId);
		jo.put("ProblemResultId", this.ProblemResultId);
		
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
