package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//考试-作业，题目关联表
@Entity
@Table(name="tab_workproblem")
public class WorkProblem {
	
	@Id
	@Column(name="wp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int WorkProblemId; // 主键
	
	@Column(name="problem_id")
	private int ProblemId; // 题目编号
	
	@Column(name="work_id")
	private int WorkId; // 考试/作业编号
	


	public int getWorkProblemId() {
		return WorkProblemId;
	}
	public void setWorkProblemId(int workProblemId) {
		WorkProblemId = workProblemId;
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
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkProblemId", this.WorkProblemId);
		jo.put("ProblemId", this.ProblemId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
