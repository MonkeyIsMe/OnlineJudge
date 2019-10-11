var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];

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
				//console.log(data);
				for(var i = 0; i < data.length; i ++){
					$('input:checkbox[name="know"][value='+data[i].knowledgeId+']').prop('checked', true);
				}
			}
			);
	
});

$(function(){
	$.post(
			"QuerySingleProblem.action",
			{
				problem_id:pid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				//console.log(datas);
				$("#tittle").append(datas.problemName);
				$("#problem_name").append(datas.problemName);
				$("#problem_content").append(datas.problemInfo);
				$("#problem_input").append(datas.problemInput);
				console.log(datas.problemInput);
				$("#problem_output").append(datas.problemOutput);
				$("#problem_hint").append(datas.problemHint);
				$("#problem_limt").append("Time Limit:  " + datas.problemTimeLimit +"MS&nbsp;&nbsp;&nbsp;&nbsp;" + "Memory Limit: " +datas.problemMemory+ "K");
				$("#problem_submission").append(" Total Submission(s):  " + datas.submissionTimes +"&nbsp;&nbsp;&nbsp;&nbsp;" + "Accepted Submission(s): " +datas.acceptTimes);
				
				$("#pname").append(datas.problemName);
				$("#pinfo").append(datas.problemInfo);
				//console.log(datas.problemName);
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
				var datas = JSON.parse(data);
				//console.log(datas);
				$("#example_input").append(datas[0].caseInput);
				$("#example_output").append(datas[0].caseOutput);
			}
	);
	
});


$(function(){
	
	$("#submit").click(function(){
			var url = "ProblemCode.html?ProblemId=" + pid;
			window.open(url);
	})
	
		$("#discuss").click(function(){
			var url = "ProblemDiscuss.html?ProblemId=" + pid;
			window.location.href = url;
	})
	
	$("#add_kp").click(function(){
			var json =[];
			$('input[name="know"]:checked').each(function(){
				var obj = {};
				obj.knowledgeId = $(this).val();
				json.push(obj);//向数组中添加元素  
				});
			var jsonText = JSON.stringify(json);
			//console.log(json);
			
			$.post(
					"AddMutiplyKnowledgeProblem.action",
					{
						problem_id:pid,
						knowledge_info:jsonText,
					}, 
					function(data) {
						
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
	})
	
});

function refresh(){
	window.location.replace(url);
}

function backprev(){
	window.location.replace("ManagerProblemSet.html");
}

