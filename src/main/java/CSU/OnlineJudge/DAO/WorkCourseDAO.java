package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.WorkCourse;

public interface WorkCourseDAO {
	
	public void addWorkCourse(WorkCourse wc);
	
	public void updateWorkCourse(WorkCourse wc);
	
	public void deleteWorkCourse(WorkCourse wc);
	
	public WorkCourse queryWorkCourse(int id);
	
	public List<WorkCourse> QueryWorkCourseByClassId(int ClassId);
	
	public List<WorkCourse> QueryWorkCourseByWorkId(int WorkId);
	
	public Object addMutiplyWorkCourse(List<WorkCourse> wc);
	
	public Object deleteMutiplyWorkCourse(List<WorkCourse> wc);
}
