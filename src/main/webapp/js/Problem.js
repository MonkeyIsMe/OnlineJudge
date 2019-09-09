var row = 1;
var count; //总记录数

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountProblem.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.ProblemCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
	$.post(
			"QueryProblemList.action",
			{
				page:row,
				limit:30
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr ></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].ProblemId +"</td>");
				        $trTemp.append("<td onclick=SeeProblem('"+data[i].ProblemId+"') style=" + "text-align:center;font-size:16px"  + ">"  +data[i].ProblemName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].AcceptTimes+"/"+data[i].SubmissionTimes +"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#ProblemList");
				    }
				}
		}
	);
	
});

function PrevPage(){
	if(row == 1){
		alert("没有前一页了");
	}
	else{
		row--;
		$("#ProblemList").html("");
		$.post(
				"QueryProblemList.action",
				{
					page:row,
					limit:30
				}, 
				function(data) {
					var data = JSON.parse(data);
					//console.log(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].ProblemId +"</td>");
					        $trTemp.append("<td onclick=SeeProblem('"+data[i].ProblemId+"') style=" + "text-align:center;font-size:16px"  + ">"  +data[i].ProblemName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].AcceptTimes+"/"+data[i].SubmissionTimes +"</td>");
					        // $("#J_TbData").append($trTemp);
					        $trTemp.appendTo("#ProblemList");
					    }
					}
			}
		);
	}
}

function NextPage(){
	if(row == count){
		alert("没有后一页了");
	}
	else{
		row ++;
		$("#ProblemList").html("");
		$.post(
				"QueryProblemList.action",
				{
					page:row,
					limit:30
				}, 
				function(data) {
					var data = JSON.parse(data);
					//console.log(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].ProblemId +"</td>");
					        $trTemp.append("<td onclick=SeeProblem('"+data[i].ProblemId+"') style=" + "text-align:center;font-size:16px"  + ">"  +data[i].ProblemName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].AcceptTimes+"/"+data[i].SubmissionTimes +"</td>");
					        // $("#J_TbData").append($trTemp);
					        $trTemp.appendTo("#ProblemList");
					    }
					}
			}
		);
	}

}

function SeeProblem(pid){
	var url = "ProblemPage.html?ProblemId=" + pid;
	window.location.href = url;
}