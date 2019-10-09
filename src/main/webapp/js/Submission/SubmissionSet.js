var count;
var row = 1;
var sid;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountSubmission.action",
			{
				
			}, 
			function(data) {
				var datas = JSON.parse(data);
				var sum = datas.SubmissionCount;
				var total = "共" + Math.ceil(sum/25) + "页";
				count = Math.ceil(sum/25);
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
	);
	
});



$(function(){
    $.post(
        "QuerySubmissionByPageSize.action",
        {
            page:row,
            limit:25,
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].submissionId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].problemId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionResult  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeMemory  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeType  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeLength  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete glyphicon glyphicon-refresh" style="cursor:pointer;margin-left:55px" data-toggle="modal" data-target="#myModal"></span></a>'
			        		+"</td>");
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
	            "QuerySubmissionByPageSize.action",
	            {
	                page:row,
	                limit:25,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].submissionId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemId  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userAccount +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeLength  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeMemory  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeType  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionResult  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete glyphicon glyphicon-refresh" style="cursor:pointer;margin-left:55px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+"</td>");
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
	            "QuerySubmissionByPageSize.action",
	            {
	                page:row,
	                limit:25,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].submissionId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemId  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userAccount +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeLength  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeMemory  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeType  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionResult  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete glyphicon glyphicon-refresh" style="cursor:pointer;margin-left:55px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+"</td>");
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
		    
		    sid = col1;
		    
		  });

	  $("#rejudge").click(function(){
		  //alert(sid);
			$.post(
					"UpdateSubmission.action",
					{
						submission_id:sid
					}, 
					function(data) {
						
					}
			);
	  });
	  
});

function refresh(){
	window.location.replace("ManagerSubmissionSet.html");
}