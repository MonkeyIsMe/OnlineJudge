var row = 1;

$(function(){
	$.post(
			"QueryProblemList.action",
			{
				rows:row,
				size:30
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr ></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center;font-size:26px"  + ">"+ data[i].ProblemId +"</td>");
				        $trTemp.append("<td onclick=SeeProblem('"+data[i].ProblemId+"') style=" + "text-align:center;font-size:26px"  + ">"  +data[i].ProblemName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:26px"  + ">" + data[i].AcceptTimes+"/"+data[i].SubmissionTimes +"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#ProblemList");
				    }
				}
		}
	);
	
});

function SeeProblem(pid){
	var url = "ProblemPage.html?ProblemId=" + pid;
	window.location.href = url;
}