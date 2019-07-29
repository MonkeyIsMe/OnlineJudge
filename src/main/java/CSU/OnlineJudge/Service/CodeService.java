package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Code;

public interface CodeService {
	
	public void AddCode(Code code);
	
	public void DeleteCode(Code code);
	
	public void UpdateCode(Code code);
	
	public Code QueryCode(int id);
	
	public List<Code> QueryCodeByUserAccount(String UserAccount);
}
