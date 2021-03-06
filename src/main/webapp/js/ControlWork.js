/**
 * Created by CallMeDad on 2019/9/12.
 */
var row = 1;  //页数
var count; //总记录数
var wid; //记录第几行的编号

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountWork.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.WorkCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
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
                    var WorkFlag;
                    if(data[i].workFlag == 0) WorkFlag = "作业";
                    else WorkFlag = "考试";
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workName  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workCreatTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workOwner  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a ><span class="delete check glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50%" ></span></a>'
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
	                        var WorkFlag;
	                        if(data[i].workFlag == 0) WorkFlag = "作业";
	                        else WorkFlag = "考试";
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workCreatTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workOwner  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a ><span class="delete check glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50%" ></span></a>'
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
	                        var WorkFlag;
	                        if(data[i].workFlag == 0) WorkFlag = "作业";
	                        else WorkFlag = "考试";
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workCreatTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workOwner  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a ><span class="delete check glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50%" ></span></a>'
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
	    
	    wid = col1;
	    var url = "WorkDetail.html?WorkId=" + wid;
	    window.location.replace(url);
	  });



});


function refresh(){
	window.location.replace("ControlWork.html");
}
