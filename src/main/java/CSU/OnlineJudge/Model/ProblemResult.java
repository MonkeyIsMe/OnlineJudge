package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tab_problemresult")
public class ProblemResult {

	@Id
	@Column(name="pr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProblemResultId;   //主键
	
	@Column(name="problem_id")
	private int ProblemId;  //题目的编号
	
	@Column(name="case_result")
	private String CaseResult;  //题目的结果
	
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
	
	public String getCaseResult() {
		return CaseResult;
	}

	public void setCaseResult(String caseResult) {
		CaseResult = caseResult;
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
	
	
	
}
