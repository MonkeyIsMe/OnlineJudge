/**
 * Created by CallMeDad on 2019/8/31.
 */
var row = 1;  //页数
var count; //总记录数
var sid; //记录第几行的编号

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
				sub_count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllKnowledge.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
		           for(var i=0 ;i<data.length;i++){ //几个人有几个checkbox
		        	   $("#allTime").append(
				                "<span>"+
				                    "<input  type='checkbox' class='time' name='know' value='"+data[i].knowledgeId+"' title='"+data[i].knowledgeName+"'>" 
				                 +"</span>");
		           }
			}
			);
	
});

$(function(){
	
	//学生数据列表
    $.post(
        "QueryUserCourseByPageSize.action",
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
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].userid +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].uname +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].uaccount  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].uclassroom  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].cname  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a ><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:35px" ></span></a>'
			        		+'<a ><span  class="delete glyphicon glyphicon-plus-sign" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
			        		+"</td>");
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
	            "QueryUserCourseByPageSize.action",
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
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].userid +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].uname +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].uaccount  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].uclassroom  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].cname  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a ><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:35px" ></span></a>'
	    			        		+'<a ><span  class="delete glyphicon glyphicon-plus-sign" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+"</td>");
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
	            "QueryUserCourseByPageSize.action",
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
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].userid +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].uname +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].uaccount  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].uclassroom  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].cname  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a ><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:35px" ></span></a>'
	    			        		+'<a ><span  class="delete glyphicon glyphicon-plus-sign" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#UserList");
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
	    var col2=currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
	    var col4=currentRow.find("td:eq(3)").text(); //获得当前行第一个TD值
	    var col5=currentRow.find("td:eq(4)").text(); //获得当前行第一个TD值
	    
	    sid = col1;
	  });
		

});

function refresh(){
	window.location.replace("ManagerCourseUser.html");
}


