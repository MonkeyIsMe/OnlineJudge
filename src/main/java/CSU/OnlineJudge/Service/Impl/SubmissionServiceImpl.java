package CSU.OnlineJudge.Service.Impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CSU.OnlineJudge.DAO.SubmissionDAO;
import CSU.OnlineJudge.DAO.Impl.SubmissionDAOImpl;
import CSU.OnlineJudge.Model.Submission;
import CSU.OnlineJudge.Service.SubmissionService;

@Transactional
public class SubmissionServiceImpl implements SubmissionService{

	private  SubmissionDAO sd;
	
	public SubmissionDAO getSd() {
		return sd;
	}

	public void setSd(SubmissionDAO sd) {
		this.sd = sd;
	}

	public int addSubmission(Submission submission) {
		// TODO Auto-generated method stub
		return sd.addSubmission(submission);
	}

	public void updateSubmission(Submission submission) {
		// TODO Auto-generated method stub
		sd.updateSubmission(submission);
	}

	public void deleteSubmission(Submission submission) {
		// TODO Auto-generated method stub
		sd.deleteSubmission(submission);
	}

	public Submission querySubmission(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Submission> QuerySubmissionByPageSize(int row, int PageSize) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSize(row, PageSize);
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccount(int row, int PageSize, String UserAccount) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSizeWithUserAccount(row, PageSize, UserAccount);
	}

	public List<Submission> QuerySubmissionByPageSizeWithProblemId(int row, int PageSize, int ProblemId) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSizeWithProblemId(row, PageSize, ProblemId);
	}

	public List<Submission> QuerySubmissionByPageSizeWithProblemIdResult(int row, int PageSize, int ProblemId,String result) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSizeWithProblemIdResult(row, PageSize, ProblemId, result);
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccountResult(int row, int PageSize, String UserAccount,String result) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSizeWithUserAccountResult(row, PageSize, UserAccount, result);
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccountProblem(int row, int PageSize, String UserAccount,
			int ProblemId) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSizeWithUserAccountProblem(row, PageSize, UserAccount, ProblemId);
	}

	public List<Submission> QuerySubmissionByPageSizeWithUserAccountResultProblem(int row, int PageSize,
			String UserAccount, String result, int ProblemId) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSizeWithUserAccountResultProblem(row, PageSize, UserAccount, result, ProblemId);
	}

	public List<Submission> QuerySubmissionByPageSizeWithResult(int row, int PageSize, String Result) {
		// TODO Auto-generated method stub
		return sd.QuerySubmissionByPageSizeWithResult(row, PageSize, Result);
	}

}
