package CSU.OnlineJudge.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import CSU.OnlineJudge.DAO.CaseDAO;
import CSU.OnlineJudge.Utils.JudgeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpRequest {

    public static String sendPost(String url, String param)
  {  
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

    public static void main(String[] args)
    {
    	JSONObject fianljo = new JSONObject();
    	JSONArray caseja = new JSONArray();
    	JSONObject casejo = new JSONObject();
    	for(int i = 1; i < 5; i ++) {
    		
    		casejo.put("stdin", "5 5");
    		casejo.put("stdout", "10");
    		//System.out.println(casejo.toString());
    		caseja.add(casejo);
    	}
    	//System.out.println(caseja.size());
    	for(int i = 0;i<caseja.size(); i ++) {
    		//System.out.println(caseja.get(i).toString());
    	}
/*    	fianljo.put("lang", "CPP");
    	fianljo.put("source_code", "#include<cstdio>\r\n" + 
    			"#include<cstring>\r\n" + 
    			"#include<algorithm>\r\n" + 
    			"#include<iostream>\r\n" + 
    			"using namespace std;\r\n" + 
    			"\r\n" + 
    			"int main()\r\n" + 
    			"{\r\n" + 
    			"    int a,b;\r\n" + 
    			"    cin>>a>>b;\r\n" + 
    			"    cout<<a+b<<endl;\r\n" + 
    			"    return 0;\r\n" + 
    			"}");
    	fianljo.put("time_limit", 3);
    	fianljo.put("memory_limit", 228);
    	fianljo.put("test_cases", caseja.toString());
        String url = "http://192.168.1.192:5000/judge";
        //发送 POST 请求
        String str = HttpRequest.sendPost(url,fianljo.toString());
        System.out.println(str);*/

    }

}