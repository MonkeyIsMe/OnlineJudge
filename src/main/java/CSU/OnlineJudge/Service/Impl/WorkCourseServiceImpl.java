package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkCourseDAO;
import CSU.OnlineJudge.DAO.Impl.WorkCourseDAOImpl;
import CSU.OnlineJudge.Model.WorkCourse;
import CSU.OnlineJudge.Service.WorkCourseService;

@Transactional
public class WorkCourseServiceImpl implements WorkCourseService{
	
	private  WorkCourseDAO wcd;

	public WorkCourseDAO getWcd() {
		return wcd;
	}

	public void setWcd(WorkCourseDAO wcd) {
		this.wcd = wcd;
	}

	public void addWorkCourse(WorkCourse wc) {
		// TODO Auto-generated method stub
		wcd.addWorkCourse(wc);
	}

	public void updateWorkCourse(WorkCourse wc) {
		// TODO Auto-generated method stub
		wcd.updateWorkCourse(wc);
	}

	public void deleteWorkCourse(WorkCourse wc) {
		// TODO Auto-generated method stub
		wcd.deleteWorkCourse(wc);
	}

	public WorkCourse queryWorkCourse(int id) {
		// TODO Auto-generated method stub
		return wcd.queryWorkCourse(id);
	}

	public List<WorkCourse> QueryWorkCourseByClassId(int ClassId) {
		// TODO Auto-generated method stub
		return wcd.QueryWorkCourseByClassId(ClassId);
	}

	public List<WorkCourse> QueryWorkCourseByWorkId(int WorkId) {
		// TODO Auto-generated method stub
		return wcd.QueryWorkCourseByWorkId(WorkId);
	}

}
