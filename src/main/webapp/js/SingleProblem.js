var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];

$(document).ready(function(){

	$(function(){
		$.ajaxSettings.async = false;
		$.post(
				"QuerySingleProblem.action",
				{
					problem_id:pid,
				}, 
				function(data) {
					var datas = JSON.parse(data);
					//console.log(datas);
					$("#problem_name").val(datas.problemName);
					$("#problem_content").val(datas.problemInfo);
					$("#problem_input").val(datas.problemInput);
					$("#problem_output").val(datas.problemOutput);
					$("#problem_hint").val(datas.problemHint);
					$("#problem_time").val(datas.problemTimeLimit);
					$("#problem_memory").val(datas.problemMemory);
					
					if(datas.problemFlag == 0){
						$('input:radio[name="problem"][value="0"]').prop('checked', true);
					}
					else{
						$('input:radio[name="problem"][value="1"]').prop('checked', true);
					}
					
				}
		);
		
	});
	
	$(function(){
		$.post(
				"QueryInCase.action",
				{
					problem_id:pid,
				}, 
				function(data) {
					var data = JSON.parse(data);
					$("#case_input").val(data[0].caseInput);
					$("#case_output").val(data[0].caseOutput);
				}
		);
		
	});
	
	$(function(){
		$.ajaxSettings.async = false;
		$.post(
				"QueryAllKnowledge.action",
				{
				},
				function(data){
					var data = JSON.parse(data);
					//console.log(data);
			           for(var i=0 ;i<data.length;i++){ //几个人有几个checkbox
			        	   $("#allTime").append(
					                "<span>"+
					                    "<input  type='checkbox' class='time' name='know' value='"+data[i].knowledgeId+"' title='"+data[i].knowledgeName+"'>" 
					                 +"</span>");
			           }
				}
				);
		
	});
	
	$(function(){
		$.ajaxSettings.async = false;
		$.post(
				"QueryKnowledgeByProblemId.action",
				{
					problem_id:pid,
				},
				function(data){
					var data = JSON.parse(data);
					for(var i = 0; i < data.length; i ++){
						$('input:checkbox[name="know"][value='+data[i].knowledgeId+']').prop('checked', true);
					}
				}
				);
		
	});
	
	$("#UpdateProblem").click(function(){
		
		var problem_name = $("#problem_name").val();
		var problem_content = $("#problem_content").val();
		var problem_input = $("#problem_input").val();
		var problem_output = $("#problem_output").val();
		var problem_hint = $("#problem_hint").val();
		var problem_time = $("#problem_time").val();
		var problem_memory = $("#problem_memory").val();
		var problem_flag = $('input:radio:checked').val();
		var case_input = $("#case_input").val();
		var case_output = $("#case_output").val();
  		var json =[];
		$('input[name="know"]:checked').each(function(){
			var obj = {};
			obj.knowledgeId = $(this).val();
			json.push(obj);//向数组中添加元素  
			});
		var jsonText = JSON.stringify(json);
		//console.log(jsonText);
		
		$.post(
				"QueryKnowledgeByProblemId.action",
				{
					problem_id:pid,
					problem_name:problem_name,
					problem_info:problem_content,
					problem_hint:problem_hint,
					problem_memory:problem_memory,
					problem_time:problem_time,
					problem_flag:problem_flag,
					problem_input:problem_input,
					problem_output:problem_output,
					knowledge_info:jsonText,
					case_input:case_input,
					case_output:case_output,
				},
				function(data){
					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
					if(data == "Fail"){
						alert("更新失败！");
					    var url = "ManagerSingleProblem.html?ProblemId=" + pid;
					    window.location.replace(url);
					}
					else{
						alert("更新成功!");
						window.location.replace("ManagerProblemSet.html");
					}
				}
				);
		
	})
	
});