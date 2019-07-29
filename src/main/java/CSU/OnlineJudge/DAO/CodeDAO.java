package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Code;

public interface CodeDAO {
	
	public void addCode(Code code);
	
	public void deleteCode(Code code);
	
	public void updateCode(Code code);
	
	public Code queryCode(int id);
	
	public List<Code> QueryCodeByUserAccount(String UserAccount);
	
}
