var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

$(function(){

	$.post(
			"QuerySingleWork.action",
			{
				work_id:wid
			},
			function(data){
				var data = JSON.parse(data);
				console.log(data);
				var flag = "考试";
				if(data.workFlag == '0'){
					flag = "作业";
				}
				var time = getNowFormatDate();
				if(data.workBeginTime > time){
					alert("该" + flag + data.workName +"还未开始，请等待开始！");
					var url = "WorkSet.html";
					window.location.href = url;
				}
				else if(data.workEndTime < time){
					alert("该" + flag + data.workName +"已经结束！");
					var url = "WorkSet.html";
					window.location.href = url;
				}
			}
			);
	
});


$(function(){
	$.post(
			"QueryPublicProblem.action",
			{
				work_id:wid
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				    for( var i = 0; i < data.length; i++ ) {
				    	if(data[i] == null) continue;
				    	if(data[i].problemId == null || data[i].problemId == "") continue;
				    	
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px;font-family: inherit;"  + ">"+ data[i].problemId +"</td>");
				        $trTemp.append("<td onclick=SeeProblem('"+data[i].problemId+"') style=" + "text-align:center;font-size:16px;font-family: inherit;"  + ">"  +data[i].problemName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px;font-family: inherit;"  + ">" + data[i].acceptTimes+"/"+data[i].submissionTimes +"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#ProblemList");
				    }
		}
	);
	
});

function SeeProblem(pid){
	var url = "ProblemPage.html?ProblemId=" + pid;
	window.location.href = url;
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}