var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

$(function(){
	$("#a_first").attr('data-url','SubmissionData.html?WorkId=' + wid);
	$("#a_second").attr('data-url','ProblemData.html?WorkId=' + wid);
	$("#a_third").attr('data-url','WorkRanklist.html?WorkId=' + wid);
	$("#a_fourth").attr('data-url','WorkStatus.html?WorkId=' + wid);
});