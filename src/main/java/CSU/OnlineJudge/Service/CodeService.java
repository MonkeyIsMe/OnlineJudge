package CSU.OnlineJudge.Service;

import java.util.List;

import CSU.OnlineJudge.Model.Code;

public interface CodeService {
	
	public void AddCode(Code code);
	
	public void DeleteCode(Code code);
	
	public void UpdateCode(Code code);
	
	public Code QueryCode(int id);
	
	public List<Code> QueryCodeByUserAccountByPageSize(String UserAccount,int row,int PageSize);
	
	public List<Code> QueryCodeByUserAccount(String UserAccount);
	
	public int CountUserCode(String UserAccount);
	
	public Object DeleteMutiplyCode(List<Code> Code);
}
