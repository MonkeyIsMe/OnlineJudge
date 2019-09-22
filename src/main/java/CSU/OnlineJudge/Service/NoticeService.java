package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Notice;

public interface NoticeService {
	
	public void AddNotice(Notice notice);
	
	public void DeleteNotice(Notice notice);
	
	public void UpdateNotice(Notice notice);
	
	public Notice QueryNotice(int id);
	
	public List<Notice> QueryNoticeByPageSize(int rows,int PageSize);
	
	public int CountNotice();
}
