package CSU.OnlineJudge.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Main {
	
	public static void main(String[] args) {
		// json数组，存放json对象
		JSONArray jsonArray = new JSONArray();
		// json对象1
		JSONObject json1 = new JSONObject();
		json1.accumulate("a","1");
		json1.accumulate("b","2");
		json1.accumulate("c","3");
		// json对象2
		JSONObject json2 = new JSONObject();
		json2.accumulate("a","4");
		json2.accumulate("b","5");
		json2.accumulate("c","6");
		
		// 将json对象添加到json数组
		jsonArray.add(json1);
		jsonArray.add(json2);
		
		// 打印json数组
		System.out.println(jsonArray.toString());
	}
}
