/**
 * Created by CallMeDad on 2019/9/19.
 */

var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

var row = 1;  //页数
var count; //总记录数
var wid; //记录第几行的编号

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountRecordByWorkId.action",
			{
				work_id:wid
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.WorkProblemCount;
				var total = "共" + sum + "条数据";
				$("#TotalPage").append(total);
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



function refresh(){
	window.location.replace(url);
}

function backprev(){
	window.location.replace("ManagerWorkData.html");
}
