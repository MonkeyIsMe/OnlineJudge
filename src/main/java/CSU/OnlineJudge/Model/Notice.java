package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//公告表
@Entity
@Table(name="tab_notice")
public class Notice {
	
	@Id
	@Column(name="notice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int NoticeId;  //主键
	
	@Column(name="notice_name")
	private String NoticeName;  //公告名称
	
	@Column(name="notice_info")
	private String NoticeInfo;  //公告具体信息
	
	@Column(name="notice_time")
	private String NoticeTime;  //公告发布时间
	
	@Column(name="notice_people")
	private String NoticePeople;  //公告发布者


	public int getNoticeId() {
		return NoticeId;
	}

	public void setNoticeId(int noticeId) {
		NoticeId = noticeId;
	}

	public String getNoticeName() {
		return NoticeName;
	}

	public void setNoticeName(String noticeName) {
		NoticeName = noticeName;
	}

	public String getNoticeInfo() {
		return NoticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		NoticeInfo = noticeInfo;
	}

	public String getNoticeTime() {
		return NoticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		NoticeTime = noticeTime;
	}

	public String getNoticePeople() {
		return NoticePeople;
	}

	public void setNoticePeople(String noticePeople) {
		NoticePeople = noticePeople;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("NoticeId", this.NoticeId);
		jo.put("NoticeName", this.NoticeName);
		jo.put("NoticeInfo", this.NoticeInfo);
		jo.put("NoticeTime", this.NoticeTime);
		jo.put("NoticePeople", this.NoticePeople);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
