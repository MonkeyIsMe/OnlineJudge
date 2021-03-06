var url = decodeURI(window.location.href);

URL = url.split("?")[1]; 
var a = URL.split("&");       
var pid = a[0].split("=")[1]; 
var wid = a[1].split("=")[1]; 
//console.log(wid + " " + pid)
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
				$("#problem_output").append(datas.problemOutput);
				$("#problem_hint").append(datas.problemHint);
				$("#problem_limt").append("Time Limit:  " + datas.problemTimeLimit +"MS&nbsp;&nbsp;&nbsp;&nbsp;" + "Memory Limit: " +datas.problemMemory+ "K");
				$("#problem_submission").append(" Total Submission(s):  " + datas.acceptTimes +"&nbsp;&nbsp;&nbsp;&nbsp;" + "Accepted Submission(s): " +datas.submissionTimes);
				
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
			var url = "WorkProblemCode.html?ProblemId=" + pid +"&WorkId=" + wid;
			window.location.href = url;
	})
	
	
});

function refresh(){
	window.location.replace(url);
}

