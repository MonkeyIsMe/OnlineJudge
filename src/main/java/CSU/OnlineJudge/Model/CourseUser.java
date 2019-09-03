package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//课程，用户关联表
@Entity
@Table(name="tab_usercourse")
public class CourseUser {
	
	@Id
	@Column(name="uc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CourseUserId;  // 主键
	
	@Column(name="course_id")
	private int CourseId;  // 课程编号
	
	@Column(name="user_id")
	private int UserId;  // 用户编号
	
	@Column(name="user_account")
	private String UserAccount; // 用户账号

	public int getCourseUserId() {
		return CourseUserId;
	}

	public void setCourseUserId(int courseUserId) {
		CourseUserId = courseUserId;
	}

	public int getCourseId() {
		return CourseId;
	}

	public void setCourseId(int courseId) {
		CourseId = courseId;
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
		jo.put("CourseUserId", this.CourseUserId);
		jo.put("UserAccount", this.UserAccount);
		jo.put("CourseId", this.CourseId);
		jo.put("UserId", this.UserId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
