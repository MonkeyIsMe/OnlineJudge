package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.OriginDAO;
import CSU.OnlineJudge.Model.Origin;
import CSU.OnlineJudge.Service.OriginService;

@Transactional
public class OriginServiceImpl implements OriginService{
	
	private OriginDAO od;
	
	public OriginDAO getOd() {
		return od;
	}

	public void setOd(OriginDAO od) {
		this.od = od;
	}

	public void AddOrigin(Origin origin) {
		// TODO Auto-generated method stub
		od.addOrigin(origin);
	}

	public void UpdateOrigin(Origin origin) {
		// TODO Auto-generated method stub
		od.updateOrigin(origin);
	}

	public void DeleteOrigin(Origin origin) {
		// TODO Auto-generated method stub
		od.deleteOrigin(origin);
	}

	public Origin queryOrigin(int id) {
		// TODO Auto-generated method stub
		return od.queryOrigin(id);
	}

	public List<Origin> QueryOriginByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return od.QueryOriginByPageSize(row, PageSize);
	}

	public List<Origin> QueryOriginByProblemId(int ProblemId) {
		// TODO Auto-generated method stub
		return od.QueryOriginByProblemId(ProblemId);
	}

}
