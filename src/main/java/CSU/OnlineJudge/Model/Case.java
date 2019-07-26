package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//样例表
@Entity
@Table(name="tab_case")
public class Case {

	@Id
	@Column(name="case_id")
	private int CaseId;  //主键
	
	@Column(name="case_flag")
	private int CaseFlag;  //题目标志位，0代表题面样例，1代表测试样例
	
	@Column(name="problem_id")
	private int ProblemId; //问题编号
	
	@Column(name="case_input")
	private String CaseInput; //输入
	
	@Column(name="case_output")
	private String CaseOutput; //输出
	
	public int getCaseId() {
		return CaseId;
	}
	public void setCaseId(int caseId) {
		CaseId = caseId;
	}
	public int getCaseFlag() {
		return CaseFlag;
	}
	public void setCaseFlag(int caseFlag) {
		CaseFlag = caseFlag;
	}
	public int getProblemId() {
		return ProblemId;
	}
	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}
	public String getCaseInput() {
		return CaseInput;
	}
	public void setCaseInput(String caseInput) {
		CaseInput = caseInput;
	}
	public String getCaseOutput() {
		return CaseOutput;
	}
	public void setCaseOutput(String caseOutput) {
		CaseOutput = caseOutput;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("CaseId", this.CaseId);
		jo.put("CaseFlag", this.CaseFlag);
		jo.put("ProblemId", this.ProblemId);
		jo.put("CaseInput", this.CaseInput);
		jo.put("CaseOutput", this.CaseOutput);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
