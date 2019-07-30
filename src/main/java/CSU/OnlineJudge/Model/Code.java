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
	
	@Column(name="code_name")
	private String CodeName; // 代码名字
	
	@Column(name="code_info")
	private String CodeInfo; // 代码

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
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("CaseId", this.CodeId);
		jo.put("CaseFlag", this.UserAccount);
		jo.put("ProblemId", this.CodeName);
		jo.put("CaseInput", this.CodeInfo);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
