var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?userId=");
var uid = argsIndex[1];

var uaccount;

$(function(){
	$.ajaxSettings.async = false;
	
	$.post(
			"QuerySingleUserById.action",
			{
				user_id:uid
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				uaccount = data.userAccount;
				$("#user_name").append(data.userName);
				$("#user_classroom").append("班级：" + data.studentClassroom);
				$("#submission_times").append(data.submissionTimes);
				$("#ac_times").append(data.acceptTimes);
				$("#wa_times").append(data.wrongAnswerTimes);
				$("#tle_times").append(data.timeLimitTimes);
				$("#ce_times").append(data.compileErrorTimes);
		}
	);
	
	$.post(
			"QueryAcceptProblem.action",
			{
				user_account:uaccount,
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0; i < data.length; i ++){
					var html = "<a class=pid href=ProblemPage.html?ProblemId="+ data[i].problemId +">" + data[i].problemId + "</a>";
					$("#right").append(html);
				}
		}
	);
	
	$.post(
			"QueryOtherProblem.action",
			{
				user_account:uaccount,
			}, 
			function(data) {
				var data = JSON.parse(data);
				for(var i = 0; i < data.length; i ++){
					var html = "<a class=pid href=ProblemPage.html?ProblemId="+ data[i].problemId +">" + data[i].problemId + "</a>";
					$("#wrong").append(html);
				}
		}
	);
	

	
});