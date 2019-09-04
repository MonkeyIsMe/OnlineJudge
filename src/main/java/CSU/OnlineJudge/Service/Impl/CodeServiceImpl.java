package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import CSU.OnlineJudge.DAO.CodeDAO;
import CSU.OnlineJudge.Model.Code;
import CSU.OnlineJudge.Service.CodeService;

public class CodeServiceImpl implements CodeService{
	
	private CodeDAO cd;
	
	public CodeDAO getCd() {
		return cd;
	}

	public void setCd(CodeDAO cd) {
		this.cd = cd;
	}

	public void AddCode(Code code) {
		// TODO Auto-generated method stub
		cd.addCode(code);
	}

	public void DeleteCode(Code code) {
		// TODO Auto-generated method stub
		cd.deleteCode(code);
	}

	public void UpdateCode(Code code) {
		// TODO Auto-generated method stub
		cd.updateCode(code);
	}

	public Code QueryCode(int id) {
		// TODO Auto-generated method stub
		return cd.queryCode(id);
	}

	public List<Code> QueryCodeByUserAccountByPageSize(String UserAccount,int row,int PageSize) {
		// TODO Auto-generated method stub
		return cd.QueryCodeByUserAccountByPageSize(UserAccount,row,PageSize);
	}

	public int CountUserCode(String UserAccount) {
		// TODO Auto-generated method stub
		return cd.CountUserCode(UserAccount);
	}

	public Object DeleteMutiplyCode(List<Code> Code) {
		// TODO Auto-generated method stub
		return cd.DeleteMutiplyCode(Code);
	}

	public List<Code> QueryCodeByUserAccount(String UserAccount) {
		// TODO Auto-generated method stub
		return cd.QueryCodeByUserAccount(UserAccount);
	}

}
