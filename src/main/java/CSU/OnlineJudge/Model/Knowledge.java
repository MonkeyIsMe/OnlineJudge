package CSU.OnlineJudge.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

//知识点表
@Entity
@Table(name="tab_knowledge")
public class Knowledge {
	
	@Id
	@Column(name="knowledge_id")
	private int KnowledgeId; //主键
	
	@Column(name="knowledge_name")
	private String KnowledgeName; //知识点名称
	
	@Column(name="knowledge_info")
	private String KnowledgeInfo; //知识点描述
	
	
	public int getKnowledgeId() {
		return KnowledgeId;
	}
	public void setKnowledgeId(int knowledgeId) {
		KnowledgeId = knowledgeId;
	}
	public String getKnowledgeName() {
		return KnowledgeName;
	}
	public void setKnowledgeName(String knowledgeName) {
		KnowledgeName = knowledgeName;
	}
	public String getKnowledgeInfo() {
		return KnowledgeInfo;
	}
	public void setKnowledgeInfo(String knowledgeInfo) {
		KnowledgeInfo = knowledgeInfo;
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("KnowledgeId", this.KnowledgeId);
		jo.put("KnowledgeInfo", this.KnowledgeInfo);
		jo.put("KnowledgeName", this.KnowledgeName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	}
}
