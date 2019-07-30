package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_work")
public class WorkCourse {
	
	@Id
	@Column(name="pw_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int WorkCourseId;  // 主键
	
	@Column(name="work_id")
	private int WorkId;  // 考试-作业编号
	
	@Column(name="course_id")
	private int CourseId; //课程编号
	
	
	public int getWorkCourseId() {
		return WorkCourseId;
	}
	public void setWorkCourseId(int workCourseId) {
		WorkCourseId = workCourseId;
	}
	public int getWorkId() {
		return WorkId;
	}
	public void setWorkId(int workId) {
		WorkId = workId;
	}
	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkCourseId", this.WorkCourseId);
		jo.put("CourseId", this.CourseId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
	
}
