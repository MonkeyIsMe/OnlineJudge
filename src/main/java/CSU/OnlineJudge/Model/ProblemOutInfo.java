package CSU.OnlineJudge.Model;

import org.json.JSONObject;

public class ProblemOutInfo {
	
	private int ProblemId;
	
	private String ProblemName;
	
	private int SubmissionTimes;
	
	private int AcceptTimes;
	
	
	
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

	public int getSubmissionTimes() {
		return SubmissionTimes;
	}

	public void setSubmissionTimes(int submissionTimes) {
		SubmissionTimes = submissionTimes;
	}

	public int getAcceptTimes() {
		return AcceptTimes;
	}

	public void setAcceptTimes(int acceptTimes) {
		AcceptTimes = acceptTimes;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("ProblemId", this.ProblemId);
		jo.put("ProblemName", this.ProblemName);
		jo.put("SubmissionTimes", this.SubmissionTimes);
		jo.put("AcceptTimes", this.AcceptTimes);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
}
