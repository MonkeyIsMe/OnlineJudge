var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];


$(function(){
	$.post(
			"QuerySingleWork.action",
			{
				work_id:wid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				//console.log(datas);
			}
	);
	
});