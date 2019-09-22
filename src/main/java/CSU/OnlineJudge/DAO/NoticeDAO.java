package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Notice;

public interface NoticeDAO {
	
	public void addNotice(Notice notice);
	
	public void deleteNotice(Notice notice);
	
	public void updateNotice(Notice notice);
	
	public Notice queryNotice(int id);
	
	public int CountNotice();
	
	public List<Notice> QueryNoticeByPageSize(int rows,int PageSize);
	
}
