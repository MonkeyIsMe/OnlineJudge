package CSU.OnlineJudge.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//课程表
@Entity
@Table(name="tab_case")
public class Course {
	
	@Id
	@Column(name="course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CourseId;  // 主键
	
	@Column(name="course_name")
	private String CourseName;  // 课程名称
	
	@Column(name="course_info")
	private String CourseInfo;  // 课程描述
	
	@Column(name="course_time")
	private String CourseTime;  // 开课时间
	

	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String className) {
		CourseName = className;
	}
	public String getCourseInfo() {
		return CourseInfo;
	}
	public void setCourseInfo(String classInfo) {
		CourseInfo = classInfo;
	}
	
	public String getCourseTime() {
		return CourseTime;
	}
	public void setCourseTime(String courseTime) {
		CourseTime = courseTime;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("CourseId", this.CourseId);
		jo.put("CourseName", this.CourseName);
		jo.put("CourseInfo", this.CourseInfo);
		jo.put("CourseTime", this.CourseTime);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
