package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.WorkDAO;
import CSU.OnlineJudge.DAO.Impl.WorkDAOImpl;
import CSU.OnlineJudge.Model.Work;
import CSU.OnlineJudge.Service.WorkService;

@Transactional
public class WorkServiceImpl implements WorkService{

	private  WorkDAO wd;
	
	public WorkDAO getWd() {
		return wd;
	}

	public void setWd(WorkDAO wd) {
		this.wd = wd;
	}

	public void addWork(Work work) {
		// TODO Auto-generated method stub
		wd.addWork(work);
	}

	public void deleteWork(Work work) {
		// TODO Auto-generated method stub
		wd.deleteWork(work);
	}

	public void updateWork(Work work) {
		// TODO Auto-generated method stub
		wd.updateWork(work);
	}

	public Work queryWork(int id) {
		// TODO Auto-generated method stub
		return wd.queryWork(id);
	}

	public List<Work> QueryWorkByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return wd.QueryWorkByPageSize(row, PageSize);
	}

	public List<Work> QueryWorkByPageSizeWithClassId(int row, int PageSize, int ClassId) {
		// TODO Auto-generated method stub
		return wd.QueryWorkByPageSizeWithClassId(row, PageSize, ClassId);
	}

	public List<Work> QueryHomeworkByPageSize(int row, int PageSize, int Flag) {
		// TODO Auto-generated method stub
		return wd.QueryWorkByPageSizeWithFlag(row, PageSize, 0);
	}

	public List<Work> QueryExamByPageSize(int row, int PageSize, int Flag) {
		// TODO Auto-generated method stub
		return wd.QueryWorkByPageSizeWithFlag(row, PageSize, 1);
	}

	public List<Work> QueryWorkByPageSizeWithOwner(int row, int PageSize, String WorkOwner) {
		// TODO Auto-generated method stub
		return wd.QueryWorkByPageSizeWithOwner(row, PageSize, WorkOwner);
	}

	public List<Work> QueryHomeWorkByPageSizeWithClassId(int row, int PageSize, int ClassId, int Flag) {
		// TODO Auto-generated method stub
		return wd.QueryWorkByPageSizeWithClassIdFlag(row, PageSize, ClassId, 0);
	}

	public List<Work> QueryExamByPageSizeWithClassId(int row, int PageSize, int ClassId, int Flag) {
		// TODO Auto-generated method stub
		return wd.QueryWorkByPageSizeWithClassIdFlag(row, PageSize, ClassId, 1);
	}

}
