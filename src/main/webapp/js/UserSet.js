/**
 * Created by CallMeDad on 2019/8/4.
 */
var row = 1;  //页数
var count; //总记录数
var sid; //记录第几行的编号
var sname; //记录第几行的名称
var sinfo; //记录第几行的备注信息
var sclassroom; //记录第几行的教室

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
	
});

$(function(){
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
			        		'<a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>'
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
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].studentClassroom  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>'
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
	    			        		'<a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>'
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
	    sname = col2;
	    sinfo = col4;
	    sclassroom = col5;
	    
	    $("#update_name").val(sname);
	    $("#update_info").val(sinfo);
	    $("#update_classroom").val(sclassroom);
	    //alert(sclassroom)
	    //console.log(cname + " " + cinfo);
	    //$("#update_name").append(cname);
	    //$("#update_info").append(cinfo);
	  });
	  
		$("#del_user").click(function(){
			//console.log(cid);
			$.post(
					"DeleteUser.action",
					{
						user_id:sid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace("ManagerUserSet.html");
						}
						else{
							alert("删除成功!");
							window.location.replace("ManagerUserSet.html");
						}
					}
					);
		})
		
		
		$("#update_user").click(function(){
			
			var name = $("#update_name").val();
			var info = $("#update_info").val();
			var classroom = $("#update_classroom").val();
			
			if(name == null || name == "" || info == null || info == "" || classroom == null || classroom == ""){
				alert("所有项均为非空!");
			}
			else{
				$.post(
						"UpdateUser.action",
						{
							user_id:sid,
							user_info:info,
							user_name:name,
							user_classroom:classroom,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Fail"){
								alert("修改失败！");
								window.location.replace("ManagerUserSet.html");
							}
							else{
								alert("修改成功!");
								window.location.replace("ManagerUserSet.html");
							}
						}
						);
			}
			

		})
		
		
		$("#add_user").click(function(){
			
			var name = $("#add_name").val();
			var info = $("#add_info").val();
			var classroom = $("#add_classroom").val();
			
			if(name == null || name == "" || info == null || info == "" || classroom == null || classroom == ""){
				alert("所有项均为非空!");
			}
			else{
				$.post(
						"AddUser.action",
						{
							user_info:info,
							user_name:name,
							user_classroom:classroom,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Fail"){
								alert("添加失败！");
								window.location.replace("ManagerUserSet.html");
							}
							else{
								alert("添加成功!");
								window.location.replace("ManagerUserSet.html");
							}
						}
						);
			}
			

		})
});

function refresh(){
	window.location.replace("ManagerUserSet.html");
}
