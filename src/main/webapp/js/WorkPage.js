var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];


$(function(){
	$.post(
			"QueryPublicProblem.action",
			{
				work_id:wid
			}, 
			function(data) {
				var data = JSON.parse(data);
				console.log(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
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
		}
	);
	
});