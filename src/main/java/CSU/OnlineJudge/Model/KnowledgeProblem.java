package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//知识点题目关联表
@Entity
@Table(name="tab_knowledgeproblem")
public class KnowledgeProblem {
	
	@Id
	@Column(name="kp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int KPid;  // 主键
	
	@Column(name="problem_id")
	private int ProblemId;  // 题目编号
	
	@Column(name="knowledge_id")
	private int KnowledgeId; // 知识点编号
	

	public int getKPid() {
		return KPid;
	}
	public void setKPid(int kPid) {
		KPid = kPid;
	}
	public int getProblemId() {
		return ProblemId;
	}
	public void setProblemId(int problemId) {
		ProblemId = problemId;
	}
	public int getKnowledgeId() {
		return KnowledgeId;
	}
	public void setKnowledgeId(int knowledgeId) {
		KnowledgeId = knowledgeId;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("KPid", this.KPid);
		jo.put("ProblemId", this.ProblemId);
		jo.put("KnowledgeId", this.KnowledgeId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
