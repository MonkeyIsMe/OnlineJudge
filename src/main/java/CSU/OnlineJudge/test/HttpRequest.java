package CSU.OnlineJudge.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

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
    	String json = "{\r\n" + 
    			"    \"lang\": \"PYTHON35\",\r\n" + 
    			"    \"source_code\": \"a = input()\\nprint(a)\",\r\n" + 
    			"    \"time_limit\": 3,\r\n" + 
    			"    \"memory_limit\": 128,\r\n" + 
    			"    \"test_cases\": [\r\n" + 
    			"        {\r\n" + 
    			"            \"stdin\": \"1\",\r\n" + 
    			"            \"stdout\": \"1\"\r\n" + 
    			"        },\r\n" + 
    			"        {\r\n" + 
    			"            \"stdin\": \"2\",\r\n" + 
    			"            \"stdout\": \"2\"\r\n" + 
    			"        }\r\n" + 
    			"    ]\r\n" + 
    			"}";
    	//String test= "aaaa" + "\n" + "45456";
    	//System.out.println(json);
    	JSONObject fianljo = new JSONObject();
    	JSONArray caseja = new JSONArray();
    	JSONObject casejo = new JSONObject();
    	for(int i = 1; i < 5; i ++) {
    		
    		casejo.put("stdin", i+"");
    		casejo.put("stdout", i+"");
    		//System.out.println(casejo.toString());
    		caseja.add(casejo);
    	}
    	//System.out.println(caseja.size());
    	for(int i = 0;i<caseja.size(); i ++) {
    		//System.out.println(caseja.get(i).toString());
    	}
    	fianljo.put("lang", "PYTHON35");
    	fianljo.put("source_code", "a = input()\nprint(a)");
    	fianljo.put("time_limit", 3);
    	fianljo.put("memory_limit", 228);
    	fianljo.put("test_cases", caseja.toString());
    	String test = responseFormat(fianljo.toString());
    	System.out.println(test);
        String url = "http://192.168.1.192:5000/judge";
        //发送 POST 请求
        String str = HttpRequest.sendPost(url,fianljo.toString());
        System.out.println(str);
    }
    
    private static String responseFormat(String resString){
        
        StringBuffer jsonForMatStr = new StringBuffer();
        int level = 0;
        for(int index=0;index<resString.length();index++)//将字符串中的字符逐个按行输出
        {
            //获取s中的每个字符
            char c = resString.charAt(index);
 
            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0  && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }
    /**
     * @param level
     * @return
     * @throws
     * @author lgh
     * @date 2018/10/29-14:29
     */
    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}