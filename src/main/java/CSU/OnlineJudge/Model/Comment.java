package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//评论表
@Entity
@Table(name="tab_comment")
public class Comment {

	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CommentId;
	
	@Column(name="comment_info")
	private String CommentInfo;
	
	@Column(name="answer_id")
	private int AnswerId;
	
	@Column(name="comment_time")
	private String CommentTime;
	
	@Column(name="user_id")
	private int UserId;
	
	@Column(name="user_account")
	private String UserAccount;

	public int getCommentId() {
		return CommentId;
	}

	public void setCommentId(int commentId) {
		CommentId = commentId;
	}

	public String getCommentInfo() {
		return CommentInfo;
	}

	public void setCommentInfo(String commentInfo) {
		CommentInfo = commentInfo;
	}

	public int getAnswerId() {
		return AnswerId;
	}

	public void setAnswerId(int answerId) {
		AnswerId = answerId;
	}

	public String getCommentTime() {
		return CommentTime;
	}

	public void setCommentTime(String commentTime) {
		CommentTime = commentTime;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("CommentId", this.CommentId);
		jo.put("AnswerId", this.AnswerId);
		jo.put("UserId", this.UserId);
		jo.put("UserAccount", this.UserAccount);
		jo.put("CommentTime", this.CommentTime);
		jo.put("CommentInfo", this.CommentInfo);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
