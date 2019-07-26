package CSU.OnlineJudge.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//考试-作业表
@Entity
@Table(name="tab_work")
public class Work {
	
	@Id
	@Column(name="work_id")
	private int WorkId;  // 主键
	
	@Column(name="work_name")
	private String WorkName;  // 考试/竞赛名称
	
	@Column(name="work_starttime")
	private String WorkBeginTime;  // 考试/竞赛开始时间
	
	@Column(name="work_endtime")
	private String WorkEndTime;  // 考试/竞赛结束时间
	
	@Column(name="work_createtime")
	private String WorkCreatTime;  // 考试/竞赛创建时间
	
	@Column(name="work_info")
	private String WorkInfo;  // 考试/竞赛描述
	
	@Column(name="work_owner")
	private String WorkOwner;  // 考试/竞赛创建者
	
	@Column(name="work_flag")
	private String WorkFlag; //标志位，0代表作业，1代表考试
	
	@Column(name="class_id")
	private int ClassId;  // 所属课程 
	
	public int getWorkId() {
		return WorkId;
	}
	public void setWorkId(int workId) {
		WorkId = workId;
	}
	public String getWorkName() {
		return WorkName;
	}
	public void setWorkName(String workName) {
		WorkName = workName;
	}
	public String getWorkBeginTime() {
		return WorkBeginTime;
	}
	public void setWorkBeginTime(String workBeginTime) {
		WorkBeginTime = workBeginTime;
	}
	public String getWorkEndTime() {
		return WorkEndTime;
	}
	public void setWorkEndTime(String workEndTime) {
		WorkEndTime = workEndTime;
	}
	public String getWorkCreatTime() {
		return WorkCreatTime;
	}
	public void setWorkCreatTime(String workCreatTime) {
		WorkCreatTime = workCreatTime;
	}
	public String getWorkInfo() {
		return WorkInfo;
	}
	public void setWorkInfo(String workInfo) {
		WorkInfo = workInfo;
	}
	public String getWorkOwner() {
		return WorkOwner;
	}
	public void setWorkOwner(String workOwner) {
		WorkOwner = workOwner;
	}
	public String getWorkFlag() {
		return WorkFlag;
	}
	public void setWorkFlag(String workFlag) {
		WorkFlag = workFlag;
	}
	public int getClassId() {
		return ClassId;
	}
	public void setClassId(int classId) {
		ClassId = classId;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("WorkId", this.WorkId);
		jo.put("WorkName", this.WorkName);
		jo.put("WorkBeginTime", this.WorkBeginTime);
		jo.put("WorkEndTime", this.WorkEndTime);
		jo.put("WorkCreatTime", this.WorkCreatTime);
		jo.put("WorkInfo", this.WorkInfo);
		jo.put("WorkOwner", this.WorkOwner);
		jo.put("WorkFlag", this.WorkFlag);
		jo.put("ClassId", this.ClassId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
