var count;
var row = 1;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountUserSubmission.action",
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
        "QueryUserSubmission.action",
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
	            "QueryUserSubmission.action",
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
	            "QueryUserSubmission.action",
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
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	}

}
