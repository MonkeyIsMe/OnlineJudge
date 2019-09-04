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
	
	//学生数据列表
    $.post(
        "QueryAllUser.action",
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
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].userId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].studentClassroom  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a id="pic"><span class="delete see_code glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px"></span></a>'
			        		+'<a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryAllUser.action",
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
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].userId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a id="pic"><span class="delete see_code glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" ></span></a>'
	    			        		+'<a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryAllUser.action",
	            {
	                page:row,
	                limit:25
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].userId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].studentClassroom  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a id="pic"><span class="delete see_code glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px"></span></a>'
	    			        		+'<a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
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

	  $("#myTable").on('click','.see_code',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2=currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
		    var col4=currentRow.find("td:eq(3)").text(); //获得当前行第一个TD值
		    var col5=currentRow.find("td:eq(4)").text(); //获得当前行第一个TD值
		    
		    sid = col1;
		    
		    var url = "UserCode.html?UserId=" + sid;
			window.location.replace(url);
		    
		  });
	  
		
});

function refresh(){
	window.location.replace("ManagerUserCodeSet.html");
}


