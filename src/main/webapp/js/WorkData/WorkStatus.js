var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QuerySingleRecordByWorkId.action",
			{
				work_id:wid
			},
			function(data){
				var data = JSON.parse(data);
				console.log(data);
				$("#sub").append(data.submitNumber);
				$("#ac").append(data.acceptTimes);
				$("#wa").append(data.wrongAnswerTimes);
				$("#ce").append(data.compileErrorTimes);
				$("#rte").append(data.runtimeErrorTimes);
				$("#tle").append(data.timeLimitTimes);
				$("#rate").append(GetPercent(data.acceptTimes,data.submitNumber));
			}
			);
	
});


function refresh(){
	window.location.replace(url);
}

function GetPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00)+"%";
}