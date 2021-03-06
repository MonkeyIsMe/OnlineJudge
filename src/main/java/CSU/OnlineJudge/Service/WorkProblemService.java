package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.WorkProblem;

public interface WorkProblemService {

	public void addWorkProblem(WorkProblem wp);
	
	public void deleteWorkProblem(WorkProblem wp);
	
	public void updateWorkProblem(WorkProblem wp);
	
	public WorkProblem queryWorkProblem(int id);
	
	public List<WorkProblem> QueryWorkProblemByWorkId(int WorkId);
	
	public Object AddMutiplyWorkProblem(List<WorkProblem> wp_list);
	
	public Object DeleteMutiplyWorkProblem(List<WorkProblem> wp_list);
	
	public int CountByWorkId(int WorkId);
	
	public WorkProblem QueryWorkProblemByWorkProblem(int WorkId,int ProblemId);
}
