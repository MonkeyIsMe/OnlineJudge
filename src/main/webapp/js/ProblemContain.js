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
				console.log(datas);
				$("#problem_name").append(datas.problemName);
				$("#problem_content").append(datas.problemInfo);
				$("#problem_input").append(datas.problemInput);
				$("#problem_output").append(datas.problemOutput);
				$("#problem_hint").append(datas.problemHint);
				$("#problem_limt").append("Time Limit:  " + datas.problemTimeLimit +"MS&nbsp;&nbsp;&nbsp;&nbsp;" + "Memory Limit: " +datas.problemMemory+ "K");
				$("#problem_submission").append(" Total Submission(s):  " + datas.acceptTimes +"&nbsp;&nbsp;&nbsp;&nbsp;" + "Accepted Submission(s): " +datas.submissionTimes);
				
			}
	);
	
});