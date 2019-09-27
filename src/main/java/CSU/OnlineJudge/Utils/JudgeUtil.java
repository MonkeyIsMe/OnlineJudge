package CSU.OnlineJudge.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import CSU.OnlineJudge.Model.Case;
import CSU.OnlineJudge.Model.Problem;
import CSU.OnlineJudge.Model.Submission;
import CSU.OnlineJudge.Service.CaseService;
import CSU.OnlineJudge.Service.ProblemService;
import CSU.OnlineJudge.Service.SubmissionService;
import CSU.OnlineJudge.Service.Impl.CaseServiceImpl;
import CSU.OnlineJudge.Service.Impl.ProblemServiceImpl;
import CSU.OnlineJudge.Service.Impl.SubmissionServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JudgeUtil {
	
	
	//发送http请求
	public String Judger(String param) {
		
		String url = "http://192.168.1.192:5000/judge";
		
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            //注意在传送json数据时， Content-Type的值
            conn.setRequestProperty("Content-Type",
                                    "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        
        return result;
	}
	
	//合成样例
	public String DealCase(String CaseInfo,String lang,int time,int memory,String code) {
    	JSONObject finaljo = new JSONObject();
    	finaljo.put("lang", lang);
    	finaljo.put("source_code", code);
    	finaljo.put("time_limit", time);
    	finaljo.put("memory_limit", memory);
    	finaljo.put("test_cases", CaseInfo);
    	
    	
		return finaljo.toString();
	}
	
	//每个样例结果的处理
	public void ResultUtil(String result,int ProblemId) {
		
		JSONObject jo = JSONObject.fromObject(result);
		String case_info =  jo.getString("test_cases");
		JSONArray ja = JSONArray.fromObject(case_info);
		for(int i = 0; i < ja.size(); i ++) {
			JSONObject cjo = ja.getJSONObject(i);
			String error = cjo.getString("error_message");
			String res = cjo.getString("result");
		}
		
	}
	
	//通过提交编号获取题目信息
	public String GetPorblemInfo(int SubmissionId) {
		
		SubmissionService SubmissionService = new SubmissionServiceImpl();
		Submission sub = SubmissionService.querySubmission(SubmissionId);
		
		int pid = sub.getProblemId();
		String lang = sub.getCodeType();
		String code = sub.getSubmissionCode();
		
		ProblemService ProblemService = new ProblemServiceImpl();
		Problem problem = ProblemService.QueryProblem(pid);
		
		int time = problem.getProblemTimeLimit();
		int memory = problem.getProblemMemory();
		
		JSONObject jo = new JSONObject();
		
		jo.put("pid", pid);
		jo.put("lang", lang);
		jo.put("code", code);
		jo.put("time", time);
		jo.put("memory", memory);
		
		return jo.toString();
	}
	
}
