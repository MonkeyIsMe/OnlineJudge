package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.WorkProblem;

public interface WorkProblemService {

	public void addWorkProblem(WorkProblem wp);
	
	public void deleteWorkProblem(WorkProblem wp);
	
	public void updateWorkProblem(WorkProblem wp);
	
	public WorkProblem queryWorkProblem(int id);
	
	public List<WorkProblem> QueryWorkProblemByWorkId(int WorkId);
	
}
