/**
 * Created by CallMeDad on 2019/9/8.
 */
var row = 1;  //页数
var count; //总记录数
var pid; //记录第几行的编号
var cnt = 0;


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
        "QueryProblemListManager.action",
        {
            page:row,
            limit:25
        },
        function(data) {
            var data = JSON.parse(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
                    var WorkFlag;
                    if(data[i].workFlag == 0) WorkFlag = "不公开";
                    else WorkFlag = "公开";
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].ProblemId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].ProblemName  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].degree  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].know  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].people  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete ans glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:62px" ></span></a>'
			        		+'<a><span  class="delete clear glyphicon glyphicon-tasks" style="cursor:pointer;margin-left:45px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryProblemListManager.action",
	            {
	                page:row,
	                limit:25
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	                        var WorkFlag;
	                        if(data[i].workFlag == 0) WorkFlag = "不公开";
	                        else WorkFlag = "公开";
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].ProblemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].ProblemName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].degree  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].know  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].people  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete ans glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:62px" ></span></a>'
	    			        		+'<a><span  class="delete clear glyphicon glyphicon-tasks" style="cursor:pointer;margin-left:45px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryProblemListManager.action",
	            {
	                page:row,
	                limit:25
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	                        var WorkFlag;
	                        if(data[i].workFlag == 0) WorkFlag = "不公开";
	                        else WorkFlag = "公开";
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].ProblemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].ProblemName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].degree  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].know  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].people  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete ans glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:62px" ></span></a>'
	    			        		+'<a><span  class="delete clear glyphicon glyphicon-tasks" style="cursor:pointer;margin-left:45px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	  $("#myTable").on('click','.ans',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    
	    pid = col1;
	    
	    var url = "ManagerAnswerSet.html?ProblemId=" + pid;
	    window.location.replace(url);
	  });
	  
	  $("#myTable").on('click','.clear',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    
		    pid = col1;
		    
		  });
	  
	  
	  $("#clear").click(function(){
		  	$.post(
					"ClearAnswerByProblemId.action",
					{
						problem_id:pid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("清空失败！");
						    var url = "ManagerProblemAnswerSet.html";
						    window.location.replace(url);
						}
						else{
							alert("清空成功!");
							var url = "ManagerProblemAnswerSet.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	 
});



function refresh(){
	window.location.replace("ManagerProblemAnswerSet.html");
}







