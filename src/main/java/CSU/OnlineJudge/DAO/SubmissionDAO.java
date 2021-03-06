package CSU.OnlineJudge.DAO;

import java.util.List;

import CSU.OnlineJudge.Model.Submission;

public interface SubmissionDAO {
	
	public int addSubmission(Submission submission);
	
	public void updateSubmission(Submission submission);
	
	public void deleteSubmission(Submission submission);
	
	public Submission querySubmission(int id);
	
	public int CountSubmission();
	
	public int CountSubmissionByUser(String UserAccount);
	
	public List<Submission> QuerySubmissionByPageSize(int row,int PageSize);
	
	public List<Submission> QuerySubmissionByPageSizeWithUserAccount(int row,int PageSize,String UserAccount);
	
	public List<Submission> QuerySubmissionByPageSizeWithProblemId(int row,int PageSize,int ProblemId);
	
	public List<Submission> QuerySubmissionByPageSizeWithWorkId(int row,int PageSize,int WorkId);
	
	public List<Submission> QuerySubmissionByPageSizeWithResult(int row,int PageSize,String Result);
	
	public List<Submission> QuerySubmissionByPageSizeWithProblemIdResult(int row,int PageSize,int ProblemId,String result);
	
	public List<Submission> QuerySubmissionByPageSizeWithUserAccountResult(int row,int PageSize,String UserAccount,String result);
	
	public List<Submission> QuerySubmissionByPageSizeWithUserAccountProblem(int row,int PageSize,String UserAccount,int ProblemId);
	
	public List<Submission> QuerySubmissionByPageSizeWithUserAccountResultProblem(int row,int PageSize,String UserAccount,String result,int ProblemId);
}
