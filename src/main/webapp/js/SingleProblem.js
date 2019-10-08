var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];

var pro_content = null;
var in_content = null;
var out_content = null;

 

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"SetUserInfo.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				if(data.user_role == '2'){
				    var url = "http://202.197.66.200:1188/OnlineJudge/Main.html";
				    window.location.replace(url);
				}
			}
			);
	
});


$(document).ready(function(){

	pro_content = CKEDITOR.replace("content"); //参数‘content’是textarea元素的name属性值，而非id属性值
	incontent = CKEDITOR.replace("incontent"); 
	outcontent = CKEDITOR.replace("outcontent"); 
	
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
					CKEDITOR.instances.content.setData(datas.problemInfo);
					CKEDITOR.instances.incontent.setData(datas.problemInput);
					CKEDITOR.instances.outcontent.setData(datas.problemOutput);
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
		var problem_hint = $("#problem_hint").val();
		var problem_time = $("#problem_time").val();
		var problem_memory = $("#problem_memory").val();
		var problem_flag = $('input:radio:checked').val();
		var case_input = $("#case_input").val();
		var case_output = $("#case_output").val();
        var problem_input =  CKEDITOR.instances.incontent.getData(); //获取值
        var problem_output = CKEDITOR.instances.outcontent.getData();
        var problem_info = CKEDITOR.instances.content.getData();
		
		var mflag = false;
		var tflag = false;
		
		mflag = IsNumber(problem_memory);
		tflag = IsNumber(problem_time);
		
		if(problem_name == null || problem_name == "" || problem_info == null || problem_info == "" || problem_input == null || problem_input == ""
			|| problem_output == null || problem_output == "" || problem_hint == null || problem_hint == "" || problem_time == null || problem_time == ""
				|| problem_memory == null || problem_memory == "" || problem_flag == null || problem_flag == ""){
			alert("所有项均为非空!");
		}
		else if(mflag == false){
			alert("内存限制需要为数字!");
		}
		else if(tflag == false){
			alert("时间限制需要为数字!");
		}
		else{
			$.post(
					"UpdateProblem.action",
					{
						problem_id:pid,
						problem_name:problem_name,
						problem_info:problem_info,
						problem_hint:problem_hint,
						problem_memory:problem_memory,
						problem_time:problem_time,
						problem_flag:problem_flag,
						problem_input:problem_input,
						problem_output:problem_output,
						case_input:case_input,
						case_output:case_output,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("更新失败！");
							window.location.replace("ManagerProblemSet.html");
						}
						else{
							alert("更新成功!");
							window.location.replace("ManagerProblemSet.html");
						}
					}
					);
		}
		

		
	})
	
});

function IsNumber(num){
	var isnum = /^\d+$/.test(num);
	return isnum;
}