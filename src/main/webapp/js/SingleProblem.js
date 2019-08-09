var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];

$(function(){
	$.post(
			"QuerySingleProblem.action",
			{
				problem_id:pid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				//console.log(datas);
				$("#problem_name").append(datas.problemName);
				$("#problem_content").append(datas.problemInfo);
				$("#problem_input").append(datas.problemInput);
				$("#problem_output").append(datas.problemOutput);
				$("#problem_hint").append(datas.problemHint);
				$("#problem_timelimt").append(datas.problemTimeLimit);
				$("#problem_memorylimt").append(datas.problemMemory)
				
				
			}
	);
	
});