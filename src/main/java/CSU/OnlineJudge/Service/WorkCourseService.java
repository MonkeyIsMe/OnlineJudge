package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.WorkCourse;

public interface WorkCourseService {

	public void addWorkCourse(WorkCourse wc);
	
	public void updateWorkCourse(WorkCourse wc);
	
	public void deleteWorkCourse(WorkCourse wc);
	
	public WorkCourse queryWorkCourse(int id);
	
	public List<WorkCourse> QueryWorkCourseByClassId(int ClassId);
	
	public List<WorkCourse> QueryWorkCourseByWorkId(int WorkId);
	
}
