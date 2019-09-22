package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.NoticeDAO;
import CSU.OnlineJudge.Model.Notice;
import CSU.OnlineJudge.Service.NoticeService;

@Transactional
public class NoticeServiceImpl implements NoticeService{
	
	private NoticeDAO nd;
	
	public NoticeDAO getNd() {
		return nd;
	}

	public void setNd(NoticeDAO nd) {
		this.nd = nd;
	}

	public void AddNotice(Notice notice) {
		// TODO Auto-generated method stub
		nd.addNotice(notice);
	}

	public void DeleteNotice(Notice notice) {
		// TODO Auto-generated method stub
		nd.deleteNotice(notice);
	}

	public void UpdateNotice(Notice notice) {
		// TODO Auto-generated method stub
		nd.updateNotice(notice);
	}

	public Notice QueryNotice(int id) {
		// TODO Auto-generated method stub
		return nd.queryNotice(id);
	}

	public List<Notice> QueryNoticeByPageSize(int rows, int PageSize) {
		// TODO Auto-generated method stub
		return nd.QueryNoticeByPageSize(rows, PageSize);
	}

	public int CountNotice() {
		// TODO Auto-generated method stub
		return nd.CountNotice();
	}

}
