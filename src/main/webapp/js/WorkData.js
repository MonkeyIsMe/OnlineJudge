/**
 * Created by CallMeDad on 2019/9/18.
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
			"CountByWorkId.action",
			{
				work_id:wid
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.WorkRecordCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
    $.post(
        "QueryByWorkIdPageSize.action",
        {
        	work_id:wid,
            page:row,
            limit:25
        },
        function(data) {
            var data = JSON.parse(data);
            console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].WorkRecordId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].SubmitNumber  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].WrongAnswerTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].TimeLimitTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].RuntimeErrorTimes  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].ProblemName  +"</td>");
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
	            "QueryAllWork.action",
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
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].WorkRecordId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].SubmitNumber  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].WrongAnswerTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].TimeLimitTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].RuntimeErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].ProblemName  +"</td>");
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
	            "QueryAllWork.action",
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
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].WorkRecordId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].SubmitNumber  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].WrongAnswerTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].AcceptTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].TimeLimitTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].RuntimeErrorTimes  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].ProblemName  +"</td>");
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
	    
	    wid = col1;
	    
	  });
	  
	  
	  $("#myTable").on('click','.check',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var url = "ManageSingleWork.html?WorkId=" + col1;
		    window.location.replace(url);
		    
		  });
	  
	  $("#myTable").on('click','.work',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var url = "UpdateProblemForWork.html?WorkId=" + col1;
		    window.location.replace(url);
		    
		  });
	  
	  $("#myTable").on('click','.see_work',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var url = "WorkData.html?WorkId=" + col1;
		    window.location.replace(url);
		    
		  });
	  
		$("#del_work").click(function(){
			//console.log(cid);
			$.post(
					"DeleteWork.action",
					{
						work_id:wid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace("ManagerWorkData.html");
						}
						else{
							alert("删除成功!");
							window.location.replace("ManagerWorkData.html");
						}
					}
					);
		})
		


});


function refresh(){
	window.location.replace(url);
}

function backprev(){
	window.location.replace("ManagerWorkData.html");
}
