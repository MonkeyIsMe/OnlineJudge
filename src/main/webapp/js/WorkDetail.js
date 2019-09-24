var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

$(function(){
	$("#a_first").attr('data-url','http://localhost:8080/OnlineJudge/WorkData/SubmissionData.html?WorkId=' + wid);
	$("#a_second").attr('data-url','http://localhost:8080/OnlineJudge/WorkData/ProblemData.html?WorkId=' + wid);
	$("#a_third").attr('data-url','http://localhost:8080/OnlineJudge/WorkData/WorkRanklist.html?WorkId=' + wid);
	/*$("#a_fourth").attr('data-url','http://localhost:8080/OnlineJudge/WorkData/WorkStatus.html?WorkId=' + wid);*/
});