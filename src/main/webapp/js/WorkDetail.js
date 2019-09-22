var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

$(function(){
	$("#a_first").attr('data-url','http://localhost:8080/OnlineJudge/WorkData/WorkRanklist.html?WorkId=1');
	$("#a_second").attr('data-url','http://localhost:8080/OnlineJudge/WorkDetail.html?WorkId=1');
	$("#a_third").attr('data-url','http://localhost:8080/OnlineJudge/WorkDetail.html?WorkId=1');
	
});