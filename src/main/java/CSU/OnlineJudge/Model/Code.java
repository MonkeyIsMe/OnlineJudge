package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//个人保存代码表
@Entity
@Table(name="tab_code")
public class Code {
	
	@Id
	@Column(name="code_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CodeId;  // 主键
	
	@Column(name="user_account")
	private String UserAccount; // 用户账号
	
	@Column(name="code_file")
	private String CodeFile; // 文件
	
	@Column(name="code_info")
	private String CodeInfo; // 代码
	
	@Column(name="code_time")
	private String CodeTime; // 创建时间
	
	@Column(name="code_name")
	private String CodeName; // 代码名字
	
	@Column(name="code_type")
	private String CodeType; // 代码类型

	public int getCodeId() {
		return CodeId;
	}

	public void setCodeId(int codeId) {
		CodeId = codeId;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

	public String getCodeName() {
		return CodeName;
	}

	public void setCodeName(String codeName) {
		CodeName = codeName;
	}

	public String getCodeInfo() {
		return CodeInfo;
	}

	public void setCodeInfo(String codeInfo) {
		CodeInfo = codeInfo;
	}
	
	public String getCodeTime() {
		return CodeTime;
	}

	public void setCodeTime(String codeTime) {
		CodeTime = codeTime;
	}

	public String getCodeFile() {
		return CodeFile;
	}

	public void setCodeFile(String codeFile) {
		CodeFile = codeFile;
	}

	public String getCodeType() {
		return CodeType;
	}

	public void setCodeType(String codeType) {
		CodeType = codeType;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("CodeId", this.CodeId);
		jo.put("UserAccount", this.UserAccount);
		jo.put("CodeName", this.CodeName);
		jo.put("CodeInfo", this.CodeInfo);
		jo.put("CodeTime", this.CodeTime);
		jo.put("CodeFile", this.CodeFile);
		jo.put("CodeType", this.CodeType);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
