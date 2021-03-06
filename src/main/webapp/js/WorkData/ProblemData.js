var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];
var count;
var row = 1;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountRecordByWorkId.action",
			{
				work_id:wid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				var sum = datas.WorkProblemCount;
				var total = "共" + Math.ceil(sum/25) + "页";
				count = Math.ceil(sum/25);
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
	);
	
});



$(function(){
    $.post(
            "QueryRecordByWorkId.action",
            {
            	work_id:wid,
            },
            function(data) {
                var data = JSON.parse(data);
                //console.log(data);
                    for( var i = 0; i < data.length; i++ ) {
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr ></tr>");
                        //往行里面追加 td单元格
    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTimes  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].acceptTimes  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].wrongAnswerTimes  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].timeLimitTimes  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].compileErrorTimes  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].runtimeErrorTimes  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemMemory  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemTimeLimit  +"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#KnowList");
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
		$("#KnowList").html("");
	    $.post(
	            "QueryRecordByWorkId.action",
	            {
	            	work_id:wid,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].acceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].wrongAnswerTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].timeLimitTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].compileErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].runtimeErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemMemory  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemTimeLimit  +"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
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
		$("#KnowList").html("");
	    $.post(
	            "QueryRecordByWorkId.action",
	            {
	            	work_id:wid,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].acceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].wrongAnswerTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].timeLimitTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].compileErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].runtimeErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemMemory  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemTimeLimit  +"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	}

}

function refresh(){
	window.location.replace(url);
}