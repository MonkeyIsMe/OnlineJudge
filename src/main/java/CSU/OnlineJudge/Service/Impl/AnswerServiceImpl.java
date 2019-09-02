package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.AnswerDAO;
import CSU.OnlineJudge.Model.Answer;
import CSU.OnlineJudge.Service.AnswerService;

@Transactional
public class AnswerServiceImpl implements AnswerService{

	private AnswerDAO ad;
	
	public AnswerDAO getAd() {
		return ad;
	}

	public void setAd(AnswerDAO ad) {
		this.ad = ad;
	}

	public void AddAnswer(Answer answer) {
		// TODO Auto-generated method stub
		ad.addAnswer(answer);
	}

	public void UpdateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		ad.updateAnswer(answer);
	}

	public void DeleteAnswer(Answer answer) {
		// TODO Auto-generated method stub
		ad.deleteAnswer(answer);
	}

	public Answer QueryAnswer(int id) {
		// TODO Auto-generated method stub
		return ad.queryAnswer(id);
	}

	public List<Answer> QueryAnswerByProblemIdPageSize(int ProblemId, int row, int PageSize) {
		// TODO Auto-generated method stub
		return ad.QueryAnswerByProblemIdPageSize(ProblemId, row, PageSize);
	}

	public List<Answer> QueryAnswerByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return ad.QueryAnswerByPageSize(row, PageSize);
	}

	public int CountAnswer() {
		// TODO Auto-generated method stub
		return ad.CountAnswer();
	}

}
