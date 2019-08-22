var row = 1;  //页数
var count; //总记录数
var rank = 1;

$(function(){
	
	$.ajaxSettings.async = false;
	$.post(
			"CountUser.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.UserCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
	$.post(
			"QueryAcceptRank.action",
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
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ (rank++)+"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].username +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].userclass  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].useraccept  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].usersubmission  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + GetPercent(data[i].useraccept,data[i].usersubmission)  +"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#UserList");
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
		$("#UserList").html("");
		$.post(
				"QueryAcceptRank.action",
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
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ (rank++)+"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].username +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].userclass  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].useraccept  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].usersubmission  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + GetPercent(data[i].useraccept,data[i].usersubmission)  +"</td>");
	                    // $("#J_TbData").append($trTemp);
	                    $trTemp.appendTo("#UserList");
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
		$("#UserList").html("");
		$.post(
				"QueryAcceptRank.action",
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
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ (rank++)+"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].username +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].userclass  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].useraccept  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].usersubmission  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + GetPercent(data[i].useraccept,data[i].usersubmission)  +"</td>");
	                    // $("#J_TbData").append($trTemp);
	                    $trTemp.appendTo("#UserList");
	                }
			}
		);
	}

}

function GetPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00)+"%";
}