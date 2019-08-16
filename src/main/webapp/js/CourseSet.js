/**
 * Created by CallMeDad on 2019/8/4.
 */
var row = 1;  //页数
var count; //总记录数
var cid; //记录第几行的编号
var cname; //记录第几行的名称
var cinfo; //记录第几行的备注信息
var cteacher; //记录第几行的授课老师
var ctime; //记录第几行的开课时间

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountCourse.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.CourseCount;
				count = Math.ceil(sum/15);
				var total = "共" + Math.ceil(sum/15) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
    $.post(
        "QueryCourseByPageSize.action",
        {
            page:row,
            limit:15
        },
        function(data) {
            var data = JSON.parse(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].courseId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].courseName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTeacher  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'    <a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>    '
			        		+'  <a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>    '
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
	            "QueryCourseByPageSize.action",
	            {
	                page:row,
	                limit:15
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].courseId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].courseName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTeacher  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'    <a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>    '
	    			        		+'  <a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>    '
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
	            "QueryCourseByPageSize.action",
	            {
	                page:row,
	                limit:15
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].courseId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].courseName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTeacher  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'    <a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>    '
	    			        		+'  <a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>    '
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
	    var col2=currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
	    var col3=currentRow.find("td:eq(2)").text(); //获得当前行第一个TD值
	    var col4=currentRow.find("td:eq(3)").text(); //获得当前行第一个TD值
	    var col5=currentRow.find("td:eq(4)").text(); //获得当前行第一个TD值
	    
	    cid = col1;
	    cname = col2;
	    ctime = col3;
	    cinfo = col4;
	    cteacher = col5;
	    
	    $("#update_name").val(cname);
	    $("#update_info").val(cinfo);
	    $("#update_teacher").val(cteacher);
	    $("#update_time").val(ctime);	    
	  });
	  
		$("#del_know").click(function(){
			//console.log(cid);
			$.post(
					"DeleteCourse.action",
					{
						course_id:cid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace("ManagerCourseSet.html");
						}
						else{
							alert("删除成功!");
							window.location.replace("ManagerCourseSet.html");
						}
					}
					);
		})
		
		
		$("#update_course").click(function(){
			
			var name = $("#update_name").val();
			var info = $("#update_info").val();
			var teacher = $("#update_teacher").val();
			var time = $("#update_time").val();
			
			if(name == null || name == "" || info == null || info == "" || teacher == "" || teacher == null || time == null || time == ""){
				alert("所有项均为非空!");
			}
			else{
				
				
				$.post(
						"UpdateCourse.action",
						{
							course_id:cid,
							course_info:info,
							course_name:name,
							course_time:time,
							course_teacher:teacher,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Fail"){
								alert("修改失败！");
								window.location.replace("ManagerCourseSet.html");
							}
							else{
								alert("修改成功!");
								window.location.replace("ManagerCourseSet.html");
							}
						}
						);
			}

		})
		
		$("#add_course").click(function(){
			
			var name = $("#add_name").val();
			var info = $("#add_info").val();
			var teacher = $("#add_teacher").val();
			var time = $("#add_time").val();
			
			if(name == null || name == "" || info == null || info == "" || teacher == "" || teacher == null || time == null || time == ""){
				alert("所有项均为非空!");
			}
			else{
				$.post(
						"AddCourse.action",
						{
							course_info:info,
							course_name:name,
							course_time:time,
							course_teacher:teacher,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Fail"){
								alert("添加失败！");
								window.location.replace("ManagerCourseSet.html");
							}
							else{
								alert("添加成功!");
								window.location.replace("ManagerCourseSet.html");
							}
						}
						);
			}
			

		})
});


function refresh(){
	window.location.replace("ManagerCourseSet.html");
}