package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//源码表
@Entity
@Table(name="tab_origin")
public class Origin {
	
	@Id
	@Column(name="origin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OriginId;
	
	@Column(name="problem_id")
	private int ProblemId;
	
	@Column(name="problem_name")
	private String ProblemName;
	
	@Column(name="origin_code")
	private String ProblemCode;

	public int getOriginId() {
		return OriginId;
	}

	public void setOriginId(int originId) {
		OriginId = originId;
	}

	public int getProblemId() {
		return ProblemId;
	}

	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}

	public String getProblemName() {
		return ProblemName;
	}

	public void setProblemName(String problemName) {
		ProblemName = problemName;
	}

	public String getProblemCode() {
		return ProblemCode;
	}

	public void setProblemCode(String problemCode) {
		ProblemCode = problemCode;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("OriginId", this.OriginId);
		jo.put("ProblemCode", this.ProblemCode);
		jo.put("ProblemId", this.ProblemId);
		jo.put("ProblemName", this.ProblemName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
