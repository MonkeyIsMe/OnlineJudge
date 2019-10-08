/**
 * Created by CallMeDad on 2019/8/8.
 */
var row = 1;  //页数
var count; //总记录数
var pid; //记录第几行的编号
var cnt = 0;


$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"SetUserInfo.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				if(data.user_role == '2'){
				    var url = "http://202.197.66.200:1188/OnlineJudge/Main.html";
				    window.location.replace(url);
				}
			}
			);
	
});

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
        "QueryProblemSubmission.action",
        {
            page:row,
            limit:25
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].ProblemId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].ProblemName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].SubmissionTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].TimeLimitTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].WrongAnswerTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].CompileErrorTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].RuntimeErrorTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50px" data-toggle="modal" data-target="#myModal"></span></a>'
			        		+"</td>");
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
	            "QueryProblemSubmission.action",
	            {
	                page:row,
	                limit:25
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].ProblemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].ProblemName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].SubmissionTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].TimeLimitTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].WrongAnswerTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].CompileErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].RuntimeErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+"</td>");
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
	            "QueryProblemSubmission.action",
	            {
	                page:row,
	                limit:25
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].ProblemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].ProblemName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].SubmissionTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].TimeLimitTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].WrongAnswerTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].CompileErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].RuntimeErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	}

}

$(document).ready(function(){
	  //用于读取所选表行单元格数据（值）的代码
	  $("#myTable").on('click','.delete',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    
	    pid = col1;
	    
	    
	  });
	  
		$("#del_problem").click(function(){
			//console.log(cid);
			$.post(
					"UpdateProblemSubmission.action",
					{
						problem_id:pid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("清空失败！");
							window.location.replace("ManagerProblemSubmission.html");
						}
						else{
							alert("清空成功!");
							window.location.replace("ManagerProblemSubmission.html");
						}
					}
					);
		})
		


});



function refresh(){
	window.location.replace("ManagerProblemSubmission.html");
}


